package com.sinosoft.surrender.cashvalue.bo.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sinosoft.surrender.common.util.DateUtil;
import com.guohualife.platform.common.api.util.StringUtil;
import com.sinosoft.surrender.cashvalue.bo.DealInterestMaxEndDateBO;
import com.sinosoft.surrender.cashvalue.dao.InterestSpecDAO;
import com.sinosoft.surrender.cashvalue.dao.LcPolSpecDAO;
import com.sinosoft.surrender.common.cache.RedisCacheBO;
import com.sinosoft.surrender.common.contant.Constant;
import com.sinosoft.surrender.common.contant.DateConstant;
import com.sinosoft.surrender.db.model.LcPol;

@Component
public class DealInterestMaxEndDateBOImpl implements DealInterestMaxEndDateBO {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private InterestSpecDAO interestSpecDAO;
	@Autowired
	private LcPolSpecDAO lcPolSpecDAO;
	@Autowired
	private RedisCacheBO redisCacheBO;
	
	@Override
	public void dealInterestMaxEndDate(String contno) {
		// 查询险种信息
		List<LcPol> lcpols = lcPolSpecDAO.getLcPolByContNo(contno);
		for(LcPol lcpol : lcpols){
			// 险种
			String riskcode = lcpol.getRiskcode();
			// 保单生效日期
			Date cvalidate = lcpol.getCvalidate();
			// 查询维护了的最大利率结束日期
			Date maxEndDate = interestSpecDAO.findMaxEndDateByContnoAndRikcode(contno, riskcode, cvalidate);
			if(maxEndDate != null){
				String date = DateUtil.getDateStr(maxEndDate, DateConstant.DATE_FORMAT_1);
				// 推送到redis缓存中
				saveMaxEndDateToResdis(contno, riskcode, date);
			}
		}
	}

	@Override
	public boolean checkIsNewinterest(String contno, Date applyDate) {
		// 查询险种信息
		List<LcPol> lcpols = lcPolSpecDAO.getLcPolByContNo(contno);
		for(LcPol lcpol : lcpols){
			StringBuffer sbf = new StringBuffer();
			sbf.append(contno);
			sbf.append("|");
			sbf.append(lcpol.getRiskcode());
			sbf.append("|");
			sbf.append(Constant.MAXENDDATE);
			String key = sbf.toString();
			// 从redis中查询上次计算现价时保存的利率的最大结束日期
			String date = redisCacheBO.getEndDateValueOfRedis(key);
			if(StringUtil.isNotBlank(date)){
				// 类型转换
				Date maxEndDate = DateUtil.getDate(date, DateConstant.DATE_FORMAT_1); 
				// maxEndDate < 申请日期
				if(DateUtil.calIntervalOfAbout(maxEndDate, applyDate, DateUtil.DATE_TYPE_D) > 0){
					// 查询现已维护了的最大利率结束日期
					Date newmaxEndDate = interestSpecDAO.findMaxEndDateByContnoAndRikcode(contno, lcpol.getRiskcode(), lcpol.getCvalidate());
					// 如果 maxEndDate < newmaxEndDate
					if(DateUtil.calIntervalOfAbout(maxEndDate, newmaxEndDate, DateUtil.DATE_TYPE_D) > 0){
						// 说明维护了新的利率
						return true;
					}
				}
			}
		}
		return false;
	}

	
	/**
	 * 将最大的结束日期保存到redis中
	 * @param endDateMap
	 */
	private void saveMaxEndDateToResdis(String contno, String riskcode, String maxEndDate){
		// redis的key：保单号|险种|MAXENDDATE
		StringBuffer sbf = new StringBuffer();
		sbf.append(contno);
		sbf.append("|");
		sbf.append(riskcode);
		sbf.append("|");
		sbf.append(Constant.MAXENDDATE);
		String key = sbf.toString();
		logger.info("====>将结算利率最大的结束日期推送至Redis中<====");
		// 先删后插
		redisCacheBO.deleteRedisCache(key);
		boolean putRedisCache = redisCacheBO.putRedisCache(key, maxEndDate);
		if (!putRedisCache) {
			logger.info("key为：{}推送Redis数据失败，请核实！", key);
		}
	}
}
