package com.hearien.demo.concurrent.unsafe;

/**
 * @author 王海洋
 * @className: LazyDmo
 * @description: 懒汉式单利
 * @create 2020/1/6 17:23
 **/
public class LazyDmo {
    private static volatile LazyDmo ourInstance = null;

    public static LazyDmo getInstance() {
        if(ourInstance==null){
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (LazyDmo.class){
                if(ourInstance==null){
                    ourInstance = new LazyDmo();
                }
            }
        }
        return ourInstance;
    }

    private LazyDmo() {
    }

    public static void main(String[] args) {
        for(int i=0;i<10;i++){
            new Thread(()->{
                System.out.println(LazyDmo.getInstance());
            }).start();
        }
    }
}
