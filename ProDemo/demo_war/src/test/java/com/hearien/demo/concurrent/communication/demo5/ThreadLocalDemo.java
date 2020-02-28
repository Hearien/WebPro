package com.hearien.demo.concurrent.communication.demo5;

/**
 * @author 王海洋
 * @className: ThreadLocalDemo
 * @description:
 * @create 2020/2/8 18:11
 **/
public class ThreadLocalDemo {

    ThreadLocal<Integer> num = ThreadLocal.withInitial(()->0);

    /**
     * 自增
     */
    public void create(){
        int myNum = num.get();
        myNum++;
        System.out.println(Thread.currentThread().getName() + "=======" + myNum);
        num.set(myNum);
    }

    public static void main(String[] args) {
        ThreadLocalDemo demo = new ThreadLocalDemo();
        for(int i=1;i<3;i++){
            int finalI = i;
            new Thread(()->{
                while (true){
                    demo.create();
                    try {
                        Thread.sleep(finalI*1000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }).start();
        }
    }
}
