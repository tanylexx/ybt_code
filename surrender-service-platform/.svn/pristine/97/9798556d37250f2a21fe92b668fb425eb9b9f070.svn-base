package com.sinosoft.surrender.cashvalue.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinosoft.platform.common.api.util.XmlUtil;
import com.sinosoft.surrender.cashvalue.bo.CalPolicyCashValueBO;
import com.sinosoft.surrender.cashvalue.bo.CallCoreCalCashValueBO;
import com.sinosoft.surrender.cashvalue.bo.DealInterestMaxEndDateBO;
import com.sinosoft.surrender.cashvalue.bo.SurrenderSaveDataBO;
import com.sinosoft.surrender.cashvalue.bo.YcPolicyCashValueBO;
import com.sinosoft.surrender.cashvalue.dao.LpEdorAppSpecDAO;
import com.sinosoft.surrender.cashvalue.dao.YdCashValueRuleSpecDAO;
import com.sinosoft.surrender.cashvalue.dto.req.PolicyCashValueReqDTO;
import com.sinosoft.surrender.cashvalue.dto.res.PolicyCashValueResDTO;
import com.sinosoft.surrender.cashvalue.service.CalPolicyCashValueService;
import com.sinosoft.surrender.cashvalue.service.SurrenderTradeTraceService;
import com.sinosoft.surrender.common.cache.RedisCacheBO;
import com.sinosoft.surrender.common.constant.ENUM_EDOR_TYPE;
import com.sinosoft.surrender.common.contant.Constant;
import com.sinosoft.surrender.common.contant.DateConstant;
import com.sinosoft.surrender.common.contant.surrenderenum.ENUM_TRACE_STATUS;
import com.sinosoft.surrender.common.exception.RulesCheckedException;
import com.sinosoft.surrender.common.exception.TradeException;
import com.sinosoft.surrender.common.lock.bo.LockBO;
import com.sinosoft.surrender.common.util.DateUtil;
import com.sinosoft.surrender.common.util.ExceptionUtil;
import com.sinosoft.surrender.db.model.LpEdorApp;
import com.sinosoft.surrender.db.model.YcPolicyCashValue;
import com.sinosoft.surrender.db.model.YdCashValueRule;

/**
 * 
 * 计算时刻现价接口
 * 
 * @author: wangwl_sinosoft
 * @date: 2018-3-8-下午2:16:04
 * @version:
 */
@Service
public class CalPolicyCashValueServiceImpl implements CalPolicyCashValueService {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private CalPolicyCashValueBO calPolicyCashValueBO;
	@Autowired
	private SurrenderTradeTraceService surrenderTradeTraceService;
	@Autowired
	private LockBO lockBO;
	@Autowired
	private SurrenderSaveDataBO surrenderSaveDataBO;
	@Autowired
	private LpEdorAppSpecDAO lpEdorAppSpecDAO;
	@Autowired
	private RedisCacheBO redisCacheBO;
	@Autowired
	private YcPolicyCashValueBO ycPolicyCashValueBO;
	@Autowired
	private YdCashValueRuleSpecDAO ydCashValueRuleSpecDAO;
	@Autowired
	private CallCoreCalCashValueBO callCoreCalCashValueBO;
	@Autowired
	private DealInterestMaxEndDateBO dealInterestMaxEndDateBO;

	@Override
	public PolicyCashValueResDTO calPolicyCashValue(PolicyCashValueReqDTO policyCashValueReqDTO) {
		long currentTime = System.currentTimeMillis();
		PolicyCashValueResDTO policyCashValueResDTO = fillPolicyCashValueResDTO(policyCashValueReqDTO);
		//logger.info("计算保单现价请求参数:/n{}", XmlUtil.toXml(policyCashValueReqDTO));
		String lockType = Constant.CALCASHVALUELOCKTYPE;
		String loker = Constant.CALCASHVALUELOCKER;
		StringBuffer sbf = new StringBuffer();
		sbf.append(policyCashValueReqDTO.getEdorType());
		sbf.append("|");
		sbf.append(policyCashValueReqDTO.getContNo());
		sbf.append("|");
		sbf.append(DateUtil.getDateStr(policyCashValueReqDTO.getApplyDate(), DateConstant.DATE_FORMAT_1));
		String key = sbf.toString();
		// 加锁，
		logger.debug("====>加锁中...<====");
		try {
			lockBO.lock(lockType, key, loker);
			logger.info("加锁耗时："+(System.currentTimeMillis()-currentTime)+"毫秒");
		} catch (Exception e1) {
			logger.error("保单时刻现价发生非预期异常:{}", e1);
			policyCashValueResDTO.setResultMsg("计算时刻现价发生非预期异常，请核实!");
			policyCashValueResDTO.setResultStatus(ENUM_TRACE_STATUS.FAIL.getValue());
			return policyCashValueResDTO;
		}
		BigDecimal sid = BigDecimal.ZERO;
		try {
			long start = System.currentTimeMillis();
			logger.info("====>流水号为：{}，保单号为{}，计算保单现价<====", policyCashValueReqDTO.getTransNo(),
					policyCashValueReqDTO.getContNo());
			// 1.保存日志
			sid = surrenderTradeTraceService.createTradeTrace(policyCashValueReqDTO);
			// 2.入参的非空校验
			policyCashValueReqParamCheck(policyCashValueReqDTO);
			logger.info("加锁、保存日志耗时："+(System.currentTimeMillis()-start)+"毫秒");
			// 是否为非支持产品
			boolean isSupport = checkRiskCode(policyCashValueReqDTO.getContNo());
			// 3.幂等处理
			if (checkCalPolicyCashValue(key, policyCashValueResDTO, policyCashValueReqDTO, isSupport)) {
				return policyCashValueResDTO;
			}
			// 5.计算保单现价,②且推送到Redis中。
			logger.info("====>计算保单现价开始<====");
			// modify by jinwc 2019-07-08  lisbug-6580 所有产品的犹退支持在第三方退保平台进行现金价值计算
			if(!isSupport && !ENUM_EDOR_TYPE.WT.getValue().equals(policyCashValueReqDTO.getEdorType())){
				// 非支持产品， 调核心试算现价
				logger.info("====>非支持产品， 调核心试算现价");
				long callCoreStart = System.currentTimeMillis();
				policyCashValueResDTO = callCoreCalCashValueBO.calCashValue(policyCashValueReqDTO);
				logger.info("====>调核心计算耗时：{}毫秒",(System.currentTimeMillis()-callCoreStart));
				// 获取到结算利率表已维护的最大结束日期推送到redis中
				dealInterestMaxEndDateBO.dealInterestMaxEndDate(policyCashValueReqDTO.getContNo());
			}else{
				// 支持产品，使用本地试算现价
				logger.info("====>支持产品，使用本地试算现价");
				policyCashValueResDTO = calPolicyCashValueBO.calConstantlyPolicyCashValue(policyCashValueReqDTO);
			}
			
			// 推送到Redis中
			logger.info("====>将现价推送至Redis中<====");
			boolean putRedisCache = redisCacheBO.putRedisCache(key, policyCashValueResDTO.getCashValue().toString());
			if (!putRedisCache) {
				logger.info("key为：{}推送Redis数据失败，请核实！", key);
			}
			// 6.①保存中间表数据,
			long centerStart = System.currentTimeMillis();
			surrenderSaveDataBO.saveData(policyCashValueResDTO.getCashValue(), policyCashValueReqDTO);
			logger.info("保存中间表中耗时："+(System.currentTimeMillis()-centerStart)+"毫秒");
			policyCashValueResDTO.setResultStatus(ENUM_TRACE_STATUS.SUCCESS.getValue());
			policyCashValueResDTO.setResultMsg(ENUM_TRACE_STATUS.SUCCESS.getName());
		} catch (RulesCheckedException re) {
			logger.error("校验类异常：{}", re);
			policyCashValueResDTO.setResultMsg(re.getMessage());
			policyCashValueResDTO.setResultStatus(ENUM_TRACE_STATUS.FAIL.getValue());
		} catch (TradeException et) {
			logger.error("计算保单时刻现价发生异常：{}", et);
			policyCashValueResDTO.setResultMsg("计算保单时刻现价发生异常,"+ et.getMessage() + ",烦请核实！");
			policyCashValueResDTO.setResultStatus(ENUM_TRACE_STATUS.FAIL.getValue());
		} catch (Exception e) {
			logger.error("计算保单时刻现价发生非预期异常：{}", e);
			policyCashValueResDTO.setResultMsg("计算保单时刻现价发生非预期异常,烦请核实!");
			policyCashValueResDTO.setResultStatus(ENUM_TRACE_STATUS.FAIL.getValue());

		} finally {
			// 8.修改日志
			logger.info("现价返回参数:{}", XmlUtil.toXml(policyCashValueResDTO));
			try {
				surrenderTradeTraceService.updateTradeTrace(sid, currentTime, policyCashValueResDTO);
			} catch (Exception e) {
				logger.error("时刻现价发生非预期异常:{}", e);
				policyCashValueResDTO.setResultMsg("时刻现价发生非预期异常，请核实!");
				policyCashValueResDTO.setResultStatus(ENUM_TRACE_STATUS.FAIL.getValue());
			}
			
			try {
				lockBO.unLock(lockType, key);
				logger.info("====>解锁成功...<====");
			} catch (Exception e) {
				logger.error("时刻现价发生非预期异常，解锁失败:{}", e);
				policyCashValueResDTO.setResultMsg("时刻现价发生非预期异常，请核实!");
				policyCashValueResDTO.setResultStatus(ENUM_TRACE_STATUS.FAIL.getValue());
			}
			
			
		}
		logger.info("计算时刻现价耗时：{}毫秒", System.currentTimeMillis() - currentTime);
		return policyCashValueResDTO;
	}

	/**
	 * 入参非空校验
	 * 
	 * @param policyCashValueReqDTO
	 */
	private void policyCashValueReqParamCheck(PolicyCashValueReqDTO policyCashValueReqDTO) {
		ExceptionUtil.checkEmpty(policyCashValueReqDTO.getTransNo(), "试算流水号不能为空!");
		ExceptionUtil.checkEmpty(policyCashValueReqDTO.getZoneNo(), "地区码不能为空！");
		ExceptionUtil.checkEmpty(policyCashValueReqDTO.getBrNo(), "网点码不能为空!");
		//ExceptionUtil.checkEmpty(policyCashValueReqDTO.getTellerNo(), "操作人员不能空!");//SEQ-9483-银保通柜面退保功能优化（农业银行）_xululu
		ExceptionUtil.checkEmpty(policyCashValueReqDTO.getBankCode(), "银行编码不能为空!");
		ExceptionUtil.checkEmpty(policyCashValueReqDTO.getContNo(), "保单号不能为空!");
		ExceptionUtil.checkEmpty(policyCashValueReqDTO.getApalyPlatform(), "保全申请平台不能为空!");
		ExceptionUtil.checkNull(policyCashValueReqDTO.getApplyDate(), "保全申请日期不能为空!");
		// 后续平台类型写成枚举
		ExceptionUtil.checkEmpty(policyCashValueReqDTO.getSourceType(), "渠道编码不能为空!");
		ExceptionUtil.checkNull(policyCashValueReqDTO.getEdorType(), "保全类型不能为空!");
	}

	/**
	 * 幂等校验
	 * 
	 * @history: 2018-4-7
	 * @author: wangwl_sinosoft
	 * @param policyCashValueReqDTO
	 * @return
	 */
	private boolean checkCalPolicyCashValue(String key, PolicyCashValueResDTO policyCashValueResDTO,
			PolicyCashValueReqDTO policyCashValueReqDTO, boolean isSupport) {
		long Start = System.currentTimeMillis();
		List<LpEdorApp> lpEdorAppList = lpEdorAppSpecDAO.getLpEdorAppByContNo(policyCashValueReqDTO.getContNo());
		if (CollectionUtils.isNotEmpty(lpEdorAppList)) {
			ycPolicyCashValueBO.updateYcPolicyCashValueByKey(policyCashValueReqDTO);
			logger.info("幂等校验耗时："+(System.currentTimeMillis()-Start)+"毫秒");
			return false;
		}
		// 非支持产品需校验是否维护新的利率
		if(!isSupport){
			// 校验是否维护新的结算利率
			if(dealInterestMaxEndDateBO.checkIsNewinterest(policyCashValueReqDTO.getContNo(), policyCashValueReqDTO.getApplyDate())){
				// 有维护新的利率，需重新计算现价
				logger.info("有维护新的利率，需重新计算现价!");
				return false;
			}
		}
		
		// 3.查询redis ,key = 计算日期+保单号
		// ①计算先查询Redis，若有对应计算的现价，直接返回
		BigDecimal value = redisCacheBO.getValueOfRedis(key);
		if (Constant.NEGATIVE_ONE.compareTo(value) != 0) {
			policyCashValueResDTO.setCashValue(value);
			logger.info("幂等校验耗时："+(System.currentTimeMillis()-Start)+"毫秒");
			return true;
		}
		List<YcPolicyCashValue> ycPolicyCashValue = ycPolicyCashValueBO.getYcPolicyCashValue(policyCashValueReqDTO);
		if (CollectionUtils.isNotEmpty(ycPolicyCashValue)) {
			value = ycPolicyCashValue.get(0).getCashvalue();
			policyCashValueResDTO.setCashValue(value);
			// 重新推缓存
			redisCacheBO.putRedisCache(key, value.toString());
			logger.info("幂等校验耗时："+(System.currentTimeMillis()-Start)+"毫秒");
			return true;
		}
		logger.info("幂等校验耗时："+(System.currentTimeMillis()-Start)+"毫秒");
		return false;

	}

	/**
	 * 
	 * 填充试算的返回DTO
	 * 
	 * @history: 2018-4-7
	 * @author: wangwl_sinosoft
	 * @param cashVlue
	 * @param policyCashValueResDTO
	 * @param policyCashValueReqDTO
	 * @return
	 */
	private PolicyCashValueResDTO fillPolicyCashValueResDTO(PolicyCashValueReqDTO policyCashValueReqDTO) {
		PolicyCashValueResDTO policyCashValueResDTO = new PolicyCashValueResDTO();
		policyCashValueResDTO.setContNo(policyCashValueReqDTO.getContNo());
		policyCashValueResDTO.setTransNo(policyCashValueReqDTO.getTransNo());
		policyCashValueResDTO.setResultStatus(ENUM_TRACE_STATUS.REPEAT.getValue());
		policyCashValueResDTO.setResultMsg(ENUM_TRACE_STATUS.REPEAT.getName());
		return policyCashValueResDTO;
	}
	
	/**
	 * 校验产品险种
	 * 
	 * @param checkSurrenderDTO
	 * @return
	 */
	private boolean checkRiskCode(String contno) {
		long centerStart = System.currentTimeMillis();
		YdCashValueRule ydCashValueRule = ydCashValueRuleSpecDAO.getByTraceContno(contno);
		if (ydCashValueRule == null) {
			// 非支持产品
			logger.info("校验产品险种耗时："+(System.currentTimeMillis()-centerStart)+"毫秒");
			return false;
		}
		// 支持产品
		return true;
	}

}
