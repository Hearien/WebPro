package com.hearien.demo.concurrent.container;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author 王海洋
 * @className: Demo
 * @description:
 * @create 2020/2/15 11:53
 **/
public class Demo {

    public static void main(String[] args) {
        /*List<String> strings = new ArrayList<>();
        List<String> stringList = Collections.synchronizedList(strings);*/

        CopyOnWriteArrayList<String> strings = new CopyOnWriteArrayList<>();
        for(int i=0;i<1000;i++){
            strings.add("demo" + i);
        }

        /*strings.forEach(e ->{
            if(e.equals("demo3")){
                strings.remove(e);
                System.out.println(e);
            }
        });*/

        /**
         * java.lang.UnsupportedOperationException
         */
        /*Iterator<String> iterator = strings.iterator();
        while(iterator.hasNext()){
            String e = iterator.next();
            if(e.equals("demo3")){
                iterator.remove();
                System.out.println(e);
            }
        }*/

        for(int i=0;i<4;i++){
            new Thread(()->{
                strings.forEach(e ->{
                    if(e.equals("demo3")){
                        strings.remove(e);
                        System.out.println(e);
                    }
                });
            }).start();
        }
    }
}
