package com.hearien.demo.concurrent.communication.demo6;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 王海洋
 * @className: Mediea
 * @description:
 * @create 2020/2/8 17:00
 **/
public class Mediea {

    private int num = 0;

    private final int TOTAL = 20;

    private Lock lock = new ReentrantLock();
    private Condition consumCondition = lock.newCondition();
    private Condition produceCondition = lock.newCondition();

    /**
     * 生产
     * 如果库存不足通知生产者生产
     * 否则停止生产
     */
    public void put(){
        lock.lock();
        try{
            if(num<TOTAL){
                System.out.println("开始生产，库存为---"+ ++num);
                consumCondition.signalAll();
            }else{
                try {
                    System.out.println("库存已满，库存为---" + num);
                    produceCondition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }finally {
            lock.unlock();
        }
    }

    /**
     * 消费
     * 如果有库存这消费
     * 否则等待
     */
    public void take(){
        lock.lock();
        try {
            if(num>0){
                System.out.println("开始消费，消费后库存为" + --num);
                produceCondition.signalAll();
            }else{
                System.out.println("库存为空，等待生产，库存为" + num);
                try {
                    consumCondition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }finally {
            lock.unlock();
        }
    }
}
