package com.jing.jacob.tts.vo;

import com.jing.jacob.tts.constants.ErrorCode;
import com.jing.jacob.tts.constants.ErrorType;
import lombok.Data;

/**
 * 前台统一返回vo
 * <p>
 * Copyright: Copyright (c) 2020 zteits
 *
 * @ClassName: ResultVO.java
 * @Description:
 * @version: v1.0.0
 * @author: jingjq
 * @date: 2020年04月13日 9:03
 */
@Data
public class ResultVO<T> {

    /**
     * 状态码:8888(成功);1000(系统错误);1002(业务错误);1006(访问的url不存在),1007(请求参数不正确)
     */
    private String code;
    /**
     * 错误信息
     */
    private String msg;
    /**
     * 业务数据
     */
    private T data;

    public ResultVO() {
        super();
    }

    public ResultVO(ErrorCode errorCode) {
        super();
        this.code = errorCode.getCode();
        this.msg = errorCode.getMsg();
    }

    public ResultVO(ErrorCode errorCode, String msg) {
        super();
        this.code = errorCode.getCode();
        this.msg = msg;
    }

    public ResultVO(T data) {
        super();
        this.data = data;
        if (isBlank(code)) {
            this.code = ErrorType.BIZ_SUCCESS.getCode();
        }
        if (isBlank(msg)) {
            this.msg = ErrorType.BIZ_SUCCESS.getMsg();
        }
    }

    private boolean isBlank(String str) {
        return str == null || str.length() == 0;
    }

}
