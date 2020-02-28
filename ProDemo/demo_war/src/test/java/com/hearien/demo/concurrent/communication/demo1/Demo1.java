package com.hearien.demo.concurrent.communication.demo1;

/**
 * @author 王海洋
 * @className: Demo
 * @description:
 * @create 2020/2/7 15:15
 **/
public class Demo1 {

    private static volatile boolean flag = false;

   public static void main(String[] args) throws InterruptedException {
       Object obj = new Object();
        new Thread(()->{
            while(!flag){
                synchronized (obj){
                    try {
                        System.out.println("flag is false");
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println(Thread.currentThread().getName() + "---" + flag);
        }).start();

       new Thread(()->{
           while(!flag){
               synchronized (obj){
                   try {
                       System.out.println("flag is false");
                       obj.wait();
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }
           }
           System.out.println(Thread.currentThread().getName() + "---" + flag);
       }).start();

        Thread.sleep(1000L);

        new Thread(()->{
            flag = true;
            synchronized (obj){
                obj.notifyAll();
            }

        }).start();
    }
}
