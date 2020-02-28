package com.hearien.demo.concurrent.communication.demo6;

/**
 * @author 王海洋
 * @className: Test
 * @description:
 * @create 2020/2/8 17:00
 **/
public class Test {

    public static void main(String[] args) {
        Mediea mediea = new Mediea();

        new Thread(new Producer(mediea)).start();
        new Thread(new Producer(mediea)).start();
        new Thread(new Producer(mediea)).start();
        new Thread(new Producer(mediea)).start();
        new Thread(new Producer(mediea)).start();
        new Thread(new Producer(mediea)).start();
        new Thread(new Producer(mediea)).start();

        new Thread(new Consumer(mediea)).start();
        new Thread(new Consumer(mediea)).start();
        new Thread(new Consumer(mediea)).start();
        new Thread(new Consumer(mediea)).start();
    }
}
