package com.sinosoft.surrender.cashvalue.bo.impl;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.CaseInsensitiveMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.stereotype.Component;

import com.sinosoft.surrender.cashvalue.bo.CalculatorBO;
import com.sinosoft.surrender.cashvalue.dao.LmCalFactorSpecDAO;
import com.sinosoft.surrender.cashvalue.dto.CalculatorDTO;
import com.sinosoft.surrender.common.cache.YdCashValueRuleCfgBO;
import com.sinosoft.surrender.common.exception.CalPolicyCashValueException;
import com.sinosoft.surrender.common.exception.TradeException;
import com.sinosoft.surrender.common.util.DateUtil;
import com.sinosoft.surrender.db.dao.LmCalModeDAO;
import com.sinosoft.surrender.db.model.LmCalFactor;
import com.sinosoft.surrender.db.model.LmCalMode;
import com.sinosoft.surrender.db.model.YdCashValueRule;

/**
 * 
 * 计算类
 * 
 * @author: wangwl_sinosoft
 * @date: 2018-3-13-下午3:37:05
 * @version:
 */
@Component
public class CalculatorBOImpl implements CalculatorBO {
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private LmCalModeDAO lmCalModeDAO;

	@Autowired
	private LmCalFactorSpecDAO lmCalFactorSpecDAO;
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	@Autowired
	private YdCashValueRuleCfgBO ydCashValueRuleCfgBO;

	@Override
	public String calculate(CalculatorDTO calculatorDTO) {
		return calculateNoSpec(calculatorDTO);
	}

	@Override
	public String calCashValue(CalculatorDTO calculatorDTO) {
		return calSpecCashValue(calculatorDTO);
	}

	/**
	 * 
	 * 计算 lMCALMODEL
	 * 
	 * 
	 * @history: 2018-3-15
	 * @author: wangwl_sinosoft
	 * @param calculatorDTO
	 * @return
	 */
	private String calculateNoSpec(CalculatorDTO calculatorDTO) {
		String calCode = calculatorDTO.getCalcode();
		Object obj = calculatorDTO.getParamsObj();
		try {
			logger.debug("deal calcode===" + calCode);
			LmCalMode lMCalMode = lmCalModeDAO.selectByPrimaryKey(calCode);
			if (lMCalMode == null) {
				calculatorDTO.setErrFlag(true);
				return "0";
			}
			String sSql = lMCalMode.getCalsql();

			List<LmCalFactor> lMCalFactorList = lmCalFactorSpecDAO.getFactorByCalCode(calCode);
			if (lMCalFactorList != null) {
				for (LmCalFactor lMCalFactor : lMCalFactorList) {
					CalculatorDTO innerCalculatorDTO = new CalculatorDTO();
					innerCalculatorDTO.setCalcode(lMCalFactor.getFactorcalcode());
					innerCalculatorDTO.setParamsObj(obj);
					String innerRes = calculate(innerCalculatorDTO);
					if (innerCalculatorDTO.isErrFlag()) {
						calculatorDTO.setErrFlag(true);
						return "0";
					}
					sSql = sSql.replace('?' + lMCalFactor.getFactorcode() + '?', innerRes);
				}
			}
			
			String result = exeWithParam(sSql, obj, calculatorDTO);
			if(calculatorDTO.isErrFlag()){
				throw new TradeException("异常算法，计算失败，请核实！");
			}
			
			return result;

		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException("算法计算失败!算法编码:" + calCode + "！错误信息:" + ex.getMessage() + ex.getClass().getName());
		}
	}

	/**
	 * 
	 * 现价公式
	 * 
	 * @history: 2018-3-15
	 * @author: wangwl_sinosoft
	 * @param calculatorDTO
	 * @return
	 */
	private String calSpecCashValue(CalculatorDTO calculatorDTO) {
		String calCode = calculatorDTO.getCalcode();
		Object obj = calculatorDTO.getParamsObj();
		try {
			logger.debug("deal calcode===" + calCode);
			YdCashValueRule ydCashValueRule = ydCashValueRuleCfgBO.getYdCashValueRule(calCode);
			if (ydCashValueRule == null) {
				calculatorDTO.setErrFlag(true);
				return "0";
			}
			String sSql = ydCashValueRule.getCalsql();
			
			String value = exeWithParam(sSql, obj, calculatorDTO);
			
			if(calculatorDTO.isErrFlag()){
				throw new CalPolicyCashValueException("异常现价算法，计算失败，请核实！");
			}
			return value;

		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException("算法计算失败!算法编码:" + calCode + "！错误信息:" + ex.getMessage() + ex.getClass().getName());
		}
	}

	/**
	 * 
	 * 解析sql
	 * 
	 * @history: 2018-3-15
	 * @author: wangwl_sinosoft
	 * @param sSql
	 * @param obj
	 * @param calculatorDTO
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	@SuppressWarnings("unchecked")
	private String exeWithParam(String sSql, Object obj, CalculatorDTO calculatorDTO) throws IllegalArgumentException,
			IllegalAccessException {
		if (obj != null) {
			Map<String, Object> objMap;
			if (obj instanceof Map) {
				objMap = new CaseInsensitiveMap((Map<String, Object>) obj);

			} else {
				objMap = new CaseInsensitiveMap();
				Field[] declaredFields = obj.getClass().getDeclaredFields();
				for (Field field : declaredFields) {
					field.setAccessible(true);
					if (!field.getType().isPrimitive()) {
						if (field.get(obj) == null && field.getType().getSuperclass() == Number.class) {
							objMap.put(field.getName(), BigDecimal.ZERO);
							continue;
						}
					}
					objMap.put(field.getName(), field.get(obj));
				}
			}

			int fInd = sSql.indexOf('?');
			while (fInd >= 0 && sSql.length() > (fInd + 1)) {
				String sSubSql = sSql.substring(fInd + 1);

				int sInd = sSubSql.indexOf('?');
				if (sInd >= 0) {

					sSql = sSql.replace(sSql.substring(fInd, fInd + 2 + sInd),
							getParamStr(objMap, sSql.substring(fInd + 1, fInd + 1 + sInd)));
				} else {
					break;
				}
				fInd = sSql.indexOf('?');
			}

		}
		logger.info("sSql==" + sSql);
		String sRes;
		try {
			List<String> sResList = namedParameterJdbcTemplate.query(sSql, new ParameterizedRowMapper<String>() {
				public String mapRow(ResultSet rs, int rowNum) throws SQLException {
					return rs.getString(1);
				}
			});
			if (sResList == null || sResList.size() < 1) {
				return "";
			}
			sRes = sResList.get(0);
		} catch (BadSqlGrammarException ex) {
			ex.printStackTrace();
			calculatorDTO.setErrFlag(true);
			logger.error("算法sql有误，信息为{}",ex.getMessage());
			return "0";
		} catch (Exception ex) {
			ex.printStackTrace();
			calculatorDTO.setErrFlag(true);
			logger.error("异常错误，信息为{}",ex.getMessage());
			return "0";
		}
		if (sRes == null) {
			return "";
		}
		return sRes;
	}

	private String getParamStr(Map<String, Object> objMap, String paramName) {
		Object obj = objMap.get(paramName);
		if (obj == null) {
			logger.info("param:" + paramName + " Should Be Setted!");
			return "";
		} else if (obj instanceof Date) {
			return DateUtil.getDateStr((Date) obj, "yyyy-MM-dd");
		} else {
			return obj.toString();
		}
	}

}
