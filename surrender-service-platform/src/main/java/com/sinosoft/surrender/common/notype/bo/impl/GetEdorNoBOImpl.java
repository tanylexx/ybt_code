package com.sinosoft.surrender.common.notype.bo.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.guohualife.platform.common.api.util.StringUtil;
import com.sinosoft.surrender.common.contant.Constant;
import com.sinosoft.surrender.common.contant.lisenum.ENUM_LIS_EDORNO_TYPE;
import com.sinosoft.surrender.common.notype.bo.GetEdorNoBO;
import com.sinosoft.surrender.common.notype.dao.GetMaxNoDAO;
@Component
public class GetEdorNoBOImpl implements GetEdorNoBO{
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private  GetMaxNoDAO getMaxNoDAO;
	@Override
	public String getEdorNo(String limitNo, String edorNo) {
		
		long startTime = System.currentTimeMillis();
		logger.info("生成流水号开始!");
		// 获取对应的流水号编码
		String edorStr = ENUM_LIS_EDORNO_TYPE.getCodeNoByValue(edorNo);
		// 获取机构编码
		String limitStr = Constant.NOLIMIT;
		//获取最大流水号
		int maxNo = getMaxNoDAO.getMaxNo(edorNo, limitStr);
		String maxno = StringUtil.getString(maxNo);
		//获取最大流水号的字符串长度
		int noLength = maxno.length();
		int limitLength = 16;
		//获取除去流水号编码和尾号之外剩下的字符串长度
		int checkLength = limitLength - edorStr.length()-1;
		if(noLength - checkLength < 0){
			//将最大流水号用0进行补充直到与剩下的字符串长度相等
			maxno = StringUtil.leftPad(maxno, checkLength, "0");
		}
		//拼接字符串，生成所需号码
		String wantedNo = StringUtil.rightPad(edorStr + maxno + "8", 20, " ");
		logger.info("生成流水号结束，耗时:{} 秒！",(System.currentTimeMillis() - startTime)/1000f);
		return wantedNo;
	}

}
