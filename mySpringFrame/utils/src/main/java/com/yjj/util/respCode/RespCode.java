package com.yjj.util.respCode;

public enum RespCode {

    FAILED(0, "failed"), //失败
    SUCCESS(1, "success"), //成功


    INCORRECT_FORMAT(100,"格式不正确"),

    NOT_FOUND(404,"路径未找到"),
    EXCEPTION_500(500,"check log"),

    PER_USERNAME_PASSWORD_ERR(907,"用户名或密码不正确"),
    VERIFY_CODE_MISTAKE(909,"验证码不正确"),
    PER_UN_LOGIN(906,"未登录，请登录"),
    PER_NO_PERMISSION(908,"权限不足！");
    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public String getMsg(){
        return msg;
    }

    /**
     * 定义构造方法，枚举常量就为这个样式
     * @param code
     * @param msg
     */
    RespCode(int code,String msg){
        this.code = code;
        this.msg = msg;
    }
}
