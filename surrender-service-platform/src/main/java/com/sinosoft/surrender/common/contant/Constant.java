package com.sinosoft.surrender.common.contant;

import java.math.BigDecimal;

/**
 * 
 * 常量类
 * 
 * @author: wangwl_sinosoft
 * @date: 2018-3-8-上午11:15:19
 * @version:
 */
public class Constant {

	/**
	 * 计算时刻现价锁类型
	 */
	public final static String CALCASHVALUELOCKTYPE = "CALCASHVALUELOCK";

	public final static String CALCASHVALUELOCKER = "CALCASHVALUELOCKER";
	/**
	 * 退保确认锁类型
	 */
	public final static String SURRENDERCONFIRMLOCKTYPE = "SURRENDERCONFIRMLOCKTYPE";

	public final static String SURRENDERCONFIRMLOCKER = "SURRENDERCONFIRMLOCKER";
	/**
	 * 前台修改处理状态：保全撤销，重新导入，锁类型
	 */
	public final static String POLICYIMPORTDEALLOCKTYPE = "POLICYIMPORTDEALLOCKTYPE";
	public final static String POLICYIMPORTDEALLOCKER = "POLICYIMPORTDEALLOCKER";
	/**
	 * 导核心批处理锁类型
	 */
	public final static String SURRENDERIMPORTCORELOCKTYPE = "SURRENDERIMPORTCORELOCKTYPE";
	public final static String SURRENDERIMPORTCORELOCKER = "SURRENDERIMPORTCORELOCKER";
	/**
	 * 退保短链接批处理锁类型
	 */
	public final static String SURRENDERSHORTURLLOCKTYPE = "SURRENDERSHORTURLLOCKTYPE";
	public final static String SURRENDERSHORTURLLOCKER = "SURRENDERSHORTURLLOCKER";
	/**
	 * 退保确认短信批处理锁类型
	 */
	public final static String SURRENDERMESSAGETLOCKTYPE = "SURRENDERMESSAGETLOCKTYPE";
	public final static String SURRENDERMESSAGETLOCKER = "SURRENDERMESSAGETLOCKER";
	/**
	 * 保费追加业务锁类型
	 */
	public final static String ADD_PREM_LOCKTYPE = "ADDPREMLOCKTYPE";
	public final static String ADD_PREM_LOCKER = "ADDPREMLOCKER";
	/**
	 * 保费追加导核心锁类型
	 */
	public final static String ADD_PREM_IMPORT_LOCKTYPE = "ADDPREMIMPORTLOCKTYPE";
	public final static String ADD_PREM_IMPORT_LOCKER = "ADDPREMIMPORTLOCKER";
	/**
	 * 锁类型
	 */
	public static final String DZSWHK = "DZSWHK";
	/**
	 * 自垫有效
	 */
	public final static String ZDVALID = "0101";
	/**
	 * 自垫停效
	 */
	public final static String ZDSTOP = "0204";

	public final static String ZERO = "0";

	public final static String ONE = "1";

	//public final static BigDecimal REDISFLAG = new BigDecimal("-1");

	public final static BigDecimal NEGATIVE_ONE = new BigDecimal("-1");

	/** 保单冻结类型 */
	public static final String BANKLOAN = "BankLoan";
	public static final String EPAYLOAN = "EPayLoan";

	/** 字符：01 */
	public static final String HEAD_01 = "01";
	/** 字符：02 */
	public static final String HEAD_02 = "02";

	public static final String CALCODESPEC = "XXXXXX";
	/** 机构编码：SYS */
	public static final String NOLIMIT = "SYS";
	/**
	 * redis的namespace
	 */
	public static final String NAMESPACE = "gh.ybt.stock";

	/**
	 * 长险类型
	 */
	public static final String LONG = "L";
	/** 保全类型 */
	public static final String EDORTYPE = "bzedortype";

	public static final String UTF_8 = "UTF-8";
	public static final String GBK = "GBK";
	//银保通犹豫期
	public static final int YBTHESITATEPERIOD = 15;
	
	/**
	 * 不重复常量
	 */
	public static final String UNREPEAT = "UNREPEAT";
	
	public static final String YBTSEND = "YBTSendServlet";
	
	/** 利率表维护的最大结束日期 */
	public static final String MAXENDDATE = "MAXENDDATE";
	
	public static final String YBTMSGBATCH = "YBTMSGURL";
}
