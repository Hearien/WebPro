package com.hearien.demo.data;

import org.assertj.core.util.Lists;

import java.util.List;

/**
 * @author 王海洋
 * @className: Data
 * @description:
 * @create 2020/1/2 9:57
 **/
public class Data {

    public static List getData(){
        List<Person> personList = Lists.newArrayList();
        personList.add(new Person("jack",12,67));
        personList.add(new Person("micke",23,89));
        personList.add(new Person("mwww",14,90));
        personList.add(new Person("小姑娘",25,87));
        personList.add(new Person("小王",19,79));
        personList.add(new Person("黎明",14,88));
        personList.add(new Person("黎明",14,88));
        return personList;
    }
}
