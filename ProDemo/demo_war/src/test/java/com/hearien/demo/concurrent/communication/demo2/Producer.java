package com.hearien.demo.concurrent.communication.demo2;

/**
 * @author 王海洋
 * @className: Producer
 * @description:
 * @create 2020/2/8 17:00
 **/
public class Producer implements Runnable {

    private Mediea mediea;

    public Producer(Mediea mediea){
        this.mediea=mediea;
    }

    @Override
    public void run() {
        while (true){
            this.mediea.put();
        }
    }
}
