package top.fivice.flinksample.dataStream;

import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.SocketTextStreamFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author wubin
 */
@Component
public class SocketAlarmDeal implements Runnable{

    private static final Logger log = LoggerFactory.getLogger(KafkaAlarmDeal.class);
    @Override
    public void run() {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStream<String> dataStream = env.socketTextStream("localhost",7777);
        dataStream.print("7777");

        try {
            env.execute("SocketAlarmDeal");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
