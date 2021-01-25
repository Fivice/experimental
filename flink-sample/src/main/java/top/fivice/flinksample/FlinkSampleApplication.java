package top.fivice.flinksample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import top.fivice.flinksample.dataStream.SocketAlarmDeal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author wubin
 */
@SpringBootApplication
@EnableScheduling
public class FlinkSampleApplication {

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext context = SpringApplication.run(FlinkSampleApplication.class, args);
        SocketAlarmDeal socketAlarmDeal = context.getBean(SocketAlarmDeal.class);
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(socketAlarmDeal);
    }

}
