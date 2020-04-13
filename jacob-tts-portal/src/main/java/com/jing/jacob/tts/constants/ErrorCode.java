package com.jing.jacob.tts.constants;

import java.io.Serializable;

/**
 * 错误编码公共接口
 * 
 * @author jingjq
 * @version 2017-11-29 10:12:07
 */
public interface ErrorCode extends Serializable {

    /**
     * 错误码
     * 
     * @return
     */
    String getCode();

    /**
     * 错误信息
     * 
     * @return
     */
    String getMsg();

}
