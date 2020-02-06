package com.hearien.demo.concurrent.lock;

import java.util.concurrent.locks.Lock;

/**
 * @author 王海洋
 * @className: RetrentLockDemo
 * @description:
 * @create 2020/1/14 16:12
 **/
public class RetrentLockDemo {

    Lock lock = new MyLock();

    public void funcA(){
        lock.lock();
        System.out.println("funcA");
        funcB();
        lock.unlock();
    }

    public void funcB(){
        lock.lock();
        System.out.println("funcB");
        lock.unlock();
    }

    public static void main(String[] args) {
        RetrentLockDemo demo = new RetrentLockDemo();
        demo.funcA();
    }
}
