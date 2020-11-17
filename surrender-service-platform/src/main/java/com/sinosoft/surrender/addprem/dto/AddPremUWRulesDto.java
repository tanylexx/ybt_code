package com.sinosoft.surrender.addprem.dto;

public class AddPremUWRulesDto {
    private String calCode;
    private String sql;
    private String remark;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCalCode() {
        return calCode;
    }

    public void setCalCode(String calCode) {
        this.calCode = calCode;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
