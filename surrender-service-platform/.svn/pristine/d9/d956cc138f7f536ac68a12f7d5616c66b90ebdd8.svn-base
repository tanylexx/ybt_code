package com.sinosoft.surrender.policyimport.batch.importUtils;

import java.io.IOException;

import org.junit.Test;

import com.sinosoft.surrender.cashvalue.base.BaseTester;

public class ImportCoreUtilTest extends BaseTester {
	
	
	@Test
	public void testImportCore(){
		String reqXml = "<?xml version='1.0' encoding='UTF-8'?>"
				+ "<TranData><BaseInfo><BankDate>20180403</BankDate>"
				+ "<BankTime>143040</BankTime><BankCode>05</BankCode>"
				+ "<ZoneNo>2103</ZoneNo><BrNo>210303003</BrNo>"
				+ "<TellerNo>9310000039</TellerNo><TransrNo>PSBCct2017051500652</TransrNo>"
				+ "<FunctionFlag>27</FunctionFlag><InsuID></InsuID>"
				+ "<SourceType>2</SourceType></BaseInfo><LCCont>"
				+ "<ContNo>3101001890105628</ContNo><Password /><LCAppnt>"
				+ "<AppntName></AppntName><AppntIDType></AppntIDType>"
				+ "<AppntIDNo></AppntIDNo><AppntMobile />"
				+ "</LCAppnt><LCInsureds><LCInsured><Name /><IDType /><IDNo />"
				+ "<Risks><Risk><RiskCode></RiskCode></Risk></Risks></LCInsured>"
				+ "</LCInsureds><BankAccNo>62220220151007111</BankAccNo><BankAccName>设计数2</BankAccName>"
				+ "</LCCont><EdorInfo><YBTEdorFlag>1</YBTEdorFlag><EdorPrtNo>2000000000000008</EdorPrtNo><ReSendFlag />"
				+ "<CIRlTmRtF_Ind></CIRlTmRtF_Ind><AgIns_Pkg_ID></AgIns_Pkg_ID>"
				+ "<InsCoTransNo></InsCoTransNo></EdorInfo></TranData>";
				
		String ServletUrl = "http://10.56.81.26:7004/TranInterfaceServlet";
		
		try {
			logger.info(ImportCoreUtil.importCore(reqXml, ServletUrl));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
