package top.fivice.thread;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class ScheduleThreadSample {
    private final ScheduledExecutorService scheduledExecutorService;
    private ScheduleThreadSample(){
        scheduledExecutorService = Executors.newScheduledThreadPool(1, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread t = Executors.defaultThreadFactory().newThread(r);
                t.setDaemon(true);
                return t;
            }
        });

    }

    private static class ScheduleThreadSampleHolder {
        public static final ScheduleThreadSample INSTANCE = new ScheduleThreadSample();
    }

    public static ScheduleThreadSample getInstance(){
        return ScheduleThreadSampleHolder.INSTANCE;
    }

    public void start(){
        scheduledExecutorService.scheduleWithFixedDelay(new Task(),0,5, TimeUnit.SECONDS);
    }
    public void shutdown(){
        scheduledExecutorService.shutdown();
    }
    private static class Task implements Runnable{

        @Override
        public void run() {
            System.out.println("Task.run...");
        }
    }

}
