package com.hearien.demo.concurrent.pool;

import java.util.concurrent.*;

/**
 * @author 王海洋
 * @className: ThreadPoolDemo
 * @description:
 * @create 2020/2/17 13:28
 **/
public class ThreadPoolDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<>();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(10,20,3L, TimeUnit.SECONDS,queue);
        /*for(int i=0;i<100;i++){
            executor.submit(()->{
                System.out.println(Thread.currentThread().getName());
            });
        }*/

        Future<String> future = null;

        for(int i=0;i<100;i++){
            future = executor.submit(new CallableDemo());
        }
        for(int i=0;i<100;i++){
            System.out.println(future.get());
        }
    }
}
