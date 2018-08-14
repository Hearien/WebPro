package com.songtech.kafkademo.thread;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Map;
import java.util.Properties;

public class KafkaConsumerThread implements Runnable {
//    每个线程拥有私有的 KafkaConsumer 实例
    private KafkaConsumer<String, String> consumer;
    public KafkaConsumerThread(String topic) {
        Properties props= new Properties ();
        props.put ("bootstrap.servers","127.0.0.1:9092");
        props.put ("group.id","test");
        props.put ("client.id","test");
        props.put ("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer") ;
        props.put ("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer") ;
        this.consumer = new KafkaConsumer<String, String>(props);
        consumer.subscribe (Arrays.asList (topic));
    }

    @Override
    public void run() {
        try{
            while(true){
                ConsumerRecords<String,String> records = consumer.poll(1000);
                for (ConsumerRecord<String,String> record:records) {
                    System.out.println(Thread.currentThread().getId()+"主题："+record.topic()+",消息："+record.value()+",key:"+record.key()+",分区："+record.partition()+",偏移量："+record.offset());
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            consumer.close();
        }
    }
}
