package com.sinosoft.surrender.policyimport.batch.importUtils;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sinosoft.surrender.common.contant.Constant;
import com.sinosoft.surrender.common.exception.TradeException;

public class ImportCoreUtil {
	
	private static Logger logger = LoggerFactory.getLogger(ImportCoreUtil.class);

	/**
	 * 
	 * 将xml报文导入核心
	 *
	 * @history: 2018年3月29日
	 * @author: zhongdw_sinosoft
	 * @param reqXml
	 * @param conURL
	 * @return
	 * @throws IOException
	 */
	public static String importCore(String reqXml, String conURL) throws IOException {
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
			byte[] reqXmlByte = reqXml.getBytes(Constant.UTF_8);
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
			reader = new BufferedReader(new InputStreamReader(dataIn, Constant.UTF_8));
			String aLine;
			while ((aLine = reader.readLine()) != null) {
				resContent.append(aLine);
			}
			// URLDecoder: HTML格式解码的实用工具类
			// 使用UTF-8编码，对 application/x-www-form-urlencoded 字符串解码。
			resXml = java.net.URLDecoder.decode(resContent.toString(), Constant.UTF_8);
			dataIn.close();

		} catch (Exception e) {
			logger.error("导核心发生异常：{}",e);
			throw new TradeException(e.getMessage());
		} finally {
			try {
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
			} catch (Exception e) {
				logger.error("关流异常：{}",e);
			}
		}
		return resXml;
	}
}
