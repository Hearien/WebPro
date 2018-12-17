package com.hearien.thrift.client;

import com.hearien.thrift.Person;
import com.hearien.thrift.PersonService;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

/**
 * @ClassName ThriftClient
 * @Author WangHaiyang
 * @Date 2018/12/17 16:21
 **/
public class ThriftClient {

    public static void main(String[] args) {
        TTransport transport = new TFramedTransport(new TSocket("localhost",8890),600);
        TProtocol protocol = new TCompactProtocol(transport);
        PersonService.Client client = new PersonService.Client(protocol);
        try {
            transport.open();
            Person person = client.getPersonByUserName("李凯");
            System.out.println(person.getUserName());
            System.out.println(person.getAge());
            System.out.println(person.marrief);
            System.out.println("---------------------------");

            Person p1 = new Person();
            p1.setMarrief(false);
            p1.setUserName("网啊");
            p1.setAge(12);
            client.savePerson(p1);
        } catch (Exception e){
            throw new RuntimeException(e.getMessage(),e);
        } finally {
            transport.close();
        }
    }
}
