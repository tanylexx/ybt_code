package com.sinosoft.surrender.common.mq.send.bo.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.guohualife.common.util.mq.client.MQProducer;
import com.guohualife.common.util.mq.dto.MQMessageDTO;
import com.sinosoft.surrender.common.mq.send.bo.SendMqMsgBO;

@Component
public class SendMqMsgBOImpl implements SendMqMsgBO {

    private Log logger = LogFactory.getLog(this.getClass());
    @Autowired
    protected MQProducer mqproducer;

    @Override
    public void sendMQMsg(String messageSendTopic, String tag, String key, Object o) {
        try {
            MQMessageDTO message = new MQMessageDTO();
            message.setTopic(messageSendTopic);
            message.setTag(tag);
            message.setKey(key);
            message.setMessage(JSON.toJSONString(o));
            StringBuffer sb = new StringBuffer("开始推送MQ消息");
            String str = sb.append(messageSendTopic).append(tag).append(key).toString();
            this.logger.info(str);
            mqproducer.sendAsync(message);
        } catch (Exception e) {
            logger.error("消息推送异常:", e);
            throw new RuntimeException(e);
        }
    }
}
