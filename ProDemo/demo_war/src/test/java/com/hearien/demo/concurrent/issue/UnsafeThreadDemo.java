package com.hearien.demo.concurrent.issue;

import java.util.concurrent.CountDownLatch;

/**
 * @author 王海洋
 * @className: UnsafeThreadDemo
 * @description: 线程不安全
 * @create 2020/1/3 9:11
 **/
public class UnsafeThreadDemo {

    private static int num = 0;

    private static CountDownLatch countDownLatch = new CountDownLatch(10);

    public static void inCreate(){
        num++;
    }

    public static void main(String[] args) {
        for(int i=0;i<10;i++){
            new Thread(()->{
                for (int j=0;j<100;j++){
                    inCreate();
                    try {
                        Thread.sleep(10);//线程不安全，数据没有同步
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //每个线程执行完成后调用
                countDownLatch.countDown();
            }).start();
        }

        while (true){
            if(countDownLatch.getCount() == 0){
                System.out.println(num);
                break;
            }
        }
    }

}
