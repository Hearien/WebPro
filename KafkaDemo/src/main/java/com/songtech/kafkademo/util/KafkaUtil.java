package com.songtech.kafkademo.util;

import java.util.Properties;

import kafka.admin.AdminUtils;
import kafka.admin.BrokerMetadata;
import kafka.server.ConfigType;
import kafka.utils.ZkUtils;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.security.JaasUtils;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import scala.collection.Map;
import scala.collection.Seq;

public class KafkaUtil {

    private static final Logger log = LoggerFactory.getLogger(KafkaUtil.class);

    /**连接 Zk **/
    private static final String ZK_CONNECT= "127.0.0.1:2181";
    /**session 过期时间**/
    private static final int SESSION_TIMEOUT = 30000 ;
    /**连接超时时间**/
    private static final int CONNECT_TIMEOUT = 30000 ;

    /**
     * 创建生产者
     * @param brokerList
     * @return
     */
    public static KafkaProducer createProducer(String brokerList){
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,brokerList);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());

        return new KafkaProducer<String,String>(properties);
    }

    /**
     * 创建消费者
     * @param brokeList
     * @return
     */
    public static KafkaConsumer createConsumer(String brokeList){
        Properties props= new Properties ();
        props.put ("bootstrap.servers","127.0.0.1:9092");
        props.put ("group.id","test");
        props.put ("client.id","test");
        props.put ("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer") ;
        props.put ("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer") ;
        KafkaConsumer<String , String> consumer= new KafkaConsumer<> (props);

        return consumer;
    }

    /**
     * 创建topic
     * @param topic 名称
     * @param partition 分区数
     * @param repilca 副本数量
     * @param properties 属性
     */
    public static void createTopic(String topic, int partition, int repilca, Properties properties){
        ZkUtils zkUtils = null;
        try{
            zkUtils = ZkUtils.apply(ZK_CONNECT,SESSION_TIMEOUT,CONNECT_TIMEOUT, JaasUtils.isZkSecurityEnabled());
            if(!AdminUtils.topicExists(zkUtils,topic)){
                AdminUtils.createTopic(zkUtils,topic,partition,repilca,properties,AdminUtils.createTopic$default$6());
            }else {
                log.warn("topic已存在");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            zkUtils.close();
        }
    }

    /**
     * 修改主题级别配置
     * @param topic
     * @param properties
     * @param partitions 分区数
     */
    public static void modifyTopicConfig(String topic, int partitions, Properties properties){
        ZkUtils zkUtils = null;
        try {
            zkUtils = ZkUtils.apply(ZK_CONNECT,SESSION_TIMEOUT,CONNECT_TIMEOUT, JaasUtils.isZkSecurityEnabled());
            Properties curProp = AdminUtils.fetchEntityConfig(zkUtils, ConfigType.Topic(),topic);
            if(!properties.isEmpty()){
                curProp.putAll(properties);
                AdminUtils.changeTopicConfig(zkUtils,topic,curProp);
            }
            if(partitions!=0){
                Seq<BrokerMetadata> seq = AdminUtils.getBrokerMetadatas(zkUtils,AdminUtils.getBrokerMetadatas$default$2(),AdminUtils.getBrokerMetadatas$default$3());
                /**
                 * todo ?
                 */
                Map<Object,Seq<Object>> map = AdminUtils.parseReplicaAssignment("1",partitions);
                AdminUtils.addPartitions(zkUtils,topic,map,seq,partitions,AdminUtils.addPartitions$default$6(),true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            zkUtils.close();
        }
    }

    /**
     * 删除topic
     * @param topic
     */
    public static void delTopic(String topic){
        ZkUtils zkUtils = null;
        try{
            zkUtils = ZkUtils.apply(ZK_CONNECT,SESSION_TIMEOUT,CONNECT_TIMEOUT, JaasUtils.isZkSecurityEnabled());
            if(!AdminUtils.topicExists(zkUtils,topic)){
                log.warn("topic不存在");
            }else {
                AdminUtils.deleteTopic(zkUtils,topic);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            zkUtils.close();
        }
    }

    public static void main(String[] args) {
        Properties properties = new Properties();
        //createTopic("demo1",3,1,properties);
        //modifyTopicConfig("demo1",2,properties);
        delTopic("demo");
    }
}
