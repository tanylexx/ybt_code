package com.sinosoft.surrender.policyimport.batch.importUtils;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.sinosoft.surrender.common.contant.XmlElementConstant;
import com.sinosoft.surrender.common.util.DateUtil;
import com.sinosoft.surrender.common.util.ExceptionUtil;
import com.sinosoft.surrender.policyimport.dto.SurrenderImportCoreDTO;

public class DtoToCoreXml {
	/**
	 * 
	 * 将DTO转成报文XML
	 * 
	 * @history: 2018年3月29日
	 * @author: zhongdw_sinosoft
	 * @param surrenderDataDTO
	 * @return
	 */
	public static String dtoToXml(SurrenderImportCoreDTO surrenderImportCoreDTO) {
		Document doc = DocumentHelper.createDocument();
		Element TranData = doc.addElement(XmlElementConstant.TRAN_DATA);
		Element BaseInfo = TranData.addElement(XmlElementConstant.BASE_INFO);
		ExceptionUtil.checkNull(surrenderImportCoreDTO.getBankDate(), "交易日期不能为空，请核实");
		String date = DateUtil.getDateStr(surrenderImportCoreDTO.getBankDate(), "yyyyMMdd");
		ExceptionUtil.checkNull(surrenderImportCoreDTO.getBankTime(), "交易时间不能为空，请核实");
		String time = surrenderImportCoreDTO.getBankTime().replaceAll(":", "");
		BaseInfo.addElement(XmlElementConstant.BANK_DATE).setText(date);
		BaseInfo.addElement(XmlElementConstant.BANK_TIME).setText(time);
		BaseInfo.addElement(XmlElementConstant.BANK_CODE).setText(change(surrenderImportCoreDTO.getBankCode()));
		BaseInfo.addElement(XmlElementConstant.ZONE_NO).setText(change(surrenderImportCoreDTO.getZoneNo()));
		BaseInfo.addElement(XmlElementConstant.BR_NO).setText(change(surrenderImportCoreDTO.getBrNo()));
		BaseInfo.addElement(XmlElementConstant.TELLER_NO).setText(change(surrenderImportCoreDTO.getTellerNo()));
		BaseInfo.addElement(XmlElementConstant.TRANSR_NO).setText(change(surrenderImportCoreDTO.getTransNo()));
		BaseInfo.addElement(XmlElementConstant.FUNCTION_FLAG).setText(change(surrenderImportCoreDTO.getFunctionFlag()));
		BaseInfo.addElement(XmlElementConstant.INSU_ID);
		BaseInfo.addElement(XmlElementConstant.SOURCE_TYPE).setText(change(surrenderImportCoreDTO.getSourceType()));

		Element LCCont = TranData.addElement(XmlElementConstant.LC_CONT);
		LCCont.addElement(XmlElementConstant.CONT_NO).setText(change(surrenderImportCoreDTO.getContNo()));
		LCCont.addElement(XmlElementConstant.PASSWORD);

		Element LCAppnt = LCCont.addElement(XmlElementConstant.LC_APPNT);
		LCAppnt.addElement(XmlElementConstant.APPNT_NAME);
		LCAppnt.addElement(XmlElementConstant.APPNT_ID_TYPE);
		LCAppnt.addElement(XmlElementConstant.APPNT_ID_NO);
		LCAppnt.addElement(XmlElementConstant.APPNT_MOBILE);
		Element LCInsureds = LCCont.addElement(XmlElementConstant.LC_INSUREDS);
		Element LCInsured = LCInsureds.addElement(XmlElementConstant.LC_INSURED);
		LCInsured.addElement(XmlElementConstant.NAME);
		LCInsured.addElement(XmlElementConstant.ID_TYPE);
		LCInsured.addElement(XmlElementConstant.ID_NO);
		Element Risks = LCInsured.addElement(XmlElementConstant.RISKS);
		Element Risk = Risks.addElement(XmlElementConstant.RISK);
		Risk.addElement(XmlElementConstant.RISKCODE);
		LCCont.addElement(XmlElementConstant.BANK_ACC_NO).setText(change(surrenderImportCoreDTO.getBankAccNo()));
		LCCont.addElement(XmlElementConstant.BANK_ACC_NAME).setText(change(surrenderImportCoreDTO.getBankAccName()));
		Element EdorInfo = TranData.addElement(XmlElementConstant.EDOR_INFO);
		EdorInfo.addElement(XmlElementConstant.YBT_EDOR_FLAG).setText("1");
		EdorInfo.addElement(XmlElementConstant.EDOR_PRT_NO).setText(change(surrenderImportCoreDTO.getEdorPrtNo()));
		EdorInfo.addElement(XmlElementConstant.RESEND_FLAG);
		// 新增字段
		EdorInfo.addElement(XmlElementConstant.CIRLTMRTF_IND);
		EdorInfo.addElement(XmlElementConstant.AGINS_PKG_ID);
		EdorInfo.addElement(XmlElementConstant.INS_CO_TRANS_NO);
		EdorInfo.addElement(XmlElementConstant.APPLY_MONEY).setText(change(surrenderImportCoreDTO.getApplyMoney()));
		EdorInfo.addElement(XmlElementConstant.EDOR_ACCEPT_NO)
				.setText(change(surrenderImportCoreDTO.getEdorAcceptNo()));

		return doc.asXML();
	}

	/**
	 * 	
	 * 对象为NULL转换
	 *
	 * @history: 2018年5月8日
	 * @author: HASEE
	 * @param obj
	 * @return
	 */
	private static String change(Object obj) {
		if (obj == null) {
			return "";
		}
		String objStr = obj.toString().trim();
		return objStr;
	}

}
