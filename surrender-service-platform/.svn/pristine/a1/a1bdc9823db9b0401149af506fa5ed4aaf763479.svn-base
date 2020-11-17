package com.sinosoft.surrender.policyimport.batch.importUtils;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sinosoft.surrender.common.contant.Constant;
import com.sinosoft.surrender.common.contant.FailurePatternConstant;
import com.sinosoft.surrender.common.util.ExceptionUtil;

public class CheckFailureUtil {
	private static Logger logger = LoggerFactory.getLogger(CheckFailureUtil.class);
	/**
	 * 对错误返回结果进行处理，目前错误结果有三种报文格式
	 * 
	 * @history: 2018年3月30日
	 * @author: zhongdw_sinosoft
	 * @param resXml
	 * @return
	 */
	public static Map<String, String> checkFailure(String resXml) {
		ExceptionUtil.checkNull(resXml, "核心返回报文为空！");
		// 创建保存错误返回结果信息的map集合
		Map<String, String> failureMap = new HashMap<String, String>();
		// 错误编码
		String respcode = "respcode";
		// 错误描述
		String respdesc = "respdesc";
		// 三种错误结果报文元素的正则表达式
		String failurePattern1 = FailurePatternConstant.FAILURE_PATTERN1;
		String failurePattern2 = FailurePatternConstant.FAILURE_PATTERN2;
		String failurePattern3 = FailurePatternConstant.FAILURE_PATTERN3;
		try {
			// 创建一个SAX解析器对象
			SAXReader saxReader = new SAXReader();
			// 设置编码格式为UTF-8
			saxReader.setEncoding(Constant.UTF_8);
			// 将核心返回报文String通过流转成DOM结构
			Document doc = saxReader.read(new ByteArrayInputStream(resXml.getBytes(Constant.UTF_8)));
			// 将报文中的换行空格都替换
			resXml = resXml.replaceAll("[\r\n]*", "");
			// 判断报文中是否匹配给定的正则表达式
			if (resXml.matches(failurePattern1)) {
				failureMap.put(respcode, getNodeValue(doc, FailurePatternConstant.CODE_PATH1));
				failureMap.put(respdesc, getNodeValue(doc, FailurePatternConstant.DESC_PATH1));
			} else if (resXml.matches(failurePattern2)) {
				failureMap.put(respcode, getNodeValue(doc, FailurePatternConstant.CODE_PATH2));
				failureMap.put(respdesc, getNodeValue(doc, FailurePatternConstant.DESC_PATH2));
			} else if (resXml.matches(failurePattern3)) {
				failureMap.put(respcode, getNodeValue(doc, FailurePatternConstant.CODE_PATH3));
				failureMap.put(respdesc, getNodeValue(doc, FailurePatternConstant.DESC_PATH3));
			} else {
				throw new RuntimeException("无法找到匹配返回报文");
			}

		} catch (Exception e) {
			logger.error("检查失败信息异常{}",e);
		}
		return failureMap;
	}

	/**
	 * 
	 * 获取节点值 modify by wangwl
	 * 
	 * @history: 2018年4月1日
	 * @author: zhongdw_sinosoft
	 * @param doc
	 * @param nodename
	 * @return
	 */
	public static String getNodeValue(Document doc, String nodename) {
		// 判断DOM对象是否为空
		ExceptionUtil.checkNull(doc, "核心返回报文转DOM对象是空，请核实！");
		// 通过xpath查找节点
		Node selectSingleNode = doc.selectSingleNode(nodename);
		Element tnode = (Element) selectSingleNode;
		if (tnode == null) {
			return "";
		}
		if (StringUtils.isNotBlank(tnode.getText())) {
			return tnode.getText().trim();
		}
		String value = tnode.attributeValue("value");
		if (value != null) {
			return value.trim();

		}
		return "";
	}
}
