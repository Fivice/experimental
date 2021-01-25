package top.fivce.logback;

import org.dom4j.*;
import org.dom4j.io.SAXReader;
import top.fivce.utils.EmptyFileCleaner;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author wubin
 */
public class LogbackConfig {

    private Document document;
    private static final String[] STANDARD_CONFIG_LOCATIONS =  new String[] { "logback-test.xml", "logback.xml", "logback-spring.xml" };

    private LogbackConfig(){
        try (InputStream inputStream = loadConfig()){
            SAXReader reader = new SAXReader();
            this.document = reader.read(inputStream);
            //起一个清理线程
            EmptyFileCleaner.start();
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }

    private static class LogbackConfigHolder{
        private static final LogbackConfig INSTANCE = new LogbackConfig();
    }

    public static LogbackConfig getInstance(){
        return LogbackConfigHolder.INSTANCE;
    }

    /**
     * 获取一个拥有logName作为logger标签的xml流信息
     * @param logName
     * @return
     */
    public static InputStream reInitLogbackXmlStream(String logName){
        LogbackConfig logbackConfig = LogbackConfig.getInstance();
        Document document = logbackConfig.document;
        //遍历document中的logger标签查找有无taskId对应的标签，如果有则直接返回当前document的inputStream，否则添加新节点后返回
        List<Element> elementList = document.selectNodes("//configuration/logger");

        for (Element element:elementList
             ) {
            //检查所有logger节点name是否有和taskId一样的标签
            Attribute attribute = element.attribute("name");
            if (logName.equals(attribute.getName())){
                return new ByteArrayInputStream(document.asXML().getBytes(StandardCharsets.UTF_8));
            }
        }
        //遍历后未找到符合条件的logger标签
        NewLogbackXmlElement xmlElement = NewLogbackXmlElement.getInstance();
        //创建新的appender节点，添加到根节点下
        createAppender(document,xmlElement,logName);
        //创建新的logger节点，添加到根节点下
        createLogger(document,xmlElement,logName);
        return new ByteArrayInputStream(document.asXML().getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 创建appender标签
     * @param xmlElement
     * @param taskId
     */
    public static void createAppender(Document document,NewLogbackXmlElement xmlElement,String taskId){
        Element root = document.getRootElement();
        Element appender = root.addElement("appender")
                .addAttribute("name",taskId.toUpperCase()+"-FILE")
                .addAttribute("class","ch.qos.logback.core.rolling.RollingFileAppender");

        appender.addElement("file").addText(xmlElement.getFile()+taskId+".log");

        Element rollingPolicy = appender.addElement("rollingPolicy")
                .addAttribute("class","ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy");

        rollingPolicy.addElement("FileNamePattern").addText(xmlElement.getFile()+taskId+"/"+xmlElement.getFileNamePattern());
        rollingPolicy.addElement("cleanHistoryOnStart").addText("true");
        rollingPolicy.addElement("MaxHistory").addText(xmlElement.getMaxHistory());
        rollingPolicy.addElement("MaxFileSize").addText(xmlElement.getMaxFileSize());

        Element encoder = appender.addElement("encoder").addAttribute("class","ch.qos.logback.classic.encoder.PatternLayoutEncoder");
        encoder.addElement("pattern").addText(xmlElement.getPattern());
    }

    /**
     * 创建logger标签
     */
    public static void createLogger(Document document,NewLogbackXmlElement xmlElement,String taskId){
        Element root = document.getRootElement();
        Element logger = root.addElement("logger")
                .addAttribute("name",taskId)
                .addAttribute("level",xmlElement.getLevel())
                .addAttribute("additivity","false");
        logger.addElement("appender-ref")
                .addAttribute("ref",taskId.toUpperCase()+"-FILE");
    }

    /**
     * 加载配置文件
     * @return
     */
    private static InputStream loadConfig(){
        //寻找标准配置文件并读取
        for (String cnf: STANDARD_CONFIG_LOCATIONS
        ) {
            InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(cnf);
            if (inputStream!=null){
                return inputStream;
            }
        }
        //无标准文件时则加载base.xml文件
        return Thread.currentThread().getContextClassLoader().getResourceAsStream("base.xml");
    }

}
