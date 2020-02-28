package com.hearien.demo.concurrent.communication.demo4;

/**
 * @author 王海洋
 * @className: Test
 * @description:
 * @create 2020/2/8 17:57
 **/
public class Test {

    public static void main(String[] args) {
        Thread thread = new Thread(()->{
            System.out.println(Thread.currentThread().getName() + "开始运行");
            try {
                Thread.sleep(1000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "运行结束");
        },"T1");

        new Thread(()->{
            System.out.println(Thread.currentThread().getName() + "开始运行");
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "运行结束");
        },"T2").start();
    }
}
