package com.songtech.kafkademo.service;

import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 手动实现消费者
 */
@Service
public class ConsumerService {

    public void consum(String topic,KafkaConsumer<String,String> consumer){


        //可以订阅多个主题，如Arrays.asList("t1","t2","t3")
        consumer.subscribe(Arrays.asList(topic), new ConsumerRebalanceListener() {
            @Override
            public void onPartitionsRevoked(Collection<TopicPartition> collection) {
                consumer.commitAsync();
            }

            @Override
            public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
                long cornrnittedOffset = -1;
                for (TopicPartition topicPartition : partitions) {
                    //获取该分区已消费的偏移量
                    cornrnittedOffset = consumer.committed(topicPartition).offset();
                    //重置偏移量到上一次提交的偏移量下 个位置处开始消费
                    consumer.seek(topicPartition, cornrnittedOffset + 1);
                }
            }
        });
//        consumer.assign(Arrays.asList(new TopicPartition(topic,0)));//订阅主题的特定分区
        try{
            while (true){
                ConsumerRecords<String,String> records = consumer.poll(1000);
                for (ConsumerRecord<String,String> record:records) {
                    System.out.println("主题："+record.topic()+",消息："+record.value()+",key:"+record.key()+",分区："+record.partition()+",偏移量："+record.offset());
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            consumer.close();
        }
    }

    /**
     * 取消订阅
     * @param consumer
     */
    public void unSubscribe(KafkaConsumer<String,String> consumer){
        Map<String,List<PartitionInfo>> map = consumer.listTopics();
        if(null != map){

        }
    }
}
