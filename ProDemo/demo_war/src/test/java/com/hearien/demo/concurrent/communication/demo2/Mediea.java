package com.hearien.demo.concurrent.communication.demo2;

/**
 * @author 王海洋
 * @className: Mediea
 * @description:
 * @create 2020/2/8 17:00
 **/
public class Mediea {

    private int num = 0;

    private final int TOTAL = 20;

    /**
     * 生产
     * 如果库存不足通知生产者生产
     * 否则停止生产
     */
    public synchronized void put(){
        if(num<TOTAL){
            System.out.println("开始生产，库存为---"+ ++num);
            notifyAll();
        }else{
            try {
                System.out.println("库存已满，库存为---" + num);
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 消费
     * 如果有库存这消费
     * 否则等待
     */
    public synchronized void take(){
        if(num>0){
            System.out.println("开始消费，消费后库存为" + --num);
            notifyAll();
        }else{
            System.out.println("库存为空，等待生产，库存为" + num);
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
