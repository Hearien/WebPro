package com.songtech.kafkademo.service;

import com.songtech.kafkademo.util.KafkaUtil;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 手动实现生产者
 */
@Service
public class Producer {

    private static final Logger log = LoggerFactory.getLogger(Producer.class);

    @Value("${spring.kafka.bootstrap-servers}")
    private String brokerList;

    public void sendMsg(String topic,String msg){
        KafkaProducer<String,String> producer = KafkaUtil.createProducer(brokerList);
        ProducerRecord<String,String> record = new ProducerRecord<String,String>(topic,msg);
        producer.send(record, new Callback() {
            @Override
            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                if(null!=e){
                    log.error("send error",e);
                }
                if(null!=recordMetadata){
                    log.info("offset:{},partition:{}",recordMetadata.offset(),recordMetadata.partition());
                }
            }
        });
    }
}
