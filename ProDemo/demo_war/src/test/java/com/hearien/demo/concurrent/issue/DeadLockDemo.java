package com.hearien.demo.concurrent.issue;

/**
 * @author 王海洋
 * @className: DeadLockDemo
 * @description: 死锁例子
 * @create 2020/1/2 17:49
 **/
public class DeadLockDemo {

    private static final Object HireA = new Object();
    private static final Object HireB = new Object();

    public static void main(String[] args) {
        new Thread(()->{
            synchronized (HireA){
                try {
                    Thread.sleep(50L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (HireB){
                    System.out.println("A scrach B");
                }
            }
        }).start();

        new Thread(()->{
            synchronized (HireB){
                synchronized (HireA){
                    System.out.println("B scrach A");
                }
            }
        }).start();
    }
}
