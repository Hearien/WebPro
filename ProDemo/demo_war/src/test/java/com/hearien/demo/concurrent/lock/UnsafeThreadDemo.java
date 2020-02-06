package com.hearien.demo.concurrent.lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;

/**
 * @author 王海洋
 * @className: UnsafeThreadDemo
 * @description: lock的使用
 * Lock 与synchronized区别：lock获得与释放锁的过程，需要手动控制；与synchronized区别托管给jvm执行，原始才用CPU悲观锁机制，即线程获得的是独占锁，意味着其他线程只能依靠阻塞来等待线程释放锁
 * @create 2020/1/3 9:11
 **/
public class UnsafeThreadDemo {

    private static volatile int num = 0;

    private static CountDownLatch countDownLatch = new CountDownLatch(10);

    private static Lock lock = new MyLock();

    public static void inCreate(){
        lock.lock();
        num++;
        lock.unlock();
    }

    public static void main(String[] args) {
        for(int i=0;i<10;i++){
            new Thread(()->{
                for (int j=0;j<100;j++){
                    inCreate();
                    try {
                        Thread.sleep(10);//线程不安全，数据没有同步
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //每个线程执行完成后调用
                countDownLatch.countDown();
            }).start();
        }

        while (true){
            if(countDownLatch.getCount() == 0){
                System.out.println(num);
                break;
            }
        }
    }

}
