package com.hearien.demo.concurrent.thread;

/**
 * @author 王海洋
 * @className: DeamThreadDemo
 * @description: 守护线程
 * @create 2020/1/6 15:32
 **/
public class DeamThreadDemo implements Runnable {
    @Override
    public void run() {
        while (true){
            System.out.println(Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new DeamThreadDemo());
        t1.setDaemon(true);//设置为守护线程
        t1.start();
        Thread.sleep(2000L);
    }
}
