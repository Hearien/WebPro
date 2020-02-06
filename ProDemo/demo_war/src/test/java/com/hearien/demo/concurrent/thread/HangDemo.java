package com.hearien.demo.concurrent.thread;

/**
 * @author 王海洋
 * @className: HangDemo
 * @description: 线程挂起   suspend()/resume()废弃，挂起不会释放资源     wait()/notify()/notifyAll()可以使用
 * @create 2020/1/3 14:12
 **/
public class HangDemo implements Runnable {

    private static  Object obj = new Object();

    public static void main(String[] args) throws InterruptedException {
        waitDemo();
    }

    public static void suspand() throws InterruptedException {
        Thread thread = new Thread(new HangDemo());
        thread.setName("t1");
        thread.start();
        Thread.sleep(3000L);
        thread.resume();

        Thread thread2 = new Thread(new HangDemo());
        thread2.setName("t2");
        thread2.start();
        //Thread.sleep(3000L);
        thread2.resume();

    }

    public static void waitDemo(){
        Thread t1 = new Thread(new WaitDemo(),"t1");
        t1.start();

        Thread t2 = new Thread(new WaitDemo(),"t2");
        t2.start();
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (WaitDemo.waitObj){
            WaitDemo.waitObj.notify();
        }

    }

    @Override
    public void run() {
        synchronized (obj){
            System.out.println(Thread.currentThread().getName() + "占用资源");
            Thread.currentThread().suspend();
        }
        System.out.println(Thread.currentThread().getName() + "释放资源");

    }

    static class WaitDemo implements Runnable{

        private static Object waitObj = new Object();

        @Override
        public void run() {
            synchronized (waitObj){
                System.out.println(Thread.currentThread().getName() + "占用资源");
                try {
                    waitObj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + "释放资源");
        }
    }
}
