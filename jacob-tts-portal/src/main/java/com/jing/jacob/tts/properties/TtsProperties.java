package com.jing.jacob.tts.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * tts配置文件
 *
 * @ClassName: TtsProperties.java
 * @Description:
 * @version: v1.0.0
 * @author: jingjq
 * @date: 2020-04-05 23:56
 */
@Data
@Component
@ConfigurationProperties(prefix = TtsProperties.PREFIX)
public class TtsProperties {
    public static final String PREFIX = "tts";

    /**
     * 文件夹路径
     */
    private String filepath;
    /**
     * 请求访问路径
     */
    private String urlPath;
    /**
     * 音频访问地址
     */
    private String audioAccessPath;
    /**
     * 文件保留天数（-1：不清理）
     */
    private int retentionDays;
}
