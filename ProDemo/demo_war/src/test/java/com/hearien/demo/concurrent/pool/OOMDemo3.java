package com.hearien.demo.concurrent.pool;

import java.util.concurrent.*;

/**
 * @author 王海洋
 * @className: OOMDemo2
 * @description:
 * @create 2020/2/18 17:56
 **/
public class OOMDemo3 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        for(int i=0;i<100;i++){
            int finalI = i;
            executorService.execute(()->{
                System.out.println(Thread.currentThread().getName());
                int j= finalI /1;
            });
        }
    }
}
