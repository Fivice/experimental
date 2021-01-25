package top.fivice.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * @author wubin
 */
public class ThreadImplCallable implements Callable<Map<String,Object>> {
    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public Map<String,Object> call() throws Exception {
        Map<String,Object> map = new HashMap<>();
        map.put("threadName",Thread.currentThread().getName());
        return map;
    }
}
