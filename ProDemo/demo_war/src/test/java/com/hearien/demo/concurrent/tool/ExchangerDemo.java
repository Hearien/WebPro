package com.hearien.demo.concurrent.tool;

import java.util.concurrent.Exchanger;

/**
 * @author 王海洋
 * @className: ExchangerDemo
 * @description:
 * @create 2020/2/16 17:37
 **/
public class ExchangerDemo {

    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();

        String str1 = "string1";
        String str2 = "string2";

        new Thread(()->{
            System.out.println(Thread.currentThread().getName() + "初始值为=====" + str1);
            try {
                String exVal = exchanger.exchange(str1);
                System.out.println(Thread.currentThread().getName() + "交换后为=====" + exVal);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(()->{
            System.out.println(Thread.currentThread().getName() + "初始值为=====" + str2);
            try {
                String exVal = exchanger.exchange(str2);
                System.out.println(Thread.currentThread().getName() + "交换后为=====" + exVal);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
