package com.hearien.demo.concurrent.atomic.demo2;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @author 王海洋
 * @className: demo1
 * @description:
 * @create 2020/2/10 17:00
 **/
public class demo1 {

    public static void main(String[] args) {
        int[] arr = new int[]{11,6};
        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(arr);
        System.out.println(atomicIntegerArray.addAndGet(1,3));

        int i = atomicIntegerArray.accumulateAndGet(0,2,(left,right)->
            left>right?left:right
        );
        System.out.println(i);
    }
}
