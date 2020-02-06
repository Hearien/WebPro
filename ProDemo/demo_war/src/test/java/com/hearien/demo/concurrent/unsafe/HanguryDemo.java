package com.hearien.demo.concurrent.unsafe;

/**
 * @author 王海洋
 * @className: HanguryDemo
 * @description: 饿汉式单利，类加载的时候就已经实例化，占内存，浪费资源
 * @create 2020/1/6 17:19
 **/
public class HanguryDemo {
    private static HanguryDemo ourInstance = new HanguryDemo();

    public static HanguryDemo getInstance() {
        return ourInstance;
    }

    private HanguryDemo() {
    }
}
