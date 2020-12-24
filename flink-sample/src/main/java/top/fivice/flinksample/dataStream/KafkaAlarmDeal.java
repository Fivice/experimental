package top.fivice.flinksample.dataStream;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.RichFlatMapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.SourceFunction;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.util.Collector;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import top.fivice.flinksample.common.utils.KafkaProducer;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author wubin
 */
@Component
public class KafkaAlarmDeal {
    @Autowired
    KafkaProducer kafkaProducer;

    @Value("${cncc.alarm.topic}")
    private String topic;

    @Value("${spring.kafka.consumer.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;

    private static final Logger log = LoggerFactory.getLogger(KafkaAlarmDeal.class);

    public void run(){
        System.out.println("<================KafkaAlarmDeal=================>");
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers",bootstrapServers);
        properties.setProperty("group.id",groupId);
        properties.setProperty("max.poll.records","5000");

        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");


        DataStream<String> dataStream = env.addSource(
                new FlinkKafkaConsumer<String>(
                        topic ,
                        new SimpleStringSchema(),
                        properties
                )
        ).setParallelism(1).name("测试");

        DataStream<Map<String,Object>> dataStream4Format = dataStream.flatMap(new RichFlatMapFunction<String, Map<String,Object>>() {
            @Override
            public void flatMap(String s, Collector<Map<String, Object>> collector) throws Exception {
                log.info("标准化处理："+s);
                Map<String,Object> map = new HashMap<>();
                map.put("Step","标准化");
                map = JSONObject.parseObject(s,new TypeReference<Map<String,Object>>(){});

                collector.collect(map);
            }
        }).setParallelism(4).name("标准化");


        try {
            env.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
