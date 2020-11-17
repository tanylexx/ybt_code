package com.sinosoft.surrender.message.batch;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sinosoft.common.util.quartz.batch.MultThreadBatch;
import com.sinosoft.surrender.message.dao.SurrenderSendMsgUrlSpecDAO;
import com.sinosoft.surrender.message.service.SurrenderSendMsgUrlService;

/**
 * 
 * 退保发送短信批处理
 *
 * @author:  HASEE
 * @date: 2019年12月10日-上午11:57:59
 * @version:
 */
@Component("surrenderSendMsgUrlBatchImpl")
public class SurrenderSendMsgUrlBatchImpl implements MultThreadBatch{
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private SurrenderSendMsgUrlService surrenderSendMsgUrlService;
	@Autowired
	private SurrenderSendMsgUrlSpecDAO surrenderSendMsgUrlSpecDAO;

	
	@Override
	public void executeInit() throws Exception {
		logger.debug("==>批处理执行开始<==");
	}

	@Override
	public List<?> executeData() throws Exception {
		List<String> contnoList = surrenderSendMsgUrlSpecDAO.getSendMsgUrlOfContNoList();
		if (CollectionUtils.isNotEmpty(contnoList)) {
			return contnoList;
		}
		return null;
	}
	
	
	@Override
	public void execute(Object obj) throws Exception {
		String contno = (String) obj;
		surrenderSendMsgUrlService.sendMsgUrl(contno);
	}

	@Override
	public void executeFinal() throws Exception {
		logger.debug("==>批处理执行结束<==");
	}

}
