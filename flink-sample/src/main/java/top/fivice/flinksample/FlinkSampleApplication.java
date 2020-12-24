package top.fivice.flinksample;

import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import top.fivice.flinksample.dataStream.KafkaAlarmDeal;
import top.fivice.flinksample.dataStream.SocketAlarmDeal;
import top.fivice.flinksample.dataStream.SocketAlarmDealBak;

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

        KafkaAlarmDeal kafkaAlarmDeal =  context.getBean(KafkaAlarmDeal.class);

        SocketAlarmDeal socketAlarmDeal = context.getBean(SocketAlarmDeal.class);

        SocketAlarmDealBak socketAlarmDealBak = context.getBean(SocketAlarmDealBak.class);

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        executorService.execute(socketAlarmDeal);

        executorService.execute(socketAlarmDealBak);

    }

}
