package com.hearien.demo.list;

import com.hearien.demo.data.Data;
import com.hearien.demo.data.Person;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author 王海洋
 * @className: Test
 * @description:
 * @create 2020/1/2 9:46
 **/
@Slf4j
public class Final {

    public static void main(String[] args) {
        //获取数据源:Data.getData().stream()、Stream.of(Data.getData())
        //获取并行流：Data.getData().stream().parallel()、Data.getData().parallelStream()
        Stream<Person> stream = Data.getData().stream();

        //最终操作1：collect
        // Collector
        List<Person> personList = stream.collect(Collectors.toList());
        log.info(personList.toString());
        Set<Person> personSet = stream.collect(Collectors.toSet());
        Map<String,Integer> personMap = stream.collect(Collectors.toMap(Person::getName,Person::getScore));
        log.info(personMap.toString());

        //最终操作2：reduce
        //1.求和
        Stream<Integer> s1 = Stream.of(1,2,3,4,5,6,22,33);
        Optional<Integer> rst1 = s1.reduce((n1, n2) -> n1+n2);
        log.info("---" + rst1.get().toString());
        //2.求person的成绩总和
        Person tmp = new Person();
        Optional<Person> rst2 = stream.reduce((n1, n2) -> tmp.setScore(n1.getScore()+n2.getScore()));
        log.info("---" + rst2.get().getScore().toString());

        //最终操作3：max、min
        //1.找到成绩最高的
        Person person1 = stream.max((p1,p2) -> p1.getScore()-p2.getScore()).get();
        log.info("---" + person1.getScore().toString());
        //2.年龄最小
        Person person2 = stream.min((p1,p2) -> p1.getAge()-p2.getAge()).get();
        log.info("---" + person2.getAge().toString());

        //最终操作4：anyMatch、allMatch、noneMatch
        //1.是否包含成绩大于80的学生
        boolean flag1 = stream.anyMatch(p -> p.getScore()>80);
        log.info("---" + flag1);
        //2.是否所有学员都及格
        boolean flag2 = stream.allMatch(p -> p.getScore()>60);
        log.info("---" + flag2);
        //3.是否所有都及格
        boolean flag3 = stream.noneMatch(p -> p.getScore()<60);
        log.info("---" + flag3);

        //最终操作5：count
        //1.原数据中有多少个元素
        long cnt = stream.count();
        log.info("---" + cnt);

        //最终操作6：foreach
        Data.getData().forEach(p -> System.out.println(p));

        //最终操作7：findFirst(获取流中第一个元素)、findAny(获取流中任一元素,并不是随机的一个.串行流与findFirst一眼，并行流可能一样可能不一样)
        System.out.println(stream.findFirst());
        System.out.println(stream.findAny());

        //最终操作8：flatMap 对流中的元素进行扁平化处理
        String[] arr = {"hello","world"};
        System.out.println(Arrays.stream(arr).map(s -> s.split("")).collect(Collectors.toList()));
        System.out.println(Arrays.stream(arr).map(s -> s.split("")).flatMap(Arrays::stream).collect(Collectors.toList()));

    }
}
