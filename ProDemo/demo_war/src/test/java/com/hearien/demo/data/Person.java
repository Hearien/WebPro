package com.hearien.demo.data;

import lombok.Data;

/**
 * @author 王海洋
 * @className: Person
 * @description: 流式操作的每一个返回都是流本身
 * @create 2020/1/2 9:45
 **/
@Data
public class Person implements Comparable<Person> {

    private String name;

    private Integer age;

    private Integer score;

    public Person(){}

    public Person(String name, int age, int score) {
        this.name=name;
        this.age=age;
        this.score=score;
    }

    public Person setName(String name) {
        this.name = name;
        return this;
    }

    public Person setAge(Integer age) {
        this.age = age;
        return this;
    }

    public Person setScore(Integer score) {
        this.score = score;
        return this;
    }

    @Override
    public int compareTo(Person o) {
        return this.getScore()-o.getScore();
    }
}
