package com.hearien.demo.concurrent.atomic.demo1;

import java.util.concurrent.atomic.LongAccumulator;

/**
 * @author 王海洋
 * @className: Demo1
 * @description:
 * @create 2020/2/10 16:46
 **/
public class Demo1 {

    public static void main(String[] args) {
        //自定义运算，每次和前一次的结果做运算，第一次与初始值运算
        LongAccumulator longAccumulator = new LongAccumulator((left,right)->
           left > right ? left : right, 0L
        );
        longAccumulator.accumulate(3L);
        System.out.println(longAccumulator.get());
    }
}
