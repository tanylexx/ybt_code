package com.sinosoft.surrender.cashvalue.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sinosoft.surrender.cashvalue.dao.SurrenderCheckBaseSpecDAO;
import com.sinosoft.surrender.common.contant.Constant;
import com.sinosoft.surrender.common.contant.lisenum.ENUM_CASE_CUST_STATUS;
import com.sinosoft.surrender.common.contant.lisenum.ENUM_CASE_RGT_STATUS;
import com.sinosoft.surrender.common.contant.lisenum.ENUM_CONT_EDOR_TYPE;
import com.sinosoft.surrender.common.contant.lisenum.ENUM_LIS_EDOR_STATUS;
import com.sinosoft.surrender.common.contant.lisenum.ENUM_OTHERNO_TYPE;
import com.sinosoft.surrender.common.contant.surrenderenum.ENUM_YES_NO_STATUS;
import com.sinosoft.surrender.db.base.SurrenderBaseDAOImpl;
import com.sinosoft.surrender.db.model.LdCode;
import com.sinosoft.surrender.db.model.LdSysTrace;
import com.sinosoft.surrender.db.model.LjsPay;
import com.sinosoft.surrender.db.model.LpEdorItem;
import com.sinosoft.surrender.db.model.LpFreeze;

@Repository
public class SurrenderCheckBaseSpecDAOImpl extends SurrenderBaseDAOImpl implements SurrenderCheckBaseSpecDAO {

	@Override
	public String getEdorAcceptNo(String contNo) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("edorState", ENUM_LIS_EDOR_STATUS.EDORCONFIRM.getValue());// 保全确认
		params.put("otherNoType", ENUM_OTHERNO_TYPE.PERSONALNO.getValue());// 个人客户
		params.put("tryFlag", ENUM_YES_NO_STATUS.YES.getValue());// 试算类保全
		params.put("contno", contNo);// 保单号
		Object obj = this.queryForObject("SURRENDERCHECKBASESPECDAO.getEdorAcceptNo", params);
		if (obj != null) {
			return (String) obj;
		}
		return null;

	}

	@Override
	public BigDecimal getClaimCount(String contNo) {
		List<String> rgtStateList = new ArrayList<String>();
		rgtStateList.add(ENUM_CASE_RGT_STATUS.DENYRECORD.getValue());// 15-不予立案
		rgtStateList.add(ENUM_CASE_RGT_STATUS.LAWSUIT.getValue());// 09-结案状态
		rgtStateList.add(ENUM_CASE_RGT_STATUS.REPORT.getValue());// 00-报案状态
		rgtStateList.add(ENUM_CASE_RGT_STATUS.DELIVERY.getValue());// 12-给付状态
		rgtStateList.add(ENUM_CASE_RGT_STATUS.REPEAL.getValue());// 14-撤件状态
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("rgtStateList", rgtStateList);
		params.put("contno", contNo);
		Object obj = this.queryForObject("SURRENDERCHECKBASESPECDAO.getClaimCount", params);
		if (obj != null) {
			return (BigDecimal) obj;
		}
		return null;
	}

	@Override
	public BigDecimal getLoloanCount(String contNo) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("payOffFlag", ENUM_YES_NO_STATUS.NO.getValue());// 1-还清 0-未还清
		params.put("contno", contNo);
		Object obj = this.queryForObject("SURRENDERCHECKBASESPECDAO.getLoloanCount", params);
		if (obj != null) {
			return (BigDecimal) obj;
		}
		return null;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<LdSysTrace> getLdsystraceInfo(LdSysTrace ldSysTrace) {
		List<LdSysTrace> ldSysTraces = this.queryForList("SURRENDERCHECKBASESPECDAO.getLdsystraceInfo", ldSysTrace);
		return ldSysTraces;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LpFreeze> getLpFreezeInfo(LpFreeze lpFreeze) {
		List<LpFreeze> lpFreezes = this.queryForList("SURRENDERCHECKBASESPECDAO.getLpFreezeInfo", lpFreeze);
		return lpFreezes;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LdCode> getLdCodeInfo(LdCode ldCode) {
		List<LdCode> ldCodes = this.queryForList("SURRENDERCHECKBASESPECDAO.getLdCodeInfo", ldCode);
		return ldCodes;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LpEdorItem> getLpedoritemInfo(String contNo) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("contloss", ENUM_CONT_EDOR_TYPE.CONTLOSS.getShortName());// 保单挂失
		params.put("contmove", ENUM_CONT_EDOR_TYPE.CONTREMOVE.getShortName());// 保单解除
		params.put("contno", contNo);
		List<LpEdorItem> lpEdorItemInfo = this.queryForList("SURRENDERCHECKBASESPECDAO.getLpedoritemInfo", params);
		return lpEdorItemInfo;
	}

	@Override
	public BigDecimal getReportCount(String contNo) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("custsituation", ENUM_CASE_CUST_STATUS.DEATH.getValue());// 事故者状况-死亡
		params.put("rgtReason", Constant.HEAD_01);
		params.put("contno", contNo);
		Object obj = this.queryForObject("SURRENDERCHECKBASESPECDAO.getReportCount", params);
		if (obj != null) {
			return (BigDecimal) obj;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LjsPay> getBankOnTheWay(String contNo) {

		List<LjsPay> ljspayList = this.queryForList("SURRENDERCHECKBASESPECDAO.getBankOnTheWay", contNo);
		return ljspayList;
	}

	@Override
	public BigDecimal getLySendTobankCount(String contNo) {
		Object obj = this.queryForObject("SURRENDERCHECKBASESPECDAO.getLySendTobankCount", contNo);
		if (obj != null) {
			return (BigDecimal) obj;
		}
		return null;
	}

	@Override
	public BigDecimal getCountOfEdorZT1ByRiskCode(String riskCode) {
		Object obj = this.queryForObject("SURRENDERCHECKBASESPECDAO.getCountOfEdorZT1ByRiskCode", riskCode);
		if(obj != null){
			return (BigDecimal)obj;
		}
		return null;
	}

	@Override
	public String getMaxStartDate(String polno) {
		Object obj = this.queryForObject("SURRENDERCHECKBASESPECDAO.getMaxStartDate", polno);
		if(obj != null){
			return (String)obj;
		}
		return null;
	}

	@Override
	public Integer getAliveFlag(Date tBaladate, String contNo) {
		Map<String , Object> map = new HashMap<String, Object>();
		map.put("tBaladate", tBaladate);
		map.put("contNo", contNo);
		Object obj = this.queryForObject("SURRENDERCHECKBASESPECDAO.getAliveFlag", map);
		if(obj != null){
			return (Integer)obj;
		}
		return 0;
	}

	@Override
	public String getEdorName(String edorType) {
		Object obj = this.queryForObject("SURRENDERCHECKBASESPECDAO.getEdorName", edorType);
		if(obj != null){
			return (String)obj;
		}
		return null;
	}

	@Override
	public BigDecimal getOtherPEdor(String contNo, Date tansDate) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("contNo", contNo);
		map.put("tansDate", tansDate);
		Object obj = this.queryForObject("SURRENDERCHECKBASESPECDAO.getOtherPEDor", map);
		if(obj != null){
			return (BigDecimal)obj;
		}
		return null;	}
}
