package com.sinosoft.surrender.servlet;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.guohualife.platform.common.api.util.XmlUtil;
import com.sinosoft.surrender.policyimport.dto.req.QuerySurrenderReqDTO;
import com.sinosoft.surrender.policyimport.dto.res.QuerySurrenderResDTO;

public class QuerySurrenderServletTest{
	@Test
	public void testServlet() {
		String url = "http://10.56.81.19:8001/surrender/QuerySurrenderServlet";
		QuerySurrenderReqDTO querySurrenderReqDTO = new QuerySurrenderReqDTO();
		querySurrenderReqDTO.setAcceptStatus("01");
		querySurrenderReqDTO.setBankCode("102");

		String reqXml = XmlUtil.toXml(querySurrenderReqDTO);
		try {
			String resXml = importCore(reqXml , url);
			List<?> list = (List<?>) XmlUtil.fromXml(resXml, new Class[] { ArrayList.class });
			System.out.println("---------------client----------------");
			for (int i = 0; i < list.size(); i++) {
				QuerySurrenderResDTO querySurrenderResDTO = (QuerySurrenderResDTO) list.get(i);
				System.out.println(querySurrenderResDTO.getAcceptStatus());
				System.out.println(querySurrenderResDTO.getBankCode());
				System.out.println(querySurrenderResDTO.getContNo());
				System.out.println(querySurrenderResDTO.getDesr());
				System.out.println(querySurrenderResDTO.getEdorType());
				System.out.println(querySurrenderResDTO.getImportDate());
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String importCore(String reqXml ,String conURL) throws IOException {
		// 核心返回的报文xml
		String resXml = "";
		// 连接所需的connection
		HttpURLConnection conn = null;
		// 数据输出流
		DataOutputStream dataOut = null;
		// 数据输入流
		DataInputStream dataIn = null;
		// 字符流
		BufferedReader reader = null;

		try {
			// 创建URL对象
			URL url = new URL(conURL);
			// 获取连接
			conn = (HttpURLConnection) url.openConnection();
			// 获得发送的字节数组
			byte[] reqXmlByte = reqXml.getBytes("UTF-8");
			// 忽略任何可用的缓存
			conn.setUseCaches(false);
			// 使用 URL连接进行输出
			conn.setDoOutput(true);
			// 使用 URL连接进行输入
			conn.setDoInput(true);
			// 设置头信息
			conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
			conn.setRequestProperty("Content-length", String.valueOf(reqXmlByte.length));
			// 设置请求方式
			conn.setRequestMethod("POST");
			// 创建一个新的数据输出流，将数据写入连接的输出流。
			dataOut = new DataOutputStream(conn.getOutputStream());
			dataOut.write(reqXmlByte);
			dataOut.flush();
			dataOut.close();
			// 定义用于返回报文的拼接的对象
			StringBuffer resContent = new StringBuffer();
			dataIn = new DataInputStream(conn.getInputStream());
			reader = new BufferedReader(new InputStreamReader(dataIn, "UTF-8"));
			String aLine;
			while ((aLine = reader.readLine()) != null) {
				resContent.append(aLine);
			}
			// URLDecoder: HTML格式解码的实用工具类
			// 使用UTF-8编码，对 application/x-www-form-urlencoded 字符串解码。
			resXml = java.net.URLDecoder.decode(resContent.toString(), "UTF-8");
			dataIn.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
			if (reader != null) {
				reader.close();
			}
			if (dataIn != null) {
				dataIn.close();
			}
			if (dataOut != null) {
				dataOut.close();
			}
		}
		return resXml;
	}
}
