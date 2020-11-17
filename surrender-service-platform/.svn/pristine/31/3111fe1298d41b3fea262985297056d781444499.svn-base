package com.sinosoft.surrender.common.mq.listener;

import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.guohualife.common.util.mq.client.MQConsumer;
import com.guohualife.common.util.mq.context.MQConsumerContext;
import com.guohualife.common.util.mq.listener.MQMessageListener;
import com.guohualife.platform.common.api.context.SpringContext;
import com.sinosoft.surrender.common.mq.dto.TopicListenerDTO;
import com.sinosoft.surrender.common.mq.dto.TopicListenerLoadDTO;
/**
 * @author HASEE
 */
public class MqInitServlet extends HttpServlet {

    private static final long serialVersionUID = 6791023217855035760L;

    private final Log logger = LogFactory.getLog(getClass());

    private static TopicListenerLoadDTO topicListenerLoadDTO = null;

    private static MQConsumer consumer = null;

    private static void ensureInit() {
        if (null == consumer) {
            consumer = (MQConsumer) SpringContext.getBean("MQConsumer");
        }
        if (null == topicListenerLoadDTO) {
            topicListenerLoadDTO = (TopicListenerLoadDTO) SpringContext.getBean("TopicListenerLoadDTO");
        }
    }
    @Override
    public void init() {
        try {
            logger.info("消息初始化开始");
            ensureInit();
            List<TopicListenerDTO> topicLietenerDTOs = topicListenerLoadDTO.getTopicLietenerDTOs();
            if (null == topicLietenerDTOs || topicLietenerDTOs.size() < 1) {
                logger.info("没有要监听的topic");
                return;
            }
            for (TopicListenerDTO topicLietenerDTO : topicLietenerDTOs) {
                consumer.subscribe(topicLietenerDTO.getTopic(), topicLietenerDTO.getTag(),
                        (MQMessageListener) SpringContext.getBean(topicLietenerDTO.getListenerBeanName()));
            }
            consumer.start();
            MQConsumerContext.addConsumer(consumer);
            logger.info("消息初始化成功");
        } catch (Exception e) {
            logger.error("加载消息异常", e);
        }
    }

    /**
     * 重新调度消息任务
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        logger.info("MQConsumer重新调度开始");
        try {
            reloadConsumer();
        } catch (Exception e) {
            logger.info("MQConsumer调度失败！\n", e);
        }
        logger.info("MQConsumer重新调度结束");
    }

    /**
     * 重新调度MQConsumer任务
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        this.doGet(request, response);
    }

    public void shutdownConsumer() throws Exception {
        Collection<MQConsumer> consumers = MQConsumerContext.getRunningConsumer();
        for (MQConsumer consumer : consumers) {
            consumer.shutdown();
            MQConsumerContext.removeConsumer(consumer);
        }
        logger.info("removeConsumer success");
    }

    public void reloadConsumer() throws Exception {
        shutdownConsumer();
        init();
        logger.info("removeConsumer success");
    }

}
