package com.jing.jacob.tts.constants;

/**
 * 错误编码
 *
 * @author jingjq
 * @version 2018-01-02 10:10:07
 */
public enum ErrorType implements ErrorCode {

    SYSTEM_ERROR("1000", "系统错误"),
    APP_ERROR("1001", "应用错误"),
    BIZ_ERROR("1002", "业务错误:%s"),
    PARAM_NOT_VALID("1007", "参数校验失败:%s"),

    BIZ_SUCCESS("8888", "成功");

    private String code;
    private String msg;

    ErrorType(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMsg() {
        return this.msg;
    }

}
