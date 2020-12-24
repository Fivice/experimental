package top.fivice.flinksample.dataStream;

import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author wubin
 */
@Component
public class SocketAlarmDealBak implements Runnable{
    private static final Logger log = LoggerFactory.getLogger(KafkaAlarmDeal.class);
    @Override
    public void run() {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStream<String> dataStream = env.socketTextStream("localhost",7778);
        dataStream.print("7778");

        try {
            env.execute("SocketAlarmDealBak");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
