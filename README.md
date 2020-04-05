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

将`dll`文件夹正面的`jacob-1.18-x64.dll`复制到部署项目的jdk目录下

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