package top.fivce;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.util.StatusPrinter;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.fivce.logback.LogbackConfig;
import top.fivce.logback.LoggerCache;
import top.fivce.logback.NewLogbackXmlElement;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

/**
 * @author wubin
 */
public class LoggerBuilder {

    public static Logger getLogger(String logName){
        //检查LoggerCache中是否有缓存，有则直接获取，没有则根据新的logName重新初始化配置
        Logger logger = null;
        LoggerCache loggerCache = LoggerCache.getInstance();
        if (loggerCache.checkLogger(logName)){
            return LoggerFactory.getLogger(logName);
        }else {
            //根据logName重新初始化配置文件流
            try(InputStream inputStream = LogbackConfig.reInitLogbackXmlStream(logName)){
                LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
                JoranConfigurator configurator = new JoranConfigurator();
                configurator.setContext(loggerContext);
                //重置配置
                loggerContext.reset();
                //加载新配置
                configurator.doConfigure(inputStream);
                StatusPrinter.printInCaseOfErrorsOrWarnings(loggerContext);
                //获取logName对应logger
                logger = LoggerFactory.getLogger(logName);
                loggerCache.putLogger(logName,logger);
            } catch (IOException | JoranException e) {
                e.printStackTrace();
            }
        }
        return logger;
    }

    /**
     * 日志输出时带全限定类名
     * @param logName
     * @param clazz
     * @return
     */
    public static Logger getLogger(String logName,Class<?> clazz){
        //检查LoggerCache中是否有缓存，有则直接获取，没有则根据新的logName重新初始化配置
        Logger logger = null;
        LoggerCache loggerCache = LoggerCache.getInstance();
        if (loggerCache.checkLogger(logName)){
            return LoggerFactory.getLogger(logName);
        }else {
            //根据logName重新初始化配置文件流
            try(InputStream inputStream = LogbackConfig.reInitLogbackXmlStream(logName)){
                LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
                JoranConfigurator configurator = new JoranConfigurator();
                configurator.setContext(loggerContext);
                //重置配置
                loggerContext.reset();
                //加载新配置
                configurator.doConfigure(inputStream);
                StatusPrinter.printInCaseOfErrorsOrWarnings(loggerContext);
                //获取logName对应logger
                logger = LoggerFactory.getLogger(logName);
                loggerCache.putLogger(logName,logger);
            } catch (IOException | JoranException e) {
                e.printStackTrace();
            }
        }
        return logger;
    }

    /**
     * 日志输出时带全限定类名，和动态自定义标签
     * @param logName
     * @param clazz
     * @param dynamicTag
     * @return
     */
    public static Logger getLogger(String logName,Class<?> clazz,String dynamicTag){
        //检查LoggerCache中是否有缓存，有则直接获取，没有则根据新的logName重新初始化配置
        Logger logger = null;
        LoggerCache loggerCache = LoggerCache.getInstance();
        if (loggerCache.checkLogger(logName)){
            return LoggerFactory.getLogger(logName);
        }else {
            //根据logName重新初始化配置文件流
            try(InputStream inputStream = LogbackConfig.reInitLogbackXmlStream(logName)){
                LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
                JoranConfigurator configurator = new JoranConfigurator();
                configurator.setContext(loggerContext);
                //重置配置
                loggerContext.reset();
                //加载新配置
                configurator.doConfigure(inputStream);
                StatusPrinter.printInCaseOfErrorsOrWarnings(loggerContext);
                //获取logName对应logger
                logger = LoggerFactory.getLogger(logName);
                loggerCache.putLogger(logName,logger);
            } catch (IOException | JoranException e) {
                e.printStackTrace();
            }
        }
        return logger;
    }




    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            Logger logger = LoggerBuilder.getLogger("Test");
            logger.info(new Date().toString());
            Thread.sleep(5000);
        }

        Thread.sleep(50000);
        System.out.println("主线线程结束");

    }
}
