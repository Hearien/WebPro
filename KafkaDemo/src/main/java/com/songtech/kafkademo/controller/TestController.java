package com.songtech.kafkademo.controller;


import com.songtech.kafkademo.service.ConsumerService;
import com.songtech.kafkademo.service.MsgConsumer;
import com.songtech.kafkademo.service.MsgProducer;
import com.songtech.kafkademo.service.Producer;
import com.songtech.kafkademo.thread.KafkaConsumerThread;
import com.songtech.kafkademo.thread.KafkaProducerThread;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.sf.json.JSONObject;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@Api(tags = "测试控制层")
@RequestMapping("/api/test")
public class TestController {

    @Autowired
    private MsgProducer msgProducer;

    @Autowired
    private ConsumerService consumerService;/*手动实现*/

    @Autowired
    private MsgConsumer msgConsumer;/*通过shipring实现*/

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;/*通过spring实现*/

    @Autowired
    private Producer producer;/*手动实现*/

    private static final int THREADS_NUMS = 10;
    private static final int MESSAGE_SIZE = 100;
    private static ConsumerFactory<String,String> consumerFactory;

    static{
        Map<String,Object> props= new HashMap<>();
        props.put ("bootstrap.servers","127.0.0.1:9092");
        props.put ("group.id","test");
        props.put ("client.id","test");
        props.put ("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer") ;
        props.put ("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer") ;
        /**
         * 2.装配消费者工厂，以构造器注入方式指定消费者配置参数，消费者工厂负责消费者的创建
         */
        consumerFactory = new DefaultKafkaConsumerFactory<String,String>(props);
    }

    @ApiOperation(value="获取用户列表", notes="获取用户列表")
    @RequestMapping(value = "test1", method = RequestMethod.POST)
    public String test(@ApiParam(value = "参数") @RequestBody String data){
        return data;
    }

    @ApiOperation(value="测试producer", notes="测试producer")
    @RequestMapping(value = "testproducer", method = RequestMethod.POST)
    public String testproducer(@ApiParam(value = "参数") @RequestBody String data){
        try{
            JSONObject dataJson = JSONObject.fromObject(data);
            producer.sendMsg(dataJson.getString("topic"), dataJson.getString("msg"));
            return "success";
        }catch (Exception e){
            return e.toString();
        }
    }

    /**
     *
     * @param data {"topic":"","msg":""}
     * @return
     */
    @ApiOperation(value="消息发送测试", notes="消息发送测试")
    @RequestMapping(value = "prdmsg", method = RequestMethod.POST)
    public String prdMsg(@ApiParam(value = "参数") @RequestBody String data){
        try{
            JSONObject dataJson = JSONObject.fromObject(data);
            msgProducer.sendMessage(dataJson.getString("topic"), dataJson.getString("msg"));
            return "success";
        }catch (Exception e){
            return e.toString();
        }
    }

    @ApiOperation(value="多线程消息发送测试", notes="多线程消息发送测试")
    @RequestMapping(value = "prdmsgthread", method = RequestMethod.POST)
    public String threadPrdMsg(@ApiParam(value = "参数") @RequestBody String data){
        JSONObject dataJson = JSONObject.fromObject(data);
        String topic = dataJson.getString("topic");
        String msg = dataJson.getString("msg");
        ProducerRecord<String, String> record = new ProducerRecord<String,String>(topic,msg);
        ExecutorService executor= Executors.newFixedThreadPool(THREADS_NUMS);
        try{
            for(int i = 0; i < MESSAGE_SIZE; i++){
                record = new ProducerRecord<String,String>(topic,msg+i);
                executor.submit(new KafkaProducerThread(kafkaTemplate,record));
            }

            return "success";
        }catch (Exception e){
            return e.toString();
        }finally {
            executor.shutdown();
        }
    }

    /**
     *
     * @param data 所要订阅的主题
     * @return
     */
    @ApiOperation(value="多线程消费者订阅", notes="多线程消费者订阅")
    @RequestMapping(value = "threadconsume", method = RequestMethod.POST)
    public String threadConsume(@ApiParam(value = "参数") @RequestBody String data){
//        ExecutorService executor= Executors.newFixedThreadPool(5);
//        try{
//            executor.submit(new KafkaConsumerThread(data));
//            return "success";
//        }catch (Exception e){
//            e.printStackTrace();
//            return "false";
//        }finally {
//            executor.shutdown();
//        }
        for(int i = 0; i < 5 ; i++){
            new KafkaConsumerThread(data).run();
        }
        return "success";
    }

    @ApiOperation(value="消费者订阅", notes="消费者订阅")
    @RequestMapping(value = "consume", method = RequestMethod.POST)
    public String consume(@ApiParam(value = "参数") @RequestBody String data){
        try{
//            Properties props= new Properties ();
//            props.put ("bootstrap.servers","127.0.0.1:9092");
//            props.put ("group.id","test");
//            props.put ("client.id","test");
//            props.put ("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer") ;
//            props.put ("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer") ;
//            KafkaConsumer<String , String> consumer= new KafkaConsumer<> (props);
//            consumerService.consum(data,consumer);
            msgConsumer.consume(data,consumerFactory);
            return "success";
        }catch (Exception e){
            e.printStackTrace();
            return "false";
        }
    }

    @ApiOperation(value="取消订阅", notes="取消订阅")
    @RequestMapping(value = "unsub", method = RequestMethod.POST)
    public String unsub(@ApiParam(value = "参数") @RequestBody String data){
        try{

            return "success";
        }catch (Exception e){
            e.printStackTrace();
            return "false";
        }
    }
}
