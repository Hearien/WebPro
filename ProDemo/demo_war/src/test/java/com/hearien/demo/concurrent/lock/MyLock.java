package com.hearien.demo.concurrent.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author 王海洋
 * @className: MyLock
 * @description:
 * @create 2020/1/14 15:36
 **/
public class MyLock implements Lock {

    private boolean isHoldLock = false;

    private Thread lockHoldThoread = null;

    private Integer lockCnt = 0;

    /**
     * 同一时刻，有且仅有一个线程能获取到锁，其他线程只能等到线程释放锁之后才能获取锁
     */
    @Override
    public synchronized void lock() {
        if(isHoldLock && lockHoldThoread!=Thread.currentThread()){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        isHoldLock = true;
        lockHoldThoread = Thread.currentThread();
        lockCnt++;
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public synchronized void unlock() {
        if(lockHoldThoread == Thread.currentThread()){
            notify();
            lockCnt--;
        }
        if(lockCnt == 0){
            isHoldLock = false;
        }
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
