package com.jing.jacob.tts.controller;

import com.jing.jacob.tts.TextToSpeech;
import com.jing.jacob.tts.properties.TtsProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @ClassName: TextToSpeechRestController.java
 * @Description:
 * @version: v1.0.0
 * @author: jingjq
 * @date: 2020-04-05 23:44
 */
@RestController
@RequestMapping("/api/tts")
public class TextToSpeechRestController {

    @Resource
    private TtsProperties properties;

    @GetMapping("/text2audio")
    public String text2audio(@RequestParam String text) {
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));

        TextToSpeech textToSpeech = new TextToSpeech(text);
        String filename = now + ".wav";
        textToSpeech.store(String.join("/", properties.getFilepath(), filename));

        return String.join("/", properties.getUrlPath(), filename);
    }

}
