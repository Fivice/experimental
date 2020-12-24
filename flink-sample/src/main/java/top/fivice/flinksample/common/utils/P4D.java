package top.fivice.flinksample.common.utils;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import top.fivice.flinksample.common.dataTypes.Alarm;

import java.time.ZonedDateTime;
import java.util.Date;

@Component
public class P4D{

    @Autowired
    KafkaProducer kafkaProducer;

    @Scheduled(cron = "0/5 * * * * ?")
    public void p4d(){
        Alarm alarm = new Alarm();
        alarm.setTitle("测试："+ZonedDateTime.now());
        alarm.setStatus("test");
        alarm.setCreateTime(new Date());

        kafkaProducer.send("Test_Wu", JSONObject.toJSONString(alarm));
    }
}
