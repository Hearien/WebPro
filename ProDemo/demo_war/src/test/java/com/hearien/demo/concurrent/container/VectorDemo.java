package com.hearien.demo.concurrent.container;

import java.util.Iterator;
import java.util.Vector;

/**
 * @author 王海洋
 * @className: VectorDemo
 * @description:
 * @create 2020/2/15 10:19
 **/
public class VectorDemo {

    public static void main(String[] args) {
        Vector<String> stringVector = new Vector<String>();
        for(int i=0;i<1000;i++){
            stringVector.add("demo" + i);
        }

        /**
         * java.util.ConcurrentModificationException
         */
        /*stringVector.forEach(e->{
            if(e.equals("demo3")){
                stringVector.remove(e);
            }
            System.out.println(e);
        });*/

        /**
         * 正确迭代
         */
        /*Iterator<String> iterator = stringVector.iterator();
        while (iterator.hasNext()){
            String val = iterator.next();
            if(val.equals("demo3")){
                iterator.remove();
            }
            System.out.println(val);
        }*/

        Iterator<String> iterator = stringVector.iterator();
        for(int i=0;i<4;i++){
            new Thread(()->{
                synchronized (iterator){//避免异常java.util.NoSuchElementException
                    while (iterator.hasNext()){
                        String val = iterator.next();
                        if(val.equals("demo3")){
                            iterator.remove();
                        }
                        //System.out.println(val);
                    }
                }
            }).start();
        }
    }
}
