package com.hearien.demo.list;

import com.hearien.demo.data.Data;
import com.hearien.demo.data.Person;

import java.util.stream.Stream;

/**
 * @author 王海洋
 * @className: Proccess
 * @description:
 * @create 2020/1/2 10:52
 **/
public class Proccess {

    public static void main(String[] args) {
        Stream<Person> stream = Data.getData().stream();
        //中间操作1：filter
        //filter:过滤器，可以自定义一个过滤条件，将流中满足条件的元素保存
        //1.保留成绩大于等于80的学员
        stream.filter(p -> p.getScore()>=80).forEach(System.out::println);

        //中间操作2：distinct去重，去除集合中重复的元素,根据需要重写对象类中的hash()和equals()
        //去重规则：先判断两个对象的hashcode，如果hashcode相同，在判断equals，如果也相同就判断为相同的元素
        stream.distinct().forEach(System.out::println);

        //中间操作3：sort排序,要求流中的元素对应的类实现Comparable接口
        stream.sorted().forEach(System.out::println);
        stream.sorted((p1,p2) -> p1.getAge()-p2.getAge()).forEach(System.out::println);

        //中间操作4：limit 限制，取流中前指定位的数据
        stream.limit(3).forEach(System.out::println);

        //中间操作5：skip 跳过指定位，取后面的元素
        stream.skip(3).forEach(System.out::println);

        //中间操作6：map 元素映射，用指定的元素来替换流中的元素
        //1.用名字替换流中的对象
        stream.map(p -> p.getName()).forEach(System.out::println);
        //2.成绩大于80保留对象，否则保留名字
        stream.map(p -> p.getScore()>80?p:p.getName()).forEach(System.out::println);
    }
}
