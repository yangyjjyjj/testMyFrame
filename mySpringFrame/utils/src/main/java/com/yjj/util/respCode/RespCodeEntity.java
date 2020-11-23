package com.yjj.util.respCode;

import lombok.Data;

@Data
public class RespCodeEntity {

    private int code;
    private String msg;

    public RespCodeEntity(RespCode respCode){
        this.code = respCode.getCode();
        this.msg = respCode.getMsg();
    }
}
