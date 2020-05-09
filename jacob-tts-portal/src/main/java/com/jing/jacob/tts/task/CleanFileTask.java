package com.jing.jacob.tts.task;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import com.jing.jacob.tts.properties.TtsProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 清理文件任务
 * <p>
 * Copyright: Copyright (c) 2020 zteits
 *
 * @ClassName: CleanFileTask.java
 * @Description:
 * @version: v1.0.0
 * @author: jingjq
 * @date: 2020年05月09日 14:18
 */
@Slf4j
@Component
public class CleanFileTask {

    @Resource
    private TtsProperties properties;

    /**
     * 清理文件任务<br>
     * 每天0点执行一次
     */
    @Scheduled(cron = "0 0 0 * * ?")
    public void doCleanFileTask() {
        // limit min value
        if (properties.getRetentionDays() <= 0) {
            return;
        }

        String prefix = DateUtil.date()
                .offset(DateField.DAY_OF_YEAR, -properties.getRetentionDays())
                .toString("yyyyMMdd");
        log.info("处理[{}]之前的图片文件夹", prefix);

        File dirFile = FileUtil.file(properties.getFilepath());

        List<File> fileList = new ArrayList<>();

        File[] dateDirs = dirFile.listFiles();
        if (dateDirs == null) {
            return;
        }

        for (File dateDir : dateDirs) {
            if (!dateDir.isDirectory()) {
                continue;
            }
            if (prefix.compareTo(dateDir.getName()) > 0) {
                fileList.add(dateDir);
            }
        }
        log.info("待处理图片文件夹列表：{}", fileList);
        for (File file : fileList) {
            FileUtil.del(file);
        }
    }

}
