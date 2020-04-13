package com.jing.jacob.tts.utils;

import com.jing.jacob.tts.constants.ErrorCode;
import com.jing.jacob.tts.constants.ErrorType;
import com.jing.jacob.tts.vo.ResultVO;

/**
 * 统一返回数据工具类
 * <p>
 * Copyright: Copyright (c) 2018 zteits
 *
 * @ClassName: BizResultUtil.java
 * @Description:
 * @version: v1.0.0
 * @author: jingjq
 * @date: 2018年09月12日 17:52
 */
public class ResultUtil {

    public static <T> ResultVO<T> success() {
        return new ResultVO<>(ErrorType.BIZ_SUCCESS);
    }

    public static <T> ResultVO<T> success(T data) {
        ResultVO<T> result = success();
        result.setData(data);
        return result;
    }

    public static <T> ResultVO<T> error(ErrorCode errorCode) {
        return new ResultVO<>(errorCode);
    }

    public static <T> ResultVO<T> error(ErrorCode errorCode, String msg) {
        return new ResultVO<>(errorCode, msg);
    }

    public static <T> ResultVO<T> error(String msg) {
        return new ResultVO<>(ErrorType.BIZ_ERROR, msg);
    }

    public static <T> ResultVO<T> error(ErrorCode errorCode, Throwable t) {
        return new ResultVO<>(errorCode);
    }

    public static <T> ResultVO<T> error(ErrorCode errorCode, String msg,
                                        Throwable t) {
        return new ResultVO<>(errorCode, msg);
    }

}
