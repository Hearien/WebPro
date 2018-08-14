package com.songtech.kafkademo.thread;

import com.songtech.kafkademo.service.MsgProducer;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.ProducerListener;

import java.util.Random;

public class KafkaProducerThread implements Runnable {

    private static final Logger log = LoggerFactory.getLogger(KafkaProducerThread.class);
    private KafkaTemplate<String, String> kafkaTemplate;
    private ProducerRecord<String, String> record = null;

    public KafkaProducerThread(KafkaTemplate<String, String> kafkaTemplate,ProducerRecord<String, String> record){
        this.kafkaTemplate = kafkaTemplate;
        this.record = record;
    }

    @Override
    public void run() {
        log.info("向kafka推送数据:[{}],当前线程：[{}]", record.value(),Thread.currentThread().getId());
        try {
            /**
             * 随机指定分区
             */
//            Random r = new Random();
//            int partion = r.nextInt(3);
            kafkaTemplate.send(record.topic(), record.value());
        } catch (Exception e) {
            log.error("发送数据出错！！！{}{}", record.topic(), record.value());
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
