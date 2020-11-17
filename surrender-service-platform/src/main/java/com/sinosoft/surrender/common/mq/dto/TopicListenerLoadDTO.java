package com.sinosoft.surrender.common.mq.dto;

import java.util.List;

public class TopicListenerLoadDTO {

    private List<TopicListenerDTO> topicLietenerDTOs;

    public List<TopicListenerDTO> getTopicLietenerDTOs() {
        return topicLietenerDTOs;
    }

    public void setTopicLietenerDTOs(List<TopicListenerDTO> topicLietenerDTOs) {
        this.topicLietenerDTOs = topicLietenerDTOs;
    }

}
