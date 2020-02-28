package com.hearien.demo.concurrent.container;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author 王海洋
 * @className: Demo3
 * @description:
 * @create 2020/2/15 19:00
 **/
public class Demo3 {

    public static void main(String[] args) throws InterruptedException {
        LinkedBlockingQueue<String> strings = new LinkedBlockingQueue<>();

        /**
         * 存元素
         */
        strings.offer("111");
        strings.add("111");
        strings.put("111");

        /**
         * 取元素
         */
        strings.remove();
        strings.take();
        strings.poll();
    }
}
