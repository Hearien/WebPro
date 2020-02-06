package com.hearien.demo.concurrent.thread;

/**
 * @author 王海洋
 * @className: ThreadStateDemo
 * @description:
 * @create 2020/1/3 10:38
 **/
public class ThreadStateDemo {

    public static void main(String[] args) {
        /**
         * runnable
         */
        /*Thread thread = new Thread(()->{
            try {
                System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        thread.start();*/

        /**
         * blocked
         */
        Object obj = new Object();
        Thread thread = new Thread(()->{
            try {
                synchronized (obj){
                    Thread.sleep(10000000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread thread2 = new Thread(()->{
            synchronized (obj){
            }
        });
        thread2.start();
    }
}
