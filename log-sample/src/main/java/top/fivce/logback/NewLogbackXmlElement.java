package top.fivce.logback;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author wubin
 */
public class NewLogbackXmlElement {

    private String appenderName;
    private String file;
    private String fileNamePattern;
    private String maxHistory;
    private String maxFileSize;
    private String pattern;

    private String loggerName;
    private String level;

    private NewLogbackXmlElement(){
        Properties properties = new Properties();
        try (InputStream inputStream = loadConfig()) {
            properties.load(inputStream);
            this.file = properties.getProperty("logback.configuration.appender.file");
            this.fileNamePattern = properties.getProperty("logback.configuration.appender.rollingPolicy.FileNamePattern");
            this.maxHistory = properties.getProperty("logback.configuration.appender.rollingPolicy.MaxHistory");
            this.maxFileSize = properties.getProperty("logback.configuration.appender.rollingPolicy.MaxFileSize");
            this.pattern = properties.getProperty("logback.configuration.appender.encoder.pattern");
            this.level = properties.getProperty("logback.configuration.logger.level");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static class NewLogbackXmlElementHolder{
        private static final NewLogbackXmlElement INSTANCE = new NewLogbackXmlElement();
    }

    public static NewLogbackXmlElement getInstance(){
        return NewLogbackXmlElementHolder.INSTANCE;
    }

    /**
     * 加载配置文件
     * @return
     */
    private static InputStream loadConfig(){
        //寻找标准配置文件并读取
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("logback-element.properties");
        if (inputStream == null){
            //无标准文件时则加载base.xml文件
            inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("base-element.properties");
        }
        return inputStream;
    }

    public String getAppenderName() {
        return appenderName;
    }

    public void setAppenderName(String appenderName) {
        this.appenderName = appenderName;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getFileNamePattern() {
        return fileNamePattern;
    }

    public void setFileNamePattern(String fileNamePattern) {
        this.fileNamePattern = fileNamePattern;
    }

    public String getMaxHistory() {
        return maxHistory;
    }

    public void setMaxHistory(String maxHistory) {
        this.maxHistory = maxHistory;
    }

    public String getMaxFileSize() {
        return maxFileSize;
    }

    public void setMaxFileSize(String maxFileSize) {
        this.maxFileSize = maxFileSize;
    }

    public String getLoggerName() {
        return loggerName;
    }

    public void setLoggerName(String loggerName) {
        this.loggerName = loggerName;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }
}
