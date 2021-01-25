package top.fivce.logback;

import org.slf4j.Logger;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wubin
 */
public class LoggerCache {
    private final Map<String,Object> logCache;

    private LoggerCache(){
        logCache = new HashMap<>();
    };

    private static class LoggerCacheHolder{
        private static final LoggerCache INSTANCE = new LoggerCache();
    }

    public static LoggerCache getInstance(){
        return LoggerCacheHolder.INSTANCE;
    }

    public boolean checkLogger(String loggerName){
        LoggerCache loggerCache = LoggerCache.getInstance();
        return loggerCache.logCache.containsKey(loggerName);
    }

    public void putLogger(String loggerName, Logger logger){
        LoggerCache loggerCache = LoggerCache.getInstance();
        loggerCache.logCache.put(loggerName,logger);
    }
}
