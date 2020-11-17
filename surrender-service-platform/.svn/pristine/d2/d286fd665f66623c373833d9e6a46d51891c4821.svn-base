package com.sinosoft.surrender.surrconfirm.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinosoft.platform.common.api.util.XmlUtil;
import com.sinosoft.surrender.common.check.CheckSurrenderBO;
import com.sinosoft.surrender.common.check.dto.CheckSurrenderDTO;
import com.sinosoft.surrender.common.constant.ENUM_PROCESS_STATUS_CODE;
import com.sinosoft.surrender.common.contant.Constant;
import com.sinosoft.surrender.common.contant.lisenum.ENUM_LIS_EDORNO_TYPE;
import com.sinosoft.surrender.common.contant.surrenderenum.ENUM_EDOR_STATUS;
import com.sinosoft.surrender.common.contant.surrenderenum.ENUM_TRACE_STATUS;
import com.sinosoft.surrender.common.exception.LockFailedException;
import com.sinosoft.surrender.common.exception.RulesCheckedException;
import com.sinosoft.surrender.common.lock.bo.LockBO;
import com.sinosoft.surrender.common.notype.bo.GetEdorNoBO;
import com.sinosoft.surrender.common.util.ExceptionUtil;
import com.sinosoft.surrender.db.dao.LcContDAO;
import com.sinosoft.surrender.db.model.LcCont;
import com.sinosoft.surrender.db.model.LpEdorItem;
import com.sinosoft.surrender.db.model.YcSurrender;
import com.sinosoft.surrender.surrconfirm.bo.GetSurrenderMoneyBO;
import com.sinosoft.surrender.surrconfirm.bo.SurrenderConfirmBO;
import com.sinosoft.surrender.surrconfirm.dao.LpEdorItemSpecDAO;
import com.sinosoft.surrender.surrconfirm.dto.req.SurrenderConfirmReqDTO;
import com.sinosoft.surrender.surrconfirm.dto.res.SurrenderConfirmResDTO;
import com.sinosoft.surrender.surrconfirm.service.SurrenderConfirmService;
import com.sinosoft.surrender.surrconfirm.service.SurrenderTraceService;

/**
 * 
 * 退保确认接口
 * 
 * @author: zhongdw_sinosoft
 * @date: 2018年4月4日-下午4:58:45
 * @version:
 */
@Service
public class SurrenderConfirmServiceImpl implements SurrenderConfirmService {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private SurrenderConfirmBO surrenderConfirmBO;
	@Autowired
	private SurrenderTraceService surrenderTraceService;
	@Autowired
	private GetSurrenderMoneyBO getSurrenderMoneyBO;
	@Autowired
	private LcContDAO lcContDAO;
	@Autowired
	private GetEdorNoBO getEdorNoBO;
	@Autowired
	private CheckSurrenderBO checkSurrenderBO;
	@Autowired
	private LockBO lockBO;
	@Autowired
	private LpEdorItemSpecDAO lpEdorItemSpecDAO;
	@Override
	public SurrenderConfirmResDTO confirmSurrender(SurrenderConfirmReqDTO surrenderConfirmReqDTO) {
		long confirmStartTime = System.currentTimeMillis();
		SurrenderConfirmResDTO surrenderConfirmResDTO = new SurrenderConfirmResDTO();
		String lockType = Constant.SURRENDERCONFIRMLOCKTYPE;
		String loker = Constant.SURRENDERCONFIRMLOCKER;
		StringBuffer sbf = new StringBuffer();
		sbf.append(surrenderConfirmReqDTO.getEdorType());
		sbf.append("|");
		sbf.append(surrenderConfirmReqDTO.getContNo());
		String key = sbf.toString();
		// 加锁，
		logger.debug("====>加锁中...<====");
		try {
			lockBO.lock(lockType, key, loker);
		} catch (LockFailedException e1) {
			logger.error("加锁失败：{}", e1);
			surrenderConfirmResDTO.setResultStatus(ENUM_TRACE_STATUS.FAIL.getValue());
			surrenderConfirmResDTO.setResultMsg("保单正在保全处理中！");
			return surrenderConfirmResDTO;
		}

		BigDecimal surrenderTraceSid = BigDecimal.ZERO;
		try {
			// 封装返回参数
			surrenderConfirmResDTO = fillSurrenderConfirmResDTO( surrenderConfirmReqDTO,surrenderConfirmResDTO);
			// 保存日志
			surrenderTraceSid = surrenderTraceService.saveSurrenderTrace(surrenderConfirmReqDTO);
			// 参数的非空校验。
			logger.info("====>参数非空校验：\n{}", XmlUtil.toXml(surrenderConfirmReqDTO));
			confirmSurrenderParamCheck(surrenderConfirmReqDTO);
			
			// 幂等操作
			logger.info("====>幂等操作...<====");
			String edorAcceptno = checkSurrenderConfirm(surrenderConfirmReqDTO.getContNo());
			if (!Constant.UNREPEAT.equals(edorAcceptno)) {
				// 重复交易
				logger.info("====>重复交易...<====");
				surrenderConfirmResDTO.setEdorAcceptNo(edorAcceptno);
				surrenderConfirmResDTO.setResultStatus(ENUM_TRACE_STATUS.FAIL.getValue());
				surrenderConfirmResDTO.setResultMsg("保单正在保全处理中！");
				return surrenderConfirmResDTO;
			}
			
			// 规则校验
			logger.info("====>规则校验...<====");
			CheckSurrenderDTO checkSurrenderDTO = fillCheckSurrenderDTO(surrenderConfirmReqDTO);
			checkSurrenderBO.checkSurrender(checkSurrenderDTO);

			// 获取退保金
			logger.debug("获取退保金，保单号：{}", surrenderConfirmReqDTO.getContNo());
			BigDecimal policyCashValue = getSurrenderMoneyBO.getSurrenderMoney(surrenderConfirmReqDTO);

			// 保存数据
			logger.info("====>保存到退保中间表中...<====");
			// 保全受理号
			String edoracceptno = surrenderConfirmResDTO.getEdorAcceptNo();
			surrenderConfirmBO.saveSurrender(surrenderConfirmReqDTO, policyCashValue, edoracceptno);
			// 退保确认成功
			logger.info("====>确认退保成功...<====");
			surrenderConfirmResDTO.setResultStatus(ENUM_TRACE_STATUS.SUCCESS.getValue());
			surrenderConfirmResDTO.setResultMsg(ENUM_TRACE_STATUS.SUCCESS.getName());
		} catch (RulesCheckedException rce) {
			// 非空校验出现异常，规则类校验
			logger.error("校验出现异常:{}", rce);
			surrenderConfirmResDTO.setResultStatus(ENUM_TRACE_STATUS.FAIL.getValue());
			String resultMsg = rce.getMessage();
			if (resultMsg.length() > 500) {
				resultMsg = resultMsg.substring(0, 500);
			}
			surrenderConfirmResDTO.setResultMsg(resultMsg);
		} catch (Exception e) {
			// 出现异常，退保不成功
			logger.error("退保确认出现非预期异常:{}", e);
			surrenderConfirmResDTO.setResultStatus(ENUM_TRACE_STATUS.FAIL.getValue());
			surrenderConfirmResDTO.setResultMsg("退保确认出现非预期异常！");
		} finally {
			try {
				// 更新交易日志
				logger.info("====>更新交易日志...<====");
				surrenderTraceService.updateSurrenderTrace(surrenderConfirmResDTO, surrenderTraceSid, confirmStartTime);
			} catch (Exception e) {
				logger.error("退保确认出现非预期异常:{}", e);
				surrenderConfirmResDTO.setResultStatus(ENUM_TRACE_STATUS.FAIL.getValue());
				surrenderConfirmResDTO.setResultMsg("退保确认出现非预期异常！");
			}
			
			try {
				// 解锁
				logger.info("====>解锁...<====");
				lockBO.unLock(lockType, key);
			} catch (Exception e) {
				logger.error("退保确认出现非预期异常:{}", e);
				surrenderConfirmResDTO.setResultStatus(ENUM_TRACE_STATUS.FAIL.getValue());
				surrenderConfirmResDTO.setResultMsg("退保确认出现非预期异常！");
			}

		}
		logger.info("退保确认返回参数:\n{},\n退保确认耗时：{}毫秒", XmlUtil.toXml(surrenderConfirmResDTO),
				System.currentTimeMillis() - confirmStartTime);
		return surrenderConfirmResDTO;
	}

	/**
	 * 
	 * 填充校验数据
	 * 
	 * @history: 2018年4月9日
	 * @author: zhongdw_sinosoft
	 * @param surrenderConfirmReqDTO
	 * @return
	 */
	private CheckSurrenderDTO fillCheckSurrenderDTO(SurrenderConfirmReqDTO surrenderConfirmReqDTO) {
		CheckSurrenderDTO checkSurrenderDTO = new CheckSurrenderDTO();
		checkSurrenderDTO.setContNo(surrenderConfirmReqDTO.getContNo());
		checkSurrenderDTO.setApplyDate(surrenderConfirmReqDTO.getConfirmDate());
		checkSurrenderDTO.setEdorType(surrenderConfirmReqDTO.getEdorType());
		checkSurrenderDTO.setTransNo(surrenderConfirmReqDTO.getTransNo());
		return checkSurrenderDTO;
	}

	/**
	 * 参数非空校验
	 * 
	 * @param surrenderConfirmReqDTO
	 */
	public void confirmSurrenderParamCheck(SurrenderConfirmReqDTO surrenderConfirmReqDTO) {
		// 请求参数不为空
		ExceptionUtil.checkNull(surrenderConfirmReqDTO, "请求参数不能为空！");
		// 参数必传属性不为空
		ExceptionUtil.checkEmpty(surrenderConfirmReqDTO.getTransNo(), "退保确认流水号不能为空！");
		ExceptionUtil.checkEmpty(surrenderConfirmReqDTO.getContNo(), "保单号不能为空！");
		ExceptionUtil.checkEmpty(surrenderConfirmReqDTO.getZoneNo(), "地区码不能为空！");
		ExceptionUtil.checkEmpty(surrenderConfirmReqDTO.getBrNo(), "网点码不能为空！");
		//ExceptionUtil.checkEmpty(surrenderConfirmReqDTO.getTellerNo(), "操作人员不能为空！");//SEQ-9483-银保通柜面退保功能优化（农业银行）_xululu
		ExceptionUtil.checkEmpty(surrenderConfirmReqDTO.getBankCode(), "银行编码不能为空！");
		ExceptionUtil.checkNull(surrenderConfirmReqDTO.getConfirmDate(), "确认日期不能为空！");
		ExceptionUtil.checkEmpty(surrenderConfirmReqDTO.getApplyPlatform(), "申请平台不能为空！");
		ExceptionUtil.checkEmpty(surrenderConfirmReqDTO.getBankAccNo(), "银行账号不能为空！");
		//modify by jinwc 2019-7-29 lisbug-6700 银行账户名校验排除建行  
		//modify by yzf 20201030 银行账户名校验排除上海银行  
		if(!"03".equals(surrenderConfirmReqDTO.getBankCode())&&!"91".equals(surrenderConfirmReqDTO.getBankCode())&&!"11".equals(surrenderConfirmReqDTO.getBankCode())) {
			ExceptionUtil.checkEmpty(surrenderConfirmReqDTO.getBankAccName(), "银行账户名不能为空！");
		}		
		ExceptionUtil.checkEmpty(surrenderConfirmReqDTO.getFunctionFlag(), "交易类型不能为空！");
	}

	/**
	 * 封装返回参数
	 * 
	 * @param surrenderConfirmReqDTO
	 * @return
	 */
	public SurrenderConfirmResDTO fillSurrenderConfirmResDTO(SurrenderConfirmReqDTO surrenderConfirmReqDTO,
			SurrenderConfirmResDTO surrenderConfirmResDTO) {

		// 封装属性
		ExceptionUtil.checkEmpty(surrenderConfirmReqDTO.getContNo(), "保单号不能为空！");
		surrenderConfirmResDTO.setContNo(surrenderConfirmReqDTO.getContNo());
		// 保全受理号
		LcCont lcCont = lcContDAO.selectByPrimaryKey(surrenderConfirmReqDTO.getContNo());
		// 非空校验
		ExceptionUtil.checkNull(lcCont, "查询合同的数据失败!");
		String limitNo = lcCont.getManagecom();// 管理机构
		String edorNo = ENUM_LIS_EDORNO_TYPE.EDORACCEPTNO.getValue();// 保全受理号-90488
		String edoracceptno = getEdorNoBO.getEdorNo(limitNo, edorNo);
		surrenderConfirmResDTO.setEdorAcceptNo(edoracceptno);
		ExceptionUtil.checkEmpty(surrenderConfirmReqDTO.getEdorType(), "保全类型不能为空！");
		surrenderConfirmResDTO.setEdorType(surrenderConfirmReqDTO.getEdorType());
		// 返回属性
		return surrenderConfirmResDTO;
	}

	/**
	 * 
	 * 幂等校验： ① 若退保中间表有数据，且审批状态未被拒绝时，在退保确认为重复确认。
	 * ②若退保中间表有数据，保全项目表为空，且导入核心失败或者待导入时，在退保确认为重复确认。
	 *
	 * @history: 2018年5月14日
	 * @author: wangwl_sinosoft
	 * @param contNo
	 * @return
	 */
	private String checkSurrenderConfirm(String contNo) {
		YcSurrender ycSurrender = surrenderConfirmBO.findSurrenderByContNo(contNo);
		if (ycSurrender == null) {
			return Constant.UNREPEAT;
		}
		String edorAcceptno = ycSurrender.getEdoracceptno();
		ExceptionUtil.checkEmpty(edorAcceptno, "保全受理号不能为空！");
		// 导入核心失败时,或者待导入时
		if (ENUM_PROCESS_STATUS_CODE.FAIL.getValue().equals(ycSurrender.getProcessStatus())
				|| ENUM_PROCESS_STATUS_CODE.PENDING.getValue().equals(ycSurrender.getProcessStatus())) {
			return edorAcceptno;
		}
		List<LpEdorItem> lpEdorItemInfo = lpEdorItemSpecDAO.getLpEdorItemInfo(edorAcceptno);
		// 若保全受理进审批，且审批未被拒绝时
		if (CollectionUtils.isNotEmpty(lpEdorItemInfo)
				&& (!ENUM_EDOR_STATUS.EDOR_REFUSE.getValue().equals(lpEdorItemInfo.get(0).getEdorstate()))) {
			return edorAcceptno;
		}
		surrenderConfirmBO.updateYcSurrenderByKey(ycSurrender);
		return Constant.UNREPEAT;
	}
	
}
