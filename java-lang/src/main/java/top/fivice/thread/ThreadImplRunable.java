package top.fivice.thread;


/**
 * @author wubin
 */
public class ThreadImplRunable implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" 执行线程run");
    }
}
