package com.sinosoft.surrender.cashvalue.bo.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.guohualife.platform.common.api.util.CollectionUtil;
import com.sinosoft.surrender.cashvalue.dao.LcPolSpecDAO;
import com.sinosoft.surrender.cashvalue.dao.SurrenderCheckBaseSpecDAO;
import com.sinosoft.surrender.common.check.abs.AbsCheckSurrenderBO;
import com.sinosoft.surrender.common.check.dto.CheckSurrenderDTO;
import com.sinosoft.surrender.common.constant.ENUM_EDOR_TYPE;
import com.sinosoft.surrender.common.contant.Constant;
import com.sinosoft.surrender.common.contant.lisenum.ENUM_CONT_EDOR_TYPE;
import com.sinosoft.surrender.common.contant.lisenum.ENUM_CONT_POLSTART;
import com.sinosoft.surrender.common.contant.surrenderenum.ENUM_YES_NO_STATUS;
import com.sinosoft.surrender.common.exception.RulesCheckedException;
import com.sinosoft.surrender.common.util.DateUtil;
import com.sinosoft.surrender.common.util.ExceptionUtil;
import com.sinosoft.surrender.db.dao.LcContDAO;
import com.sinosoft.surrender.db.dao.LdLockDAO;
import com.sinosoft.surrender.db.model.LcCont;
import com.sinosoft.surrender.db.model.LcPol;
import com.sinosoft.surrender.db.model.LdCode;
import com.sinosoft.surrender.db.model.LdLock;
import com.sinosoft.surrender.db.model.LdLockKey;
import com.sinosoft.surrender.db.model.LdSysTrace;
import com.sinosoft.surrender.db.model.LjsPay;
import com.sinosoft.surrender.db.model.LpEdorItem;
import com.sinosoft.surrender.db.model.LpFreeze;

@Component
public class CheckSurrenderBOImpl extends AbsCheckSurrenderBO {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private SurrenderCheckBaseSpecDAO surrenderCheckBaseSpecDAO;
	@Autowired
	private LcPolSpecDAO lcPolSpecDAO;
	@Autowired
	private LdLockDAO ldLockDAO;
	@Autowired
	private LcContDAO lcContDAO;

	@Override
	public void checkSurrender(CheckSurrenderDTO checkSurrenderDTO) {
		long timeStart = System.currentTimeMillis();
		String contNo = checkSurrenderDTO.getContNo();// 保单号
		Date applyDate = checkSurrenderDTO.getApplyDate();// 申请日期
		String edorType = checkSurrenderDTO.getEdorType();// 保全类型
		String transNo = checkSurrenderDTO.getTransNo();//交易流水号
		// 校验受理状态
		checkEdorAcceptNo(contNo,transNo);
		// 校验是否在理赔状态中
		checkClaim(contNo);
		// 校验保单是否存在未还清贷款
		checkLoloanCount(contNo);
		// 校验是否过犹豫期
		checkExceedHesitate(contNo, applyDate, edorType);
		// 保单续期收费银行途中校验
		checkBankOnTheWay(contNo);
		// 校验保单冻结止付状态
		checkFreeze(contNo);
		// 校验保单是否处于钱袋冻结
		checkQDFreeze(contNo);
		// 保单是否在淘宝进行冻结
		checkTBFreeze(contNo);
		// 校验保单冻结状态
		checkFreezeState(contNo, edorType);
		// 是否处于银行转账
		checkSendToBank(contNo);
		// 保单挂失校验
		checkContReportLoss(contNo);
		// 死亡报案理赔校验
		checkReport(contNo);
		// 校验保单相关状态
		checkContStatus(contNo);
		// 校验有到期尚未派发的生存金或红利
		checkHasAliveGet(applyDate, contNo, edorType);
		// 服务类交易统一新增校验
		checkAtOtherPEdor(contNo, applyDate);
		long timeEnd = System.currentTimeMillis();
		logger.info("保单号：{},，----end----,校验总耗时：{}", contNo, (timeEnd - timeStart));

	}
	
	

	/**
	 * 
	 * 是否存在其他业务，存在则受理失败
	 * 
	 * @history: 2018-4-8
	 * @author: wangwl_sinosoft
	 * @param contNo
	 * @param transNo 
	 */
	private void checkEdorAcceptNo(String contNo, String transNo) {
		// 1. 查询lpedorapp表校验
		// 1.1 是否存在其他业务，存在则受理失败
		// 1.2 是否存在当前单申请保全，存在则受理失败
		// 根据保单号查询保全受理号是否存在
		String edorAcceptNo = surrenderCheckBaseSpecDAO.getEdorAcceptNo(contNo);
		
//		ExceptionUtil.checkNotEmpty(edorAcceptNo, "该保单正在进行其他保全业务，不可操作!");
		
		ExceptionUtil.checkNotEmpty(edorAcceptNo, "您的保单已在其他渠道受理了保全业务("+edorAcceptNo+")["+transNo+",E047]");
	}

	/**
	 * 
	 * 校验是否在理赔状态中
	 * 
	 * @history: 2018-4-8
	 * @author: wangwl_sinosoft
	 * @param contNo
	 */
	private void checkClaim(String contNo) {
		// 2.2 校验是否在理赔状态中，在理赔中则受理失败llcase
		// 根据保单号获取处在理赔状态中的保单个数
		BigDecimal claimCount = surrenderCheckBaseSpecDAO.getClaimCount(contNo);
		ExceptionUtil.checkBigDecimal(claimCount, "该保单正在进行理赔业务，受理失败！");
	}

	/**
	 * 
	 * 校验保单是否存在未还清贷款
	 * 
	 * @history: 2018-4-8
	 * @author: wangwl_sinosoft
	 * @param contNo
	 */
	private void checkLoloanCount(String contNo) {
		// 2.3 保单是否存在未还清贷款，存在则受理失败
		// 根据保单号查询未还清贷款的个数
		BigDecimal loloanCount = surrenderCheckBaseSpecDAO.getLoloanCount(contNo);
		ExceptionUtil.checkBigDecimal(loloanCount, "当前保单未还清借款，受理失败！");
	}

	/**
	 * 
	 * 校验保单冻结止付状态,保全处于冻结止付状态，保全项目仅可受理付费变更(CC)，客户资料变更(AC)，联系方式变更(AH)，质押解除(BD)
	 * 
	 * @history: 2018-4-7
	 * @author: wangwl_sinosoft
	 * @param contNo
	 */
	private void checkFreeze(String contNo) {
		LdSysTrace ldsystrace = new LdSysTrace();
		ldsystrace.setPolno(contNo);
		ldsystrace.setPolstate(Constant.BANKLOAN);// 保单冻结类型
		ldsystrace.setValiflag(ENUM_YES_NO_STATUS.YES.getValue());// 轨迹有效标志
		List<LdSysTrace> ldsystraceInfo = surrenderCheckBaseSpecDAO.getLdsystraceInfo(ldsystrace);
		ExceptionUtil.checkCollNotEmpty(ldsystraceInfo, "此保单由于银行质押贷款处于冻结止付状态，不能操作该保全项目！");
	}

	/**
	 * 
	 * 校验保单冻结状态,
	 * 
	 * @history: 2018-4-7
	 * @author: wangwl_sinosoft
	 * @param contNo
	 * @param edorType
	 */
	private void checkFreezeState(String contNo, String edorType) {
		LpFreeze lpFreeze = new LpFreeze();
		lpFreeze.setContno(contNo);
		// 保单冻结状态
		lpFreeze.setFreezestate(ENUM_YES_NO_STATUS.YES.getValue());
		List<LpFreeze> lpFreezeList = surrenderCheckBaseSpecDAO.getLpFreezeInfo(lpFreeze);

		if (CollectionUtils.isEmpty(lpFreezeList)) {
			return;
		}
		LdCode ldcode = new LdCode();
		ldcode.setCodetype(Constant.EDORTYPE);
		ldcode.setCode(edorType);
		List<LdCode> ldCodeInfo = surrenderCheckBaseSpecDAO.getLdCodeInfo(ldcode);
		ExceptionUtil.checkCollNotEmpty(ldCodeInfo, "保单冻结状态，无法操作其他保全项目!");

	}

	/**
	 * 
	 * 保单挂失校验
	 * 
	 * @history: 2018-4-7
	 * @author: wangwl_sinosoft
	 * @param contNo
	 * @return
	 */
	private void checkContReportLoss(String contNo) {
		List<LpEdorItem> itemList = surrenderCheckBaseSpecDAO.getLpedoritemInfo(contNo);
		if (CollectionUtil.isEmpty(itemList)) {
			return;
		}
		int dist = 0;
		// 判断该保单最终究竟是挂失还是解除
		for (LpEdorItem lpedoritem : itemList) {
			String edorType = lpedoritem.getEdortype().toUpperCase();
			if (ENUM_CONT_EDOR_TYPE.CONTLOSS.getShortName().equals(edorType)) {// 挂失
				dist++;
			} else if (ENUM_CONT_EDOR_TYPE.CONTREMOVE.getShortName().equals(edorType)) {// 解除
				dist--;
			}
		}
		if (dist > 0) {
			throw new RulesCheckedException("保单已经挂失，不能进行此保全操作!");
		}

	}

	/**
	 * 
	 * 保单状态相关校验
	 * 
	 * @history: 2018-3-24
	 * @author: wangwl_sinosoft
	 * @param contNo
	 */
	private void checkContStatus(String contNo) {
		// 第一步：获取lcpol信息
		List<LcPol> lcPolList = lcPolSpecDAO.getLcPolByContNo(contNo);
		ExceptionUtil.checkCollEmpty(lcPolList, "此保单号无险种表信息，请核实！");
		for (LcPol lcpol : lcPolList) {
			String polState = lcpol.getPolstate();
			ExceptionUtil.checkEmpty(polState, "保单状态不能为空！");
			// 1 校验polstate以"0303"开头--保单满期状态
			ExceptionUtil.checkStartWith(polState, ENUM_CONT_POLSTART.FULLTIME.getValue(), "保单处于满期终止状态，不能添加此保全项目!");
			// 2 校验polstate以"0302"开头--保单处于永久失效状态
			ExceptionUtil.checkStartWith(polState, ENUM_CONT_POLSTART.NEVERDO.getValue(), "保单处于永久失效状态，不能添加此保全项目!");
			// 3 校验polstate以"0310"开头--保单处于理赔责任终止状态
			ExceptionUtil.checkStartWith(polState, ENUM_CONT_POLSTART.CLAIMSTOP.getValue(), "保单处于理赔责任终止状态，不能添加此保全项目!");
			// 4 校验polstate以"0304"开头--保单处于解约终止状态
			ExceptionUtil.checkStartWith(polState, ENUM_CONT_POLSTART.RECOMMENCE.getValue(), "保单处于解约终止状态，不能添加此保全项目!");
			// 校验polstate以02开头的
			ExceptionUtil.checkStartWith(polState, Constant.HEAD_02, "保单处于停效状态，不能添加此保全项目!");
		}
	}

	/**
	 * 
	 * 查询保单是否已过犹豫期
	 * 
	 * @history: 2018-4-7
	 * @author: wangwl_sinosoft
	 * @param contNo
	 * @param edorAppDate
	 * @return true：已过犹豫期；false：未过犹豫期
	 */

	private void checkExceedHesitate(String contNo, Date edorAppDate, String edorType) {
		LcCont lcCont = lcContDAO.selectByPrimaryKey(contNo);
		if (lcCont.getCustomgetpoldate() == null) {
			// 客户回执日期为空
			return;
		}
		Date hesiDate = DateUtil.calDate(lcCont.getCustomgetpoldate(), Constant.YBTHESITATEPERIOD, DateUtil.DATE_TYPE_D,
				lcCont.getCustomgetpoldate());
		int interval = DateUtil.calIntervalOfAbandon(edorAppDate, hesiDate, DateUtil.DATE_TYPE_D);

		logger.info(
				"保单：" + contNo + "，保全申请日期：" + edorAppDate + "，犹豫期最后一天：" + hesiDate + "，保全申请日期与犹豫期最后一天的间隔：" + interval);
		if (ENUM_EDOR_TYPE.WT.getValue().equals(edorType)) {
			if (interval < 0) {
				throw new RulesCheckedException("该保单已过犹豫期，无法进行犹豫期退保！");
			}
			return;
		}

		if (interval >= 0) {
			throw new RulesCheckedException("该保单未过犹豫期，无法进行退保！");
		}
	}

	/**
	 * 
	 * 死亡报案理赔校验
	 * 
	 * @history: 2018-4-8
	 * @author: wangwl_sinosoft
	 * @param contNo
	 */
	private void checkReport(String contNo) {
		BigDecimal reportCount = surrenderCheckBaseSpecDAO.getReportCount(contNo);
		ExceptionUtil.checkBigDecimal(reportCount, "该被保人已报案死亡，无法进行保全操作！");

	}

	/**
	 * 
	 * 保单续期收费银行途中校验
	 * 
	 * @history: 2018-4-8
	 * @author: wangwl_sinosoft
	 * @param contNo
	 */
	private void checkBankOnTheWay(String contNo) {
		List<LjsPay> bankOnTheWay = surrenderCheckBaseSpecDAO.getBankOnTheWay(contNo);
		ExceptionUtil.checkCollNotEmpty(bankOnTheWay, "此保单续期收费银行在途中，不能进行此保全操作!");
	}

	/**
	 * 
	 * 校验保单是否在淘宝进行冻结
	 * 
	 * @history: 2018-4-8
	 * @author: wangwl_sinosoft
	 * @param contNo
	 */
	private void checkTBFreeze(String contNo) {
		LdSysTrace ldsystrace = new LdSysTrace();
		ldsystrace.setPolno(contNo);
		ldsystrace.setPolstate(Constant.EPAYLOAN);// 保单冻结类型
		ldsystrace.setValiflag(ENUM_YES_NO_STATUS.YES.getValue());// 轨迹有效标志
		List<LdSysTrace> ldsystraceInfo = surrenderCheckBaseSpecDAO.getLdsystraceInfo(ldsystrace);
		ExceptionUtil.checkCollNotEmpty(ldsystraceInfo, "该保单已在淘宝进行冻结，不允许在核心操作该保全项目!");
	}

	/**
	 * 
	 * 校验保单是否钱袋冻结中
	 * 
	 * @history: 2018-4-8
	 * @author: wangwl_sinosoft
	 * @param contNo
	 */
	private void checkQDFreeze(String contNo) {
		LdLockKey key = new LdLockKey();
		key.setLockey(contNo);
		key.setLocktype(Constant.DZSWHK);
		LdLock ldLock = ldLockDAO.selectByPrimaryKey(key);
		ExceptionUtil.checkNotNull(ldLock, "保单处于钱袋冻结中，无法操作其他保全项目!");
	}

	/**
	 * 
	 * 校验保单是否处于银行转账
	 * 
	 * @history: 2018-4-8
	 * @author: wangwl_sinosoft
	 * @param contNo
	 */
	private void checkSendToBank(String contNo) {
		BigDecimal lySendTobankCount = surrenderCheckBaseSpecDAO.getLySendTobankCount(contNo);
		ExceptionUtil.checkBigDecimal(lySendTobankCount, "当前保单处于银行转账，不能办理该项目！");
	}

	/**
	 * 
	 * 校验是否有到期尚未派发的生存金或红利
	 * 
	 * @history: 2018-4-11
	 * @author: zhongdw_sinosoft
	 * @param confirmDate
	 * @param contNo
	 * @param edorType
	 */
	private void checkHasAliveGet(Date confirmDate, String contNo, String edorType) {
		//modify by jinwc lisbug-7028 退保平台有到期尚未派发的生存金或红利校验需要调整 20191010 
		// 犹退时不进行此校验
		if(!ENUM_EDOR_TYPE.WT.getValue().equals(edorType)) {
			// 查询险种信息表
			List<LcPol> lcPolList = lcPolSpecDAO.getLcPolByContNo(contNo);
			ExceptionUtil.checkCollEmpty(lcPolList, "此保单号无险种表信息，请核实！");
			LcPol lcPol = lcPolList.get(0);
			// 查询有效标志
			int aliveFlag = surrenderCheckBaseSpecDAO.getAliveFlag(confirmDate, lcPol.getContno());
			if (1 == aliveFlag) {
				throw new RulesCheckedException(
						"有到期尚未派发的生存金或红利，请先做生存金/红利派发后再做" + ENUM_EDOR_TYPE.getNameByValue(edorType) + "！");
			}
		}
		
	}

	/**
	 * 
	 * 校验保单是否正在进行其他服务类业务
	 * 
	 * @history: 2018-4-11
	 * @author: zhongdw_sinosoft
	 * @param contNo
	 * @param tansDate
	 */
	private void checkAtOtherPEdor(String contNo, Date tansDate) {
		BigDecimal otherPE = surrenderCheckBaseSpecDAO.getOtherPEdor(contNo, tansDate);
		if (otherPE == null || otherPE.compareTo(BigDecimal.ZERO) == 0) {
			return;
		}
		if (otherPE.compareTo(BigDecimal.ZERO) != 0) {
			throw new RulesCheckedException("该保单正在进行其他服务类业务，不可操作!");
		}
	}
}
