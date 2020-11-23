package com.yjj.util.property;


/**
 * 日志操作记录类,持久化日志
 */
public class OperationLogger {
    private String operation_ip;//操作人ip
    private String sysUser_name;//操作人名称
    private String operation_time;//操作时间
    private String operation_record;//操作记录


    public String getOperation_ip() {
        return operation_ip;
    }

    public void setOperation_ip(String operation_ip) {
        this.operation_ip = operation_ip;
    }

    public String getSysUser_name() {
        return sysUser_name;
    }

    public void setSysUser_name(String sysUser_name) {
        this.sysUser_name = sysUser_name;
    }

    public String getOperation_time() {
        return operation_time;
    }

    public void setOperation_time(String operation_time) {
        this.operation_time = operation_time;
    }

    public String getOperation_record() {
        return operation_record;
    }

    public void setOperation_record(String operation_record) {
        this.operation_record = operation_record;
    }

    @Override
    public String toString() {
        return operation_ip+":"+sysUser_name+":"+operation_time+":"+operation_record;
    }
}
