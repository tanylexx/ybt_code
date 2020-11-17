package com.sinosoft.surrender.batchRenew.importUtils;

import com.guohualife.platform.common.api.util.StringUtil;

/**
 * 改枚举类用于表示YCRENEWALSTATUSLOG的polstatus字段种类
 * */
public enum ENUM_RENEW_STATUS {
    //核保通过随时可以接受试算
    WAIT_TO_ACCEPT("准备试算","0"),
    //试算通过待签单
    WAIT_TO_SIGN("准备签单","1"),
    //2代表签单完成但是未导核心
    WAIT_TO_IMPORT("准备导核心","2"),
    //导核心结束
    IMPORT_COMPLETE("导核心完毕","3")
    ;
    private final String statusName;
    private final String status;
    ENUM_RENEW_STATUS(String statusName, String status) {
        this.statusName = statusName;
        this.status = status;
    }

    public String getStatusName() {
        return statusName;
    }

    public String getStatus() {
        return status;
    }

    public static String getStatusByStatusName(String statusName) {
        if(StringUtil.isNotBlank(statusName)) {
            for(ENUM_RENEW_STATUS obj : ENUM_RENEW_STATUS.values()) {
                if(obj.getStatus().equals(statusName)) {
                    return obj.getStatus();
                }
            }
        }
        return null;
    }
}
