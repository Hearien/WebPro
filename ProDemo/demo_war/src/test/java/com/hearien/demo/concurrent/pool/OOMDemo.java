package com.hearien.demo.concurrent.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author 王海洋
 * @className: OOMDemo
 * @description:
 * @create 2020/2/18 13:47
 **/
public class OOMDemo {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        while(true){
            executorService.submit(()->{
                System.out.println(Thread.currentThread().getName());
                try {
                    Thread.sleep(3000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
