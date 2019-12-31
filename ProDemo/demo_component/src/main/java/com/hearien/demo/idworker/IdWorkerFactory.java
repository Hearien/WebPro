package com.hearien.demo.idworker;

/**
 * @author 王海洋
 * @className: IdWorkerFactory
 * @description:
 * @create 2019/8/20 17:55
 **/
public class IdWorkerFactory {
    public static IdWorker create(int ... indexes) {
        return new StardardIdWorker(indexes);
    }
}
