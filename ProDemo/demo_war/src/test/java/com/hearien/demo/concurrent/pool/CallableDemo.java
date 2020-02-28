package com.hearien.demo.concurrent.pool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author 王海洋
 * @className: CallableDemo
 * @description:
 * @create 2020/2/17 13:52
 **/
public class CallableDemo implements Callable<String> {
    @Override
    public String call() throws Exception {
        //Thread.sleep(3000L);
        System.out.println("111111111");
        return Thread.currentThread().getName();
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CallableDemo demo = new CallableDemo();

        FutureTask<String> futureTask = new FutureTask<String>(demo);
        new Thread(futureTask).start();
        System.out.println(futureTask.get());

    }
}
