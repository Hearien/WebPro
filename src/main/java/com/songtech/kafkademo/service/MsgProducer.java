package com.songtech.kafkademo.service;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.ProducerListener;
import org.springframework.stereotype.Service;

/**
 * 由spring容器管理生产者，线程安全，而且多个线程公用一个KafkaProducer 实例比每个线程各自实例化一个KafkaProducer性能要好，因此
 * DefaultKafkaProducerFactory 以单例模式实例化了一个KafkaProducer
 */
@Service
public class MsgProducer {
    private static final Logger log = LoggerFactory.getLogger(MsgProducer.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String topicName, String jsonData) {
        log.info("向kafka推送数据:[{}]", jsonData);
        try {
            kafkaTemplate.send(topicName, jsonData);
        } catch (Exception e) {
            log.error("发送数据出错！！！{}{}", topicName, jsonData);
            log.error("发送数据出错=====>", e);
        }

        //消息发送的监听器，用于回调返回信息
        kafkaTemplate.setProducerListener(new ProducerListener<String, String>() {
            @Override
            public void onSuccess(String topic, Integer partition, String key, String value, RecordMetadata recordMetadata) {
                log.info("数据发送success");
            }

            @Override
            public void onError(String topic, Integer partition, String key, String value, Exception exception) {
                log.info("数据发送failed");
            }

            @Override
            public boolean isInterestedInSuccess() {
                log.info("数据发送完毕");
                return false;
            }
        });
    }

}
