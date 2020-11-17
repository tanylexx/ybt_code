package com.sinosoft.surrender.cashvalue.bo.abs;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.sinosoft.surrender.cashvalue.bo.CalPolicyYearEndAlivePayBO;
import com.sinosoft.surrender.cashvalue.bo.CalSurrenderBO;
import com.sinosoft.surrender.cashvalue.dao.LcGetSpecDAO;
import com.sinosoft.surrender.cashvalue.dto.AlgorithElementDTO;
import com.sinosoft.surrender.cashvalue.dto.SurrenderDTO;
import com.sinosoft.surrender.common.contant.Constant;
import com.sinosoft.surrender.common.contant.DateConstant;
import com.sinosoft.surrender.common.exception.CalPolicyCashValueException;
import com.sinosoft.surrender.common.util.DateUtil;
import com.sinosoft.surrender.common.util.ExceptionUtil;
import com.sinosoft.surrender.db.dao.LmDutyGetAliveDAO;
import com.sinosoft.surrender.db.dao.LmDutyGetDAO;
import com.sinosoft.surrender.db.model.LcGet;
import com.sinosoft.surrender.db.model.LcPol;
import com.sinosoft.surrender.db.model.LmDutyGet;
import com.sinosoft.surrender.db.model.LmDutyGetAlive;
import com.sinosoft.surrender.db.model.LmDutyGetAliveKey;

/**
 * 
 * 退保参数中使用的年末生存金型当年的生存金.如果有之前应领未领的金额,这里不算
 * 
 * 
 * @author: wangwl_sinosoft
 * @date: 2018-4-1-下午1:25:27
 * @version:
 */
public abstract class AbsCalPolicyYearEndAlivePayBO implements CalPolicyYearEndAlivePayBO {
	private Logger logger = LoggerFactory.getLogger(getClass());
	private boolean debug = logger.isDebugEnabled();
	@Autowired
	private LcGetSpecDAO lcGetSpecDAO;
	@Autowired
	private LmDutyGetDAO lmDutyGetDAO;
	@Autowired
	private LmDutyGetAliveDAO lmDutyGetAliveDAO;
	@Autowired
	private CalSurrenderBO calSurrenderBO;

	@Override
	public BigDecimal calPolicyYearEndAlivePay(SurrenderDTO surrenderDTO) {
		long currentTime = System.currentTimeMillis();
		
		// 计算本年度末的生存金，当交至日小于退保日时，退保点取交至日
		Date ztDate = surrenderDTO.getzTPoint();
		ExceptionUtil.checkNull(ztDate, "退保点为空，请核实！");
		Date payToDate = surrenderDTO.getPayToDate();
		ExceptionUtil.checkNull(payToDate, "交至日期为空，请核实！");
		if (debug) {
			logger.debug("退保点为：{}，交至日期为：{}", DateUtil.getDateStr(ztDate, DateConstant.DATE_FORMAT_7),
					DateUtil.getDateStr(payToDate, DateConstant.DATE_FORMAT_7));
		}
		int size = DateUtil.calIntervalOfAbandon(ztDate, payToDate, DateConstant.PERIOD_UNITS_DAY);
		// 若退保日期和交至日期相等，为了保证计算生存金的年度正确，这里将退保点-1天
		if (size == 0) {
			ztDate = DateUtil.calDate(ztDate, -1, DateConstant.PERIOD_UNITS_DAY, null);
		}
		LcPol lcPol = surrenderDTO.getLcPol();
		ExceptionUtil.checkNull(lcPol, "险种表信息为空，请核实！");
		// 保单生效日
		Date cValiDate = lcPol.getCvalidate();
		// 跟据保单生效日与退保点，计算生存金的年度
		int interval = DateUtil.calIntervalOfAbandon(cValiDate, ztDate, DateConstant.PERIOD_UNITS_YEAR);
		// 保单年度开始日期
		Date dateSatrt = DateUtil.calDate(cValiDate, interval, DateConstant.PERIOD_UNITS_YEAR, null);
		// 本年度末日期
		Date dateEnd = DateUtil.calDate(dateSatrt, 1, DateConstant.PERIOD_UNITS_YEAR, null);
		if (debug) {
			logger.debug("保单年度开始日期：{}，本年度末日期为：{}", DateUtil.getDateStr(dateSatrt, DateConstant.DATE_FORMAT_7),
					DateUtil.getDateStr(dateEnd, DateConstant.DATE_FORMAT_7));
		}
		surrenderDTO.setEndDate(dateEnd);
		// 根据 polNo,管理机构，保单年度开始时间以及结束时间查询lcget
		List<LcGet> lcGetInfos = lcGetSpecDAO.getLcGetInfos(lcPol.getPolno(), dateSatrt, dateEnd);
		BigDecimal getMoney = BigDecimal.ZERO;
		if (CollectionUtils.isEmpty(lcGetInfos)) {
			return getMoney;
		}
		for (LcGet lcGet : lcGetInfos) {
		    surrenderDTO.setLcGet(lcGet);
			BigDecimal money = calGetMoney(lcGet, surrenderDTO);
			if (Constant.NEGATIVE_ONE.compareTo(money) == 0) {
				throw new CalPolicyCashValueException("年末生存金计算有误，请核实！");
			}
			getMoney = getMoney.add(money);
		}
		logger.info("计算年末生存给付,年末生存给付为：{},耗时：{}毫秒", getMoney, System.currentTimeMillis() - currentTime);
		return getMoney;
	}

	/**
	 * 
	 * 计算给付金额
	 * 
	 * @history: 2018-3-29
	 * @author: wangwl_sinosoft
	 * @param lcGet
	 * @return
	 */
	private BigDecimal calGetMoney(LcGet lcGet, SurrenderDTO surrenderDTO) {
		BigDecimal getMoney = BigDecimal.ZERO;
		// 判断是否为生存给付责任。
		LmDutyGet lmDutyGet = lmDutyGetDAO.selectByPrimaryKey(lcGet.getGetdutycode());
		ExceptionUtil.checkNull(lmDutyGet, "无给付责任描述数据！");
		LmDutyGetAliveKey key = new LmDutyGetAliveKey();
		key.setGetdutycode(lcGet.getGetdutycode());
		key.setGetdutykind(lcGet.getGetdutykind());
		LmDutyGetAlive lmDutyGetAlive = lmDutyGetAliveDAO.selectByPrimaryKey(key);
		ExceptionUtil.checkNull(lmDutyGetAlive, "无生存给付描述数据！");
		// 期领责任给付计算
		getMoney = calGetMoneyOfStage(lcGet, lmDutyGetAlive, surrenderDTO);
		logger.info("期领责任给付:{}", getMoney);
		return getMoney;
	}

	/**
	 * 
	 * 期领责任给付计算,根据计算编码查询核心配置算法
	 * 
	 * @history: 2018-3-29
	 * @author: wangwl_sinosoft
	 * @param getToDate
	 * @param lcGet
	 * @param lmDutyGetAlive
	 * @param surrenderDTO
	 * @return
	 */
	private BigDecimal calGetMoneyOfStage(LcGet lcGet, LmDutyGetAlive lmDutyGetAlive, SurrenderDTO surrenderDTO) {
		BigDecimal getMoney = BigDecimal.ZERO;
		AlgorithElementDTO algorithElementDTO = fillAlgorithmElementDTO(surrenderDTO);
		// 计算应领金额
		String calCode = lmDutyGetAlive.getCalcode();
		getMoney = calSurrenderBO.calGetSurrender(calCode, algorithElementDTO);
		return getMoney;
	}

	/**
	 * 
	 * 填充计算要素
	 * 
	 * @history: 2018-3-29
	 * @author: wangwl_sinosoft
	 * @param surrenderDTO
	 * @return
	 */
	protected abstract AlgorithElementDTO fillAlgorithmElementDTO(SurrenderDTO surrenderDTO);

}
