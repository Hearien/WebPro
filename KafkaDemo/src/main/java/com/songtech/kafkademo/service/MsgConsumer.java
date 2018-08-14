package com.songtech.kafkademo.service;


import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.kafka.listener.config.ContainerProperties;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 使用spring kafka消费者
 */
@Service
public class MsgConsumer {
//    @KafkaListener(topics = {"topic-1","topic-2"})
//    public void processMessage(String content) {
//
//        System.out.println("消息被消费"+content);
//    }

    public void consume(String topic,ConsumerFactory<String,String> consumerFactory){
        /**
         * 1.装配一个 HashMap ，定义实例化 KafkaConsumer 的配置参数。
         */
//        Map<String,Object> props= new HashMap<>();
//        props.put ("bootstrap.servers","127.0.0.1:9092");
//        props.put ("group.id","test");
//        props.put ("client.id","test");
//        props.put ("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer") ;
//        props.put ("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer") ;
//        /**
//         * 2.装配消费者工厂，以构造器注入方式指定消费者配置参数，消费者工厂负责消费者的创建
//         */
//        ConsumerFactory<String,String> consumerFactory = new DefaultKafkaConsumerFactory<String,String>(props);
        /**
         * 3.装配一个容器配置的 Bean ，以构造器注入的方式指定消费者所消费的主题。
         */
        ContainerProperties containerProperties = new ContainerProperties(topic);

        /**
         * 4.装配一个自定义的消息监听器，该监昕器实现消费者具体业务逻辑。
         */
        /**
         * 一、多线程多消费者，属于同一个消费者组
         */
        containerProperties.setMessageListener(new MessageListener<String,String>() {
            @Override
            public void onMessage(ConsumerRecord<String, String> data) {
                if(null != data){
                    System.out.println(Thread.currentThread().getId()+"主题："+data.topic()+",消息："+data.value());
                }
            }
        });
        ConcurrentMessageListenerContainer messageListenerContainer = new ConcurrentMessageListenerContainer(consumerFactory,containerProperties);
        messageListenerContainer.setConcurrency(3);
        messageListenerContainer.start();

        /**
         * 二、单线程，单消费者
         */
//        Consumer<String,String> consumer = consumerFactory.createConsumer();
//        consumer.subscribe(Arrays.asList(topic));
//        try{
//            while (true){
//                ConsumerRecords<String,String> records = consumer.poll(1000);
//                for (ConsumerRecord<String,String> record:records) {
//                    System.out.println(Thread.currentThread().getId()+"主题："+record.topic()+",消息："+record.value()+",key:"+record.key()+",分区："+record.partition()+",偏移量："+record.offset());
//                }
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }finally {
//            consumer.close();
//        }
    }

}
