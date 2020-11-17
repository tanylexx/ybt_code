package com.sinosoft.surrender.cashvalue.bo.impl;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;

import com.sinosoft.surrender.cashvalue.bo.CashValueDTOtoXMLBO;
import com.sinosoft.surrender.cashvalue.dto.req.PolicyCashValueReqDTO;
import com.sinosoft.surrender.common.contant.XmlElementConstant;
import com.sinosoft.surrender.common.util.DateUtil;
import com.sinosoft.surrender.db.dao.LcContDAO;
import com.sinosoft.surrender.db.model.LcCont;

public class CashValueDTOtoXMLBOImpl implements CashValueDTOtoXMLBO {
	@Autowired
	private LcContDAO lcContDAO;

	@Override
	public String dealDTOTOXML(PolicyCashValueReqDTO policyCashValueReqDTO) {
		// 根据保单号查询Lccont
		LcCont lcCont = lcContDAO.selectByPrimaryKey(policyCashValueReqDTO.getContNo());
		Document doc = DocumentHelper.createDocument();
		// 根节点
		Element tranData = doc.addElement(XmlElementConstant.TRANDATA);

		// 基础信息节点
		Element baseInfo = tranData.addElement(XmlElementConstant.BASEINFO);
		String applyDate = DateUtil.getDateStr(policyCashValueReqDTO.getApplyDate(), "yyyyMMdd");
		baseInfo.addElement(XmlElementConstant.BANKDATE).setText(change(applyDate));
		baseInfo.addElement(XmlElementConstant.BANKTIME).setText(change(policyCashValueReqDTO.getApplyTime()));
		baseInfo.addElement(XmlElementConstant.BANKCODE).setText(change(policyCashValueReqDTO.getBankCode()));
		baseInfo.addElement(XmlElementConstant.ZONENO).setText(change(policyCashValueReqDTO.getZoneNo()));
		baseInfo.addElement(XmlElementConstant.BRNO).setText(change(policyCashValueReqDTO.getBrNo()));
		baseInfo.addElement(XmlElementConstant.TELLERNO).setText(change(policyCashValueReqDTO.getTellerNo()));
		baseInfo.addElement(XmlElementConstant.TRANSRNO).setText(change(policyCashValueReqDTO.getTransNo()));
		baseInfo.addElement(XmlElementConstant.FUNCTIONFLAG).setText(change(policyCashValueReqDTO.getFunctionFlag()));
		baseInfo.addElement(XmlElementConstant.INSUID).setText(change(lcCont.getInsuredno()));
		baseInfo.addElement(XmlElementConstant.SOURCETYPE).setText(change(policyCashValueReqDTO.getSourceType()));

		// 保全信息节点
		Element edorInfo = tranData.addElement(XmlElementConstant.EDORINFO);
		edorInfo.addElement(XmlElementConstant.EDORTYPE).setText(change(policyCashValueReqDTO.getEdorType()));
		edorInfo.addElement(XmlElementConstant.ORDERNO);
		edorInfo.addElement(XmlElementConstant.CONTNO).setText(change(policyCashValueReqDTO.getContNo()));
		edorInfo.addElement(XmlElementConstant.EDORAPPDATE).setText(change(applyDate));

		// 银保通保全信息节点
		Element ybtEdorInfo = tranData.addElement(XmlElementConstant.YBTEDORINFO);
		// 标志：1:银保通的交易
		ybtEdorInfo.addElement(XmlElementConstant.YBTEDORFLAG).setText("1");
		ybtEdorInfo.addElement(XmlElementConstant.APPNTNAME).setText(change(lcCont.getAppntname()));
		ybtEdorInfo.addElement(XmlElementConstant.APPNTIDTYPE).setText(change(lcCont.getAppntidtype()));
		ybtEdorInfo.addElement(XmlElementConstant.APPNTIDNO).setText(change(lcCont.getAppntidno()));
		ybtEdorInfo.addElement(XmlElementConstant.BTCHPRTNO).setText(change(lcCont.getPrtno()));
		ybtEdorInfo.addElement(XmlElementConstant.INSPOLCYPSWD);
		ybtEdorInfo.addElement(XmlElementConstant.TELLOUTCALLFLAG);

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
