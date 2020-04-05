# jacob-tts
采用jacob，实现文本转语音

## 环境依赖

### 添加maven依赖

```xml
<!-- https://mvnrepository.com/artifact/com.jacob/jacob 文字转语音 -->
<dependency>
  <groupId>com.hynnet</groupId>
  <artifactId>jacob</artifactId>
  <version>1.18</version>
</dependency>
```

### 复制dll文件

根据项目部署环境的差异，复制对应的dll库

比如采用的jdk 64版本，则将`lib`文件夹正面的`jacob-1.18-x64.dll`复制到部署项目的jdk目录下

## 示例

### 语音播放

```java
TextToSpeech textToSpeech = new TextToSpeech("吃葡萄不吐葡萄皮");
textToSpeech.play();
```

### 保存到语音文件

```java
TextToSpeech textToSpeech = new TextToSpeech("吃葡萄不吐葡萄皮");
textToSpeech.store("./text.wav");
```

### 通过api接口生成视频文件

```
http://localhost:8080/api/tts/text2audio?text=%E5%90%83%E8%91%A1%E8%90%84%E4%B8%8D%E5%90%90%E8%91%A1%E8%90%84%E7%9A%AE
```