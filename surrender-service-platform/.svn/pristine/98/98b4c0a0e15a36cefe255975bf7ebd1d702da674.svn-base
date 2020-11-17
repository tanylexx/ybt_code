package com.sinosoft.surrender.surrconfirm.bo.impl;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.guohualife.platform.common.api.util.XmlUtil;
import com.sinosoft.surrender.cashvalue.dto.CheckMSMDto;
import com.sinosoft.surrender.cashvalue.dto.NewMSMDto;
import com.sinosoft.surrender.common.config.bo.ConfigInfoBO;
import com.sinosoft.surrender.common.contant.Constant;
import com.sinosoft.surrender.common.contant.surrenderenum.ENUM_MESSAGE_TYPE;
import com.sinosoft.surrender.common.contant.surrenderenum.ENUM_TRACE_STATUS;
import com.sinosoft.surrender.common.exception.LockFailedException;
import com.sinosoft.surrender.common.exception.TradeException;
import com.sinosoft.surrender.common.lock.bo.LockBO;
import com.sinosoft.surrender.db.dao.LcContDAO;
import com.sinosoft.surrender.db.model.LcCont;
import com.sinosoft.surrender.db.model.YcSendMessage;
import com.sinosoft.surrender.message.dao.SurrenderSendMsgUrlSpecDAO;
import com.sinosoft.surrender.surrconfirm.bo.SurrenderConfirmSendMessageBO;
import com.sinosoft.surrender.surrconfirm.dao.LdCodeSpecDAO;
import com.sinosoft.surrender.surrconfirm.dao.YcSendMessageSpecDAO;
import com.sinosoft.surrender.surrconfirm.dao.YcSurrenderSpecDAO;

@Component
public class SurrenderConfirmSendMessageBOImpl implements SurrenderConfirmSendMessageBO {
	private Logger logger = LoggerFactory.getLogger(getClass());
	private boolean debug = logger.isDebugEnabled();
	@Resource
	private LockBO lockBO;
	@Resource
	private LdCodeSpecDAO ldCodeSpecDAO;
	@Resource
	private YcSendMessageSpecDAO ycSendMessageSpecDAO;
	@Resource
	private LcContDAO lccontDao;//sql语句
	@Resource
	private ConfigInfoBO configInfoBO; 
	@Resource
	private SurrenderSendMsgUrlSpecDAO surrenderSendMsgUrlSpecDAO;
	@Resource
	private YcSurrenderSpecDAO ycSurrenderSpecDao;

	@Override
	public void sendConfirmMessage(YcSendMessage ycSendMessage) {
		long startTime = System.currentTimeMillis();
		String lockType = Constant.SURRENDERMESSAGETLOCKTYPE;// 加锁
		String loker = Constant.SURRENDERMESSAGETLOCKER;
		StringBuffer sbf = new StringBuffer();
		sbf.append(ycSendMessage.getContno());
		String key = sbf.toString();
		if (debug) {
			logger.debug("====>加锁中...<====");
		}
		try {
			lockBO.lock(lockType, key, loker);
		} catch (LockFailedException e1) {
			logger.error("加锁失败：{}", e1);
			return;
		}
		try {
			String conURL = ldCodeSpecDAO.findCodeName(Constant.YBTSEND);
			StringBuffer sbff = new StringBuffer();
			sbff.append(conURL);
			sbff.append("LisCommonServlet?dealclass=YBTSendMessageImpl&dealmethod=dealServlet&&Contno=");
			sbff.append(ycSendMessage.getContno());
			sbff.append("&&BussinessTypeNo=");
//and by yangyb 20191106
			if(sendNewMSG(ycSendMessage)){//发送新短信
				sbff.append(ENUM_MESSAGE_TYPE.getNameByValue("NEWMSG"));
				ycSendMessage.setModifiedUser("NEWMESSAGE");
			}else{//发送旧短信
				sbff.append(ENUM_MESSAGE_TYPE.getNameByValue(ycSendMessage.getEdortype()));
				ycSendMessage.setModifiedUser("MESSAGEBATCH");
			}
			
			sbff.append("&&Operator=");
			sbff.append("YBTSURRENDER");
			if (debug) {
				logger.debug("调用核心的短信发送servet为：{}", sbff.toString());
			}
			// 调用核心短信发送servert
			String resXml = importCore(sbff.toString());
			ycSendMessage.setProcessResult("短信发送成功!");
			ycSendMessage.setProcessStatus(ENUM_TRACE_STATUS.SUCCESS.getValue());
			logger.info(XmlUtil.toXml(resXml));
			//LISBUG-7543-退保平台带链接短信批处理优化 add by songjie 20200318
			//查询保单是否已发送过退保短信,
			List<YcSendMessage> contnoList = ycSendMessageSpecDAO.queryMessageByContNo(ycSendMessage.getContno());
			if(CollectionUtils.isNotEmpty(contnoList)) {
				//删除已发短信的数据
				ycSendMessageSpecDAO.updateStatusByContNo(ycSendMessage);
			}
			//查询保单是否已发送过退保链接短信,
			List<String> contList = surrenderSendMsgUrlSpecDAO.queryMessageUrlByContNo(ycSendMessage.getContno());
			if (CollectionUtils.isNotEmpty(contList)) {
				//删除退保链接短信数据
				surrenderSendMsgUrlSpecDAO.updateStatusByContNo(ycSendMessage);
			}
		} catch (Exception e) {
			logger.error("退保确认短信发送异常：", e);
			ycSendMessage.setProcessStatus(ENUM_TRACE_STATUS.FAIL.getValue());
			String resultMsg = e.getMessage();
			if (resultMsg.length() > 500) {
				resultMsg = resultMsg.substring(0, 500);
			}
			ycSendMessage.setProcessResult(resultMsg);
		} finally {
			try {
				lockBO.unLock(lockType, key);
				long endTime = System.currentTimeMillis();
				if (debug) {
					logger.debug("此保单号为：{}，短信发送耗时：{}毫秒", ycSendMessage.getContno(), (endTime - startTime));
				}
				ycSendMessage.setProcessCost(BigDecimal.valueOf(endTime - startTime));
				ycSendMessage.setModifiedDate(new Date());
				ycSendMessageSpecDAO.updateYcSendMessageByContNo(ycSendMessage);
			} catch (Exception e) {
				logger.error("非预期异常:{}", e);
			}
		}
	}
	/**
	 * 是否发送新短信的校验
	 * @param ycSendMessage
	 * @return
	 */	
	private boolean sendNewMSG(YcSendMessage ycSendMessage) {
		List<NewMSMDto> newMSMDtolist = configInfoBO.getNewMSG();//配置中心的数据放到集合中
		CheckMSMDto cmDto = new CheckMSMDto();//该对象存放用户涉及的三个维度
		LcCont lccont = lccontDao.selectByPrimaryKey(ycSendMessage.getContno());//通过主键取（保单信息相关数据）
		// 获取退保或给付金额 add by wujw_sinosoft @LISBUG-7811-银保通在线受理提醒短信调整
		BigDecimal applyPrem = ycSurrenderSpecDao.getApplyPremByContNo(ycSendMessage.getContno());
		if(null!=lccont){
			cmDto.setBankcode(ycSendMessage.getBankcode().trim());
			cmDto.setManagecom(lccont.getManagecom());
			cmDto.setSaleChnlDetail(lccont.getSalechnldetail());
		}
		for (NewMSMDto newMSMDto : newMSMDtolist) {
			List < String > bankcodeList = Arrays.asList(newMSMDto.getBankcode().split("#"));//分割配置中心取出的维度
			List < String > saleChnlDetailList = Arrays.asList(newMSMDto.getSaleChnlDetail().split("#"));
			List < String > ManagecomList = Arrays.asList(newMSMDto.getManagecom().split("#"));
			//银行这个维度的判断
			if((bankcodeList.contains(cmDto.getBankcode())||StringUtils.isBlank(newMSMDto.getBankcode()))&&//配置中心包含或者配置中心不存在的情况
					(saleChnlDetailList.contains(cmDto.getSaleChnlDetail())||StringUtils.isBlank(newMSMDto.getSaleChnlDetail()))&&//截取用户机构的前六位
					(ManagecomList.contains(cmDto.getManagecom().substring(0, 6))||StringUtils.isBlank(newMSMDto.getManagecom()))){//三个维度都校验
				// 获取退保或给付金额大于等于1万元，才发送新短信 add by wujw_sinosoft @LISBUG-7811-银保通在线受理提醒短信调整
				if (applyPrem.compareTo(new BigDecimal("10000"))>=0) {
					return true;
				}
			}
		}
		return false;
	}
	/**
	 * 
	 * 调用核心servet
	 *
	 * @history: 2018年6月11日
	 * @author: HASEE
	 * @param conURL
	 * @return
	 * @throws IOException
	 */
	public String importCore(String conURL) throws IOException {
		String resXml = "";
		// 连接所需的connection
		HttpURLConnection conn = null;
		// 数据输入流
		DataInputStream dataIn = null;
		// 字符流
		BufferedReader reader = null;
		try {
			// 创建URL对象
			URL url = new URL(conURL);
			// 获取连接
			conn = (HttpURLConnection) url.openConnection();
			// 忽略任何可用的缓存
			conn.setUseCaches(false);
			// 使用 URL连接进行输出
			conn.setDoOutput(true);
			// 使用 URL连接进行输入
			conn.setDoInput(true);
			// 设置头信息
			conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
			// 设置请求方式
			conn.setRequestMethod("POST");
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
			logger.error("调用核心servet发生异常：{}", e);
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
			} catch (Exception e) {
				logger.error("关流异常：{}", e);
			}
		}
		return resXml;
	}

}
