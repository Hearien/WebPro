package com.hearien.demo.concurrent.communication.demo6;

/**
 * @author 王海洋
 * @className: Consumer
 * @description:
 * @create 2020/2/8 16:59
 **/
public class Consumer implements Runnable {

    private Mediea mediea;

    public Consumer(Mediea mediea){
        this.mediea=mediea;
    }

    @Override
    public void run() {
        while (true){
            this.mediea.take();
        }
    }
}
