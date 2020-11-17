package com.sinosoft.surrender.common.mq.listener;

import com.alibaba.fastjson.JSON;
import com.guohualife.common.util.mq.constant.ConsumerResult;
import com.guohualife.common.util.mq.context.MQConsumerContext;
import com.guohualife.common.util.mq.dto.MQMessageDTO;
import com.guohualife.common.util.mq.listener.MQMessageListener;
import com.sinosoft.surrender.common.mq.bo.RenewalUWResultSaveBO;
import com.sinosoft.surrender.db.dao.YcRenewALStatusLogDAO;
import com.sinosoft.surrender.db.model.LcRenewalStatusLog;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *    续保核保结论消息监听
 *
 * @author: jinwc
 * @date: 2020年3月14日-上午11:50:17
 * @version:
 */	
@Component
public class RenewalQueryMsgListener implements MQMessageListener {

    private final Log logger = LogFactory.getLog(getClass());

    @Autowired
    YcRenewALStatusLogDAO ycRenewalStatusLogDAO;
    @Autowired
    RenewalUWResultSaveBO renewalUWResultSaveBO;

    @Override
    public ConsumerResult consumeMessage(MQMessageDTO messageDTO, MQConsumerContext context) {
        logger.info("监听续保核保结论消息开始...");
        try {
            // 消息基础校验
            baseCheck(messageDTO);
            // 消息转换
            String message = (String) messageDTO.getMessage();
            // 转换json格式
            LcRenewalStatusLog lcRenewalStatusLog = JSON.parseObject(message, LcRenewalStatusLog.class);
            renewalUWResultSaveBO.saveRenewalUWResult(lcRenewalStatusLog, messageDTO);
            // 业务处理
            logger.info("监听续保核保结论结果消息处理成功。");
            return ConsumerResult.CONSUMER_SUCCESS;
        } catch (Exception e) {
            logger.error("续保核保结论监听发生非预期异常,", e);
            return ConsumerResult.RECONSUME_LATER;
        }
    }


    private void baseCheck(MQMessageDTO messageDTO) {
        // 幂等校验
        if(StringUtils.isEmpty(messageDTO.getTopic())){
            throw new RuntimeException("消息Topic不能为空");
        }
        if(messageDTO.getMessage()==null){
            throw new RuntimeException("消息体不能为空");
        }
    }

}
