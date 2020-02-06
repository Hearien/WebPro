package com.hearien.demo.concurrent.thread;

/**
 * @author 王海洋
 * @className: InterruptDemo
 * @description: 中断  stop()废弃，会引发安全问题   interrupt()    自定义一个标志来中断
 * @create 2020/1/3 14:50
 **/
public class InterruptDemo {

    public static void main(String[] args) {

        Thread t1 = new Thread(new MyInterrupt(),"t1");
        t1.start();
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        MyInterrupt.flag=false;
    }

    static class Interrupt implements Runnable{

        @Override
        public void run() {
            while(!Thread.currentThread().isInterrupted()){
                System.out.println(Thread.currentThread().getName());
            }
        }
    }

    static class MyInterrupt implements Runnable{

        private static volatile boolean flag = true;

        @Override
        public void run() {
            while(flag){
                System.out.println(Thread.currentThread().getName());
            }
        }
    }


}
