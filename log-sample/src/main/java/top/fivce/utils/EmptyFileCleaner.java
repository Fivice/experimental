package top.fivce.utils;

import java.util.concurrent.*;

/**
 * @author wubin
 */
public class EmptyFileCleaner {

    private static final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor(new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setName("EmptyFileCleaner");
            thread.setDaemon(true);
            return thread;
        }
    });

    private EmptyFileCleaner(){
        //在线程启动一分钟后执行，每次任务结束后等待5分钟再次执行任务
        executorService.scheduleWithFixedDelay(new Cleaner(),1,5, TimeUnit.MINUTES);
    }

    private static class EmptyFileCleanerHolder{
        private static final EmptyFileCleaner INSTANCE = new EmptyFileCleaner();
    }

    public static void start(){
        EmptyFileCleaner emptyFileCleaner = EmptyFileCleanerHolder.INSTANCE;
    }

     private static class Cleaner implements Runnable{
         @Override
         public void run() {
             System.out.println("Cleaner.run");
         }
     }
}
