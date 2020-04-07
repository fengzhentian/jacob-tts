package com.jing.jacob.tts.controller;

import com.jing.jacob.tts.TextToSpeech;
import com.jing.jacob.tts.properties.TtsProperties;
import it.sauronsoftware.jave.AudioUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.File;
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

        File directory = new File(properties.getFilepath());
        if (!directory.exists()) {
            directory.mkdirs();
        }

        TextToSpeech textToSpeech = new TextToSpeech(text);
        String wavFilename = now + ".wav";
        String mp3Filename = now + ".mp3";
        String wavFilepath = String.join("/", properties.getFilepath(), wavFilename);

        File wavFile = new File(String.join("/", properties.getFilepath(), wavFilename));
        File mp3File = new File(String.join("/", properties.getFilepath(), mp3Filename));

        textToSpeech.store(wavFilepath);

        if (wavFile.exists()) {
            AudioUtils.amrToMp3(wavFile, mp3File);
            wavFile.delete();
        }

        return String.join("/", properties.getUrlPath(), mp3Filename);
    }

}
