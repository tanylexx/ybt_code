package com.sinosoft.surrender.common.contant;

/**
 * 
 * 错误结果报文元素的正则表达式
 * DOM查询节点的xpath
 *
 * @author:  zhongdw_sinosoft
 * @date: 2018年4月3日-下午4:19:23
 * @version:
 */
public class FailurePatternConstant {
	/*错误结果报文元素的正则表达式*/
	public static String FAILURE_PATTERN1 = ".*((TXLife)|(TXLifeResponse)|(TransResult)|(ResultCode)|(ResultInfo)|(ResultInfoDesc)).*";
	public static String FAILURE_PATTERN2 = ".*((SOAP-ENV:Fault)|(faultcode)|(faultstring)).*";
	public static String FAILURE_PATTERN3 = ".*TranData.*";
	
	/*DOM查询节点的xpath*/
	public static String CODE_PATH1 = "//TXLife/TXLifeResponse/TransResult/ResultCode";
	public static String CODE_PATH2 = "//SOAP-ENV:Fault/faultcode";
	public static String CODE_PATH3 = "//TranData/RetData/Flag";
	
	public static String DESC_PATH1 = "//TXLife/TXLifeResponse/TransResult/ResultInfo/ResultInfoDesc";
	public static String DESC_PATH2 = "//SOAP-ENV:Fault/faultstring";
	public static String DESC_PATH3 = "//TranData/RetData/Desc";
	
	/** 试算退保金额*/
	public static String GETMONEY_PATH = "//TranData/YBTInfo/CnclIns_Amt";
	
}
