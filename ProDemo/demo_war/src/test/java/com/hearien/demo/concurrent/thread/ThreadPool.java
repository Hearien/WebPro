package com.hearien.demo.concurrent.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author 王海洋
 * @className: ThreadPool
 * @description:
 * @create 2020/1/3 11:19
 **/
public class ThreadPool {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(()->{
            System.out.println(3|9);
            System.out.println(Thread.currentThread().getName());
        });
    }
}
