package com.hearien.demo.list;

import com.hearien.demo.data.Data;
import com.hearien.demo.data.Person;

import java.util.IntSummaryStatistics;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author 王海洋
 * @className: Parallel
 * @description: Collectors工具类
 * @create 2020/1/2 15:39
 **/
public class CollectorsDemo {

    public static void main(String[] args) {
        //Collectors:工具类，提供若干方法返回Collector接口的实现对象。toList、toSet、toMap
        Stream<Person> stream = Data.getData().stream();
        //maxBy:通过制定规则获取流中最大的元素
        //minBy:通过制定规则获取流中最小的元素
        Optional<Person> pe1 = stream.collect(Collectors.maxBy((p1, p2) -> p1.getScore()-p2.getScore()));
        Optional<Person> pe2 = stream.collect(Collectors.minBy((p1, p2) -> p1.getAge()-p2.getAge()));

        //joining 合并，将流中的元素以字符窜的形式连接起来
        String s1 = stream.map(Person::getName).collect(Collectors.joining());
        String s2 = stream.map(Person::getName).collect(Collectors.joining("-"));
        String s3 = stream.map(Person::getName).collect(Collectors.joining("-","{","}"));

        //sumingInt 将流中的每个元素映射成一个int类型的元素，然后进行求和
        //sumingFloat
        //sumingDouble
        int s = stream.collect(Collectors.summingInt(Person::getScore));

        //summarizingInt 对流中元素映射成int元素,最后获取这些元素的描述信息
        IntSummaryStatistics ret = stream.collect(Collectors.summarizingInt(Person::getAge));
    }
}
