package com.hearien.demo.concurrent.thread;

/**
 * @author 王海洋
 * @className: Demo
 * @description:
 * @create 2020/1/3 10:18
 **/
public class Demo {

    public synchronized void t1(){
        System.out.println(Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        Demo demo = new Demo();
        Demo demo2 = new Demo();
        demo.t1();
        demo2.t1();
    }
}
