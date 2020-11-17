package com.sinosoft.surrender.common.mq.bo;

import com.guohualife.common.util.mq.dto.MQMessageDTO;

/**
 * jinwc 2020-4-1
 * 将核保结论保存到云上表中
 */
public interface RenewalUWResultSaveBO {
    /**
     * 查询有没有保存这条核保结果
     */
    void saveRenewalUWResult(Object object, MQMessageDTO mqMessageDTO);
}
