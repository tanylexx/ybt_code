package com.sinosoft.surrender.cashvalue.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.sinosoft.surrender.db.model.LdCode;
import com.sinosoft.surrender.db.model.LdSysTrace;
import com.sinosoft.surrender.db.model.LjsPay;
import com.sinosoft.surrender.db.model.LpEdorItem;
import com.sinosoft.surrender.db.model.LpFreeze;

public interface SurrenderCheckBaseSpecDAO {

	/**
	 * 
	 * 根据contno查询保全受理号
	 * 
	 * @history: 2018-3-24
	 * @author: wangwl_sinosoft
	 * @param contNo
	 * @return
	 */
	public String getEdorAcceptNo(String contNo);

	/**
	 * 
	 * 根据contno查询理赔个数
	 * 
	 * @history: 2018-3-24
	 * @author: wangwl_sinosoft
	 * @param contNo
	 * @return
	 */
	public BigDecimal getClaimCount(String contNo);

	/**
	 * 
	 * 根据contno查询未还清借款个数
	 * 
	 * @history: 2018-3-24
	 * @author: wangwl_sinosoft
	 * @param contNo
	 * @return
	 */
	public BigDecimal getLoloanCount(String contNo);

	/**
	 * 
	 * 根据保单冻结类型、轨迹有效标志和保单号获取处于冻结止付状态的记录
	 * 
	 * @history: 2018-4-7
	 * @author: wangwl_sinosoft
	 * @param ldSysTrace
	 * @return
	 */
	List<LdSysTrace> getLdsystraceInfo(LdSysTrace ldSysTrace);

	/**
	 * 
	 * 根据保单号和保单冻结状态查询处于冻结状态的记录
	 * 
	 * @history: 2018-4-7
	 * @author: wangwl_sinosoft
	 * @param lpFreeze
	 * @return
	 */
	List<LpFreeze> getLpFreezeInfo(LpFreeze lpFreeze);

	/**
	 * 
	 * 根据编码和编码类型获取公用的代码表信息
	 * 
	 * @history: 2018-4-7
	 * @author: wangwl_sinosoft
	 * @param ldCode
	 * @return
	 */
	List<LdCode> getLdCodeInfo(LdCode ldCode);

	/**
	 * 
	 * 根据保单号查询当前保单是否存在挂失处理或者解除处理
	 * 
	 * @history: 2018-4-7
	 * @author: wangwl_sinosoft
	 * @param contNo
	 * @return
	 */
	List<LpEdorItem> getLpedoritemInfo(String contNo);

	/**
	 * 
	 * 根据contno获取死亡报案个数
	 * 
	 * @history: 2018-4-8
	 * @author: wangwl_sinosoft
	 * @param contNo
	 * @return
	 */
	BigDecimal getReportCount(String contNo);

	/**
	 * 
	 * 查詢保单续期收费是否在银行途中
	 * 
	 * @history: 2018-4-8
	 * @author: wangwl_sinosoft
	 * @param contNo
	 * @return
	 */
	List<LjsPay> getBankOnTheWay(String contNo);

	/**
	 * 
	 * 根據保单号查询是否处于银行转账
	 * 
	 * @history: 2018-4-8
	 * @author: wangwl_sinosoft
	 * @param contNo
	 * @return
	 */
	BigDecimal getLySendTobankCount(String contNo);
	
	/**
	 * 	
	 * 根据险种获取数据 
	 *
	 * @history: 2018年4月9日
	 * @author: zhongdw_sinosoft
	 * @param riskCode
	 * @return
	 */
	BigDecimal getCountOfEdorZT1ByRiskCode(String riskCode);
	
	/**
	 * 	
	 * 查询险种的停效日期  
	 *
	 * @history: 2018年4月9日
	 * @author: zhongdw_sinosoft
	 * @param polno
	 * @return
	 */
	String getMaxStartDate(String polno);
	
	/**
	 * 	
	 * 获取有效的类型  
	 *
	 * @history: 2018年4月9日
	 * @author: zhongdw_sinosoft
	 * @param tBaladate
	 * @param contNo
	 * @return
	 */
	Integer getAliveFlag(Date tBaladate , String contNo);
	
	/**
	 * 	
	 * 查询保全名词 
	 *
	 * @history: 2018年4月10日
	 * @author: zhongdw_sinosoft
	 * @param edorType
	 * @return
	 */
	String getEdorName(String edorType);
	
	/**
	 * 	
	 * 获取其他服务业务个数
	 *
	 * @history: 2018年4月10日
	 * @author: zhongdw_sinosoft
	 * @param contNo
	 * @param tansDate
	 * @return
	 */
	BigDecimal getOtherPEdor(String contNo, Date tansDate);
}
