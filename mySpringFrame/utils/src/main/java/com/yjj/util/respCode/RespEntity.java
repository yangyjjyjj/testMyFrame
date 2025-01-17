package com.yjj.util.respCode;

import lombok.Data;

@Data
public class RespEntity {

    private int code;
    private String msg;
    private Object data;

    public RespEntity(RespCode respCode){
        this.code = respCode.getCode();
        this.msg = respCode.getMsg();
    }

    public RespEntity(RespCode respCode, Object data){
        this(respCode);
        this.data = data;
    }

}
