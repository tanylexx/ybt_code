package com.sinosoft.surrender.cashvalue.bo.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sinosoft.surrender.cashvalue.bo.CalAccountValueBO;
import com.sinosoft.surrender.cashvalue.dao.LcInsureAccSpecDAO;
import com.sinosoft.surrender.cashvalue.dao.LcInsureAccTraceSpecDAO;
import com.sinosoft.surrender.cashvalue.dao.LdIntegrationRateSpecDAO;
import com.sinosoft.surrender.cashvalue.dto.InterestSubsectionDTO;
import com.sinosoft.surrender.cashvalue.dto.SurrenderDTO;
import com.sinosoft.surrender.common.constant.ENUM_EDOR_TYPE;
import com.sinosoft.surrender.common.contant.surrenderenum.ENUM_EDOR_ACC_TYPE;
import com.sinosoft.surrender.common.contant.surrenderenum.ENUM_LIVE_GET_TYPE;
import com.sinosoft.surrender.common.exception.RulesCheckedException;
import com.sinosoft.surrender.common.util.ArithUtil;
import com.sinosoft.surrender.common.util.DateUtil;
import com.sinosoft.surrender.common.util.ExceptionUtil;
import com.sinosoft.surrender.common.util.InterestUtil;
import com.sinosoft.surrender.db.model.LcInsureAcc;
import com.sinosoft.surrender.db.model.LcInsureAccTrace;
import com.sinosoft.surrender.db.model.LcPol;
import com.sinosoft.surrender.db.model.LdIntegrationRate;

@Component
public class CalAccountValueBOImpl implements CalAccountValueBO {
	private Logger logger = LoggerFactory.getLogger(getClass());
	private boolean debug = logger.isDebugEnabled();
	@Autowired
	private LcInsureAccSpecDAO lcInsureAccSpecDAO;
	@Autowired
	private LcInsureAccTraceSpecDAO lcInsureAccTraceSpecDAO;
	@Autowired
	private LdIntegrationRateSpecDAO ldIntegrationRateSpecDAO;

	@Override
	public BigDecimal calAccountValue(SurrenderDTO surrenderDTO) {
		long currentTime = System.currentTimeMillis();
		// 账户价值
		BigDecimal accountVaule = BigDecimal.ZERO;
		// 若保全类型为犹退，账户价值为零。
		if (ENUM_EDOR_TYPE.WT.getValue().equals(surrenderDTO.getEdorType())) {
			return accountVaule;
		}
		LcPol lcPol = surrenderDTO.getLcPol();
		ExceptionUtil.checkNull(lcPol, "个人险种为空，请核实！");
		if (StringUtils.isNotBlank(lcPol.getLivegetmode())
				&& !ENUM_LIVE_GET_TYPE.LIVEINTEREST.getValue().equals(lcPol.getLivegetmode())) {
			return accountVaule;
		}
		// 生存金，账户结息
		String accType = ENUM_EDOR_ACC_TYPE.LIVEACC.getValue();
		List<LcInsureAcc> lcInsureAccList = lcInsureAccSpecDAO.getLcInsureAccByNoAndType(lcPol.getPolno(), accType);
		if (CollectionUtils.isEmpty(lcInsureAccList)) {
			return accountVaule;
		}
		for (LcInsureAcc lcInsureAcc : lcInsureAccList) {
			List<LcInsureAccTrace> lcInsureAccTraceInfos = lcInsureAccTraceSpecDAO
					.getLcInsureAccTraceInfos(lcInsureAcc);
			if (CollectionUtils.isEmpty(lcInsureAccTraceInfos)) {
				return accountVaule;
			}
			// 单轨迹计算利息，目前只支持年结
			for (LcInsureAccTrace lcInsureAccTrace : lcInsureAccTraceInfos) {
				// 本日期已结算过利息，下次开始日期需要在一天后开始
				Date startDate = DateUtil.calDate(lcInsureAccTrace.getPaydate(), 1, DateUtil.DATE_TYPE_D, null);
				Date endDate = DateUtil.calDate(lcInsureAccTrace.getPaydate(), 1, DateUtil.DATE_TYPE_Y, null);
				if (surrenderDTO.getzTPoint().before(endDate)) {
					endDate = surrenderDTO.getzTPoint();
				}
				// 生存金
				BigDecimal liveMoney = lcInsureAccTrace.getMoney();
				// 账户利息
				BigDecimal accoutInterest = getInterest(startDate, endDate, accountVaule);
				// 生存金利息
				BigDecimal liveInterest = getInterest(startDate, endDate, liveMoney);
				// 总利息
				BigDecimal countInterest = accoutInterest.add(liveInterest);
				//modify by jinwc 2020-8-4 lisbuug-8196 利息累加后再四舍五入，而不是单笔生存金四舍五入再累加
				//countInterest = new BigDecimal(ArithUtil.round(countInterest.doubleValue(), 2) + "");// 保留两位小数(加字符串失精度)
				
				// 账户价值
				accountVaule = accountVaule.add(liveMoney).add(countInterest);
				
				logger.info("计算开始日期为{},结束日期为：{}，生存金利息为{}，账户利息为：{}，利息总和为{}，此时账户价值为：{}！",
						DateUtil.getDateStr(startDate, "yyyy-MM-dd"), DateUtil.getDateStr(endDate, "yyyy-MM-dd"),
						ArithUtil.round(liveInterest.doubleValue(), 2),
						ArithUtil.round(accoutInterest.doubleValue(), 2),
						ArithUtil.round(accoutInterest.add(liveInterest).doubleValue(), 2),
						ArithUtil.round(accountVaule.doubleValue(), 2));
				
				
				
				if (debug) {
					logger.info("计算开始日期为{},结束日期为：{}，生存金利息为{}，账户利息为：{}，此时账户价值为：{}！",
							DateUtil.getDateStr(startDate, "yyyy-MM-dd"), DateUtil.getDateStr(endDate, "yyyy-MM-dd"),
							ArithUtil.round(liveInterest.doubleValue(), 2),
							ArithUtil.round(accoutInterest.doubleValue(), 2),
							ArithUtil.round(accountVaule.doubleValue(), 2));
				}
			}
			// 四舍五入处理
			accountVaule = new BigDecimal(ArithUtil.round(accountVaule.doubleValue(), 2) + "");// 保留两位小数(加字符串失精度)
		}
		logger.info("计算账户价值,价值为：{},耗时：{}毫秒", accountVaule, System.currentTimeMillis() - currentTime);
		return accountVaule;
	}

	/**
	 * 计算利息 ①根据利率表，将退保点与上次结息日分段，得到每段的利息，以及此利率段的天数 ②循环利率段，计算复利，利息
	 * 
	 * @history: 2018-4-23 wangwl_sinosoft
	 * @param startDate
	 * @param endDate
	 * @param insuaccbala
	 * @return
	 */
	private BigDecimal getInterest(Date startDate, Date endDate, BigDecimal insuaccbala) {
		BigDecimal interestCount = BigDecimal.ZERO;
		// 查询利率表，得到利率段
		List<InterestSubsectionDTO> interestSubsectionDTOList = calInterestSubsection(startDate, endDate);
		if (CollectionUtils.isEmpty(interestSubsectionDTOList)) {
			throw new RulesCheckedException("账户利息计算有误,烦请核实!");
		}
		// 复利
		for (InterestSubsectionDTO interestSubsectionDTO : interestSubsectionDTOList) {
			// 4.3 计算利息
			BigDecimal subsectionRate = InterestUtil.calAccValue(interestSubsectionDTO, insuaccbala);
			insuaccbala = insuaccbala.add(subsectionRate);
			// 利息累加，得到总利息
			interestCount = interestCount.add(subsectionRate);
		}
		return interestCount;

	}

	/**
	 * 分段处理
	 * 
	 * @history: 2018-4-22 wangwl_sinosoft
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	private List<InterestSubsectionDTO> calInterestSubsection(Date startDate, Date endDate) {
		List<InterestSubsectionDTO> interestSubsectionDTOList = new ArrayList<InterestSubsectionDTO>();
		List<LdIntegrationRate> ldIntegrationRateInfos = ldIntegrationRateSpecDAO.getLdIntegrationRateInfos(startDate,
				endDate);
		if (CollectionUtils.isEmpty(ldIntegrationRateInfos)) {
			throw new RulesCheckedException("未配置对应的利息，请核实!");
		}
		for (LdIntegrationRate ldIntegrationRate : ldIntegrationRateInfos) {
			Date rateStartDate = ldIntegrationRate.getStartdate();// 利率段的开始时间
			Date rateEndDate = ldIntegrationRate.getEnddate();// 利率段的结束时间
			//转成LONG类型进行比较，较为准确
			long startDateLong = startDate.getTime();
			long endDateLong = endDate.getTime();
			long rateStartDateLong = rateStartDate.getTime();
			long rateEndDateLong = rateEndDate.getTime();
			InterestSubsectionDTO interestSubsectionDTO = new InterestSubsectionDTO();
			// 利率段的开始时间 < 开始时间 && 利率段的结束时间 > 结束时间
			if(rateStartDateLong<startDateLong && rateEndDateLong>endDateLong) {
				interestSubsectionDTO.setStartDate(startDate);
				interestSubsectionDTO.setEndDate(endDate);
				int days = DateUtil.calIntervalOfAbandon(startDate, endDate, "D") + 1;
				interestSubsectionDTO.setDays(days);
				// 利率段的开始时间 < 开始时间 && 开始时间 <= 利率段的结束时间 <= 结束时间	
			}else if (rateStartDateLong<startDateLong && startDateLong<=rateEndDateLong && rateEndDateLong<=endDateLong) {
				// 取时间分段的开始时间和利率段的结束时间之间的天数
				interestSubsectionDTO.setStartDate(startDate);
				interestSubsectionDTO.setEndDate(rateEndDate);
				int days = DateUtil.calIntervalOfAbandon(startDate, rateEndDate, "D") + 1;
				interestSubsectionDTO.setDays(days);
				// 利率段的结束时间 > 结束时间 && 开始时间 <= 利率段的开始时间 <=结束时间	
			}else if (rateEndDateLong>endDateLong && startDateLong<= rateStartDateLong && rateStartDateLong<=endDateLong) {
				// 取利率段的开始时间和结束时间之间的天数
				interestSubsectionDTO.setStartDate(rateStartDate);
				interestSubsectionDTO.setEndDate(endDate);
				int days = DateUtil.calIntervalOfAbandon(rateStartDate, endDate, "D") + 1;
				interestSubsectionDTO.setDays(days);
				// 开始时间 <= 利率段的开始时间 <= 利率段的结束时间<= 结束时间	
			}else if (startDateLong<=rateStartDateLong && rateEndDateLong<=endDateLong) {
				// 取利率段的开始和结束时间之间的天数
				interestSubsectionDTO.setStartDate(rateStartDate);
				interestSubsectionDTO.setEndDate(rateEndDate);
				int days = DateUtil.calIntervalOfAbandon(rateStartDate, rateEndDate, "D") + 1;
				interestSubsectionDTO.setDays(days);
			}
			// 获取日利率
			double rate = InterestUtil.TransAccRate(ldIntegrationRate.getRate().doubleValue(),
					ldIntegrationRate.getRateintv(), ldIntegrationRate.getRatetype(), "D");
			interestSubsectionDTO.setRate(new BigDecimal(String.valueOf(rate)));
			interestSubsectionDTOList.add(interestSubsectionDTO);
		}
		return interestSubsectionDTOList;
	}

}
