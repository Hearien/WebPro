package com.hearien.demo.concurrent.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 王海洋
 * @className: ReentrantLockDebugDemo
 * @description:
 * @create 2020/1/28 11:21
 **/
public class ReentrantLockDebugDemo {

    private int i = 0;
    private ReentrantLock reentrantLock = new ReentrantLock();

    public void inCreate(){
        reentrantLock.lock();
        try{
            i++;
            System.out.println(i);
        }finally {
            reentrantLock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantLockDebugDemo reentrantLockDebugDemo = new ReentrantLockDebugDemo();
        for(int i=0; i < 3; i++){
            new Thread(()->{
                reentrantLockDebugDemo.inCreate();
            }).start();
        }
    }
}
