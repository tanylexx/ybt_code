package com.sinosoft.surrender.common.contant;

public class XmlElementConstant {
	// 退保导核心的报文
	public static String TRAN_DATA = "TranData";
	
	public static String BASE_INFO = "BaseInfo";
	public static String BANK_DATE = "BankDate";
	public static String BANK_TIME = "BankTime";
	public static String BANK_CODE = "BankCode";
	public static String ZONE_NO = "ZoneNo";
	public static String BR_NO = "BrNo";
	public static String TELLER_NO = "TellerNo";
	public static String TRANSR_NO = "TransrNo";
	public static String FUNCTION_FLAG = "FunctionFlag";
	public static String INSU_ID = "InsuID";
	public static String SOURCE_TYPE = "SourceType";
	
	public static String LC_CONT = "LCCont";
	public static String CONT_NO = "ContNo";
	public static String PASSWORD = "Password";
	public static String LC_APPNT = "LCAppnt";
	public static String APPNT_NAME = "AppntName";
	public static String APPNT_ID_TYPE = "AppntIDType";
	public static String APPNT_ID_NO = "AppntIDNo";
	public static String APPNT_MOBILE = "AppntMobile";
	public static String LC_INSUREDS = "LCInsureds";
	public static String LC_INSURED = "LCInsured";
	public static String NAME = "Name";
	public static String ID_TYPE = "IDType";
	public static String ID_NO = "IDNo";
	public static String RISKS = "Risks";
	public static String RISK = "Risk";
	public static String RISKCODE = "RisksCode";
	public static String BANK_ACC_NO = "BankAccNo";
	public static String BANK_ACC_NAME = "BankAccName";
	
	public static String EDOR_INFO = "EdorInfo";
	public static String YBT_EDOR_FLAG = "YBTEdorFlag";
	public static String EDOR_PRT_NO = "EdorPrtNo";
	public static String RESEND_FLAG = "ReSendFlag";
	public static String CIRLTMRTF_IND = "CIRlTmRtF_Ind";
	public static String AGINS_PKG_ID = "AgIns_Pkg_ID";
	public static String INS_CO_TRANS_NO = "InsCoTransNo";
	public static String APPLY_MONEY = "ApplyMoney";
	public static String EDOR_ACCEPT_NO = "EdorAcceptNo";
	public static String SEND_PHONE = "SendPhone";
	// 核心退保查询报文
	/*<?xml version="1.0" encoding="UTF-8"?>
	<TranData>
		<BaseInfo>
			<BankDate>20180807</BankDate>
			<BankTime>00:00:00</BankTime>
			<BankCode>05</BankCode>
			<ZoneNo>3700</ZoneNo>
			<BrNo>37018910</BrNo>
			<TellerNo>flag_09</TellerNo>
			<TransrNo>2799559</TransrNo>
			<FunctionFlag>24</FunctionFlag>
			<InsuID>0066</InsuID>
			<SourceType>4</SourceType>
		</BaseInfo>
		<EdorInfo>
			<EdorType/>
			<OrderNo/>
			<ContNo>3716001890164538</ContNo>
			<EdorAppDate>20180807</EdorAppDate>
		</EdorInfo>
		<YBTEdorInfo>
			<YBTEdorFlag>1</YBTEdorFlag>
			<AppntName>戚树芳</AppntName>
			<AppntIDType>0</AppntIDType>
			<AppntIDNo>372325199407063629</AppntIDNo>
			<BtchPrtNo>5000000001875638</BtchPrtNo>
			<InsPolcyPswd/>
			<TellOutCallFlag/>
		</YBTEdorInfo>
	</TranData>*/
	/** 根节点*/
	public static String TRANDATA = "TranData";
	/** 基础信息节点*/
	public static String BASEINFO = "BaseInfo";
	/** 银行交易日期*/
	public static String BANKDATE = "BankDate";
	/** 银行交易时间*/
	public static String BANKTIME = "BankTime";
	/** 银行编码*/
	public static String BANKCODE = "BankCode";
	/** 地区码*/
	public static String ZONENO = "ZoneNo";
	/** 网点码*/
	public static String BRNO = "BrNo";
	/** 操作人员*/
	public static String TELLERNO = "TellerNo";
	/** 流水号*/
	public static String TRANSRNO = "TransrNo";
	/** 交易类型*/
	public static String FUNCTIONFLAG = "FunctionFlag";
	/** */
	public static String INSUID = "InsuID";
	/** 渠道编码*/
	public static String SOURCETYPE = "SourceType";
	
	/** 保全信息节点*/
	public static String EDORINFO = "EdorInfo";
	/** 保全类型*/
	public static String EDORTYPE = "EdorType";
	/** */
	public static String ORDERNO = "OrderNo";
	/** 保单号*/
	public static String CONTNO = "ContNo";
	/** 保全受理日期*/
	public static String EDORAPPDATE = "EdorAppDate";
	/** 保全受理时间*/
	public static String EDORAPPTIME = "EdorAppTime";
	/** 银保通保全信息节点*/
	public static String YBTEDORINFO = "YBTEdorInfo";
	/** 银保通保全标志*/
	public static String YBTEDORFLAG = "YBTEdorFlag";
	/**申请方式*/
	public static String APPTYPE="AppType";
	/** 投保人姓名*/
	public static String APPNTNAME = "AppntName";
	/** 投保人证件类型*/
	public static String APPNTIDTYPE = "AppntIDType";
	/** 投保人证件号码*/
	public static String APPNTIDNO = "AppntIDNo";
	/** */
	public static String BTCHPRTNO = "BtchPrtNo";
	/** */
	public static String INSPOLCYPSWD = "InsPolcyPswd";
	/** */
	public static String TELLOUTCALLFLAG = "TellOutCallFlag";
	
}
