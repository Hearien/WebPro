package com.hearien.demo.concurrent.tool;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author 王海洋
 * @className: CyclicBarrierDemo
 * @description:
 * @create 2020/2/16 13:58
 **/
public class CyclicBarrierDemo {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(4);

        for(int i=0;i<8;i++){
            int finalI = i;
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() + "准备就绪");
                try {
                    Thread.sleep(finalI *1000L);
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "开始比赛");
            }).start();
        }
    }
}
