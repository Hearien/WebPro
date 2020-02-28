package com.hearien.demo.concurrent.communication.demo1;

import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * @author 王海洋
 * @className: Demo
 * @description:
 * @create 2020/2/7 15:15
 **/
public class Demo {

    private static volatile boolean flag = false;

    public static void main(String[] args) throws InterruptedException {
        new Thread(()->{
            while(!flag){
                try {
                    Thread.sleep(1000l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("flag is false");
            }
            System.out.println(Thread.currentThread().getName() + "---" + flag);
        }).start();

        Thread.sleep(1000L);

        new Thread(()->{
            flag = true;
        }).start();
    }
}
