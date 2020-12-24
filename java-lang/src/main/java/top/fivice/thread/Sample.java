package top.fivice.thread;

public class Sample {
    public static void main(String[] args) throws InterruptedException {
        ScheduleThreadSample scheduleThreadSample = ScheduleThreadSample.getInstance();
        scheduleThreadSample.start();

        Thread.sleep(100000);
        scheduleThreadSample.shutdown();
    }
}
