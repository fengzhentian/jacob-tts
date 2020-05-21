package com.jing.jacob.tts;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

/**
 * 文字转语音
 * jdk bin文件中需要导入jacob-1.18-x64.dll
 *
 * @ClassName: TextToSpeech.java
 * @Description:
 * @version: v1.0.0
 * @author: jingjq
 * @date: 2020-04-05 22:12
 */
public class TextToSpeech {
    /**
     * 文本信息
     */
    private String text;

    public TextToSpeech(String text) {
        this.text = text;
    }

    /**
     * 语音播放
     */
    public void play() {
        ActiveXComponent ax = null;

        try {
            ax = new ActiveXComponent("Sapi.SpVoice");

            // 运行时输出语音内容
            Dispatch spVoice = ax.getObject();
            // 音量 0-100
            ax.setProperty("Volume", new Variant(100));
            // 语音朗读速度 -10 到 +10
            ax.setProperty("Rate", new Variant(-2));
            // 执行朗读
            Dispatch.call(spVoice, "Speak", new Variant(text));

            spVoice.safeRelease();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ax != null) {
                ax.safeRelease();
            }
        }
    }

    /**
     * 语音播放
     *
     * @param text 文本信息
     */
    public static void play(String text) {
        new TextToSpeech(text).play();
    }

    /**
     * 保存到语音文件
     *
     * @param filepath 语音文件路径
     */
    public void store(String filepath) {
        ActiveXComponent voiceComponent = null;
        ActiveXComponent fileStreamComponent = null;
        ActiveXComponent audioFormatComponent = null;

        try {
            voiceComponent = new ActiveXComponent("Sapi.SpVoice");

            // 运行时输出语音内容
            Dispatch spVoice = voiceComponent.getObject();

            // 下面是构建文件流把生成语音文件

            fileStreamComponent = new ActiveXComponent("Sapi.SpFileStream");
            Dispatch spFileStream = fileStreamComponent.getObject();

            audioFormatComponent = new ActiveXComponent("Sapi.SpAudioFormat");
            Dispatch spAudioFormat = audioFormatComponent.getObject();

            // 设置音频流格式
            Dispatch.put(spAudioFormat, "Type", new Variant(22));
            // 设置文件输出流格式
            Dispatch.putRef(spFileStream, "Format", spAudioFormat);
            // 调用输出 文件流打开方法，创建一个.wav文件
            Dispatch.call(spFileStream, "Open", new Variant(filepath), new Variant(3), new Variant(true));
            // 设置声音对象的音频输出流为输出文件对象
            Dispatch.putRef(spVoice, "AudioOutputStream", spFileStream);
            // 设置音量 0到100
            Dispatch.put(spVoice, "Volume", new Variant(100));
            // 设置朗读速度
            Dispatch.put(spVoice, "Rate", new Variant(-2));
            // 开始朗读
            Dispatch.call(spVoice, "Speak", new Variant(text));

            // 关闭输出文件
            Dispatch.call(spFileStream, "Close");
            Dispatch.putRef(spVoice, "AudioOutputStream", null);

            spAudioFormat.safeRelease();
            spFileStream.safeRelease();
            spVoice.safeRelease();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (voiceComponent != null) {
                voiceComponent.safeRelease();
            }
            if (fileStreamComponent != null) {
                fileStreamComponent.safeRelease();
            }
            if (audioFormatComponent != null) {
                audioFormatComponent.safeRelease();
            }
        }
    }

    /**
     * 保存到语音文件
     *
     * @param text     文本信息
     * @param filepath 语音文件路径
     */
    public static void store(String text, String filepath) {
        new TextToSpeech(text).store(filepath);
    }

    public static void main(String[] args) {
        TextToSpeech textToSpeech = new TextToSpeech("吃葡萄不吐葡萄皮");
        textToSpeech.play();
//        textToSpeech.store("./text.wav");
    }
}
