package com.hearien.thrift.server;

import com.hearien.thrift.DataException;
import com.hearien.thrift.Person;
import com.hearien.thrift.PersonService;
import org.apache.thrift.TException;

/**
 * @ClassName PersonServiceImpl
 * @Author WangHaiyang
 * @Date 2018/12/17 15:02
 **/
public class PersonServiceImpl implements PersonService.Iface {
    @Override
    public Person getPersonByUserName(String userName) throws DataException, TException {
        Person person = new Person();
        person.setUserName(userName);
        person.setAge(12);
        person.setMarrief(false);
        return person;
    }

    @Override
    public void savePerson(Person person) throws DataException, TException {
        System.out.println(person.getAge()+";"+person.getUserName());
        System.out.println("已保存");
    }
}
