package com.jing.jacob.tts.config;

import com.jing.jacob.tts.properties.TtsProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.Resource;

/**
 * 音频访问路径映射
 *
 * @ClassName: AudioAccessPathConfig.java
 * @Description:
 * @version: v1.0.0
 * @author: jingjq
 * @date: 2020-04-06 0:09
 */
@Configuration
public class AudioAccessPathConfig extends WebMvcConfigurerAdapter {

    @Resource
    private TtsProperties properties;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(properties.getAudioAccessPath())
                .addResourceLocations("file:" + properties.getFilepath());
    }
}
