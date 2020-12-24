package top.fivice.flinksample.common.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import javax.annotation.Resource;

/**
 * @author wubin
 */
@Component
public class KafkaProducer {
    @Resource
    private KafkaTemplate<Object,Object> kafkaTemplate;

    public ListenableFuture<SendResult<Object, Object>> send(String topic , Object data){
        ListenableFuture<SendResult<Object, Object>> listenableFuture = kafkaTemplate.send(topic,data);
        kafkaTemplate.flush();
        return listenableFuture;
    }
    public ListenableFuture<SendResult<Object, Object>> send(String topic , Object key , Object data){
        ListenableFuture<SendResult<Object, Object>> listenableFuture = kafkaTemplate.send(topic,key,data);
        kafkaTemplate.flush();
        return listenableFuture;
    }

    public ListenableFuture<SendResult<Object, Object>> send(String topic ,int partition , Object data){
        ListenableFuture<SendResult<Object, Object>> listenableFuture = kafkaTemplate.send(topic,partition,data);
        kafkaTemplate.flush();
        return listenableFuture;
    }
}
