package com.hearien.demo.concurrent.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author 王海洋
 * @className: ReentrantReadWriteLockDemo
 * @description:
 * @create 2020/1/30 10:30
 **/
public class ReentrantReadWriteLockDemo {

    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    Lock readLock = lock.readLock();
    Lock writeLock = lock.writeLock();

    private int i = 0;
    private int j = 0;

    public void out(){
        readLock.lock();
        try{
            System.out.println(Thread.currentThread().getName() + "---i===" + i + ";j===" + j);
        }finally {
            readLock.unlock();
        }
    }

    public void inCreate(){
        writeLock.lock();
        try {
            i++;
            Thread.sleep(500L);
            j++;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantReadWriteLockDemo lockDemo = new ReentrantReadWriteLockDemo();
        for(int i = 0; i<3; i++){
            new Thread(()->{
                lockDemo.inCreate();
                lockDemo.out();
            }).start();
        }
    }
}
