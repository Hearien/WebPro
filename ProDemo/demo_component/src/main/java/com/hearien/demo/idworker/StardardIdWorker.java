package com.hearien.demo.idworker;

/**
 * @author 王海洋
 * @className: StardardIdWorker
 * @description:
 * @create 2019/8/20 17:55
 **/
public class StardardIdWorker implements IdWorker {
    private IdWorkerHandler handler;

    public StardardIdWorker(int ... indexes) {
        handler = new IdWorkerHandler(indexes);
    }

    public int getMaxIndex() {
        return IdWorkerHandler.MAX_WORKER_INDEX;
    }


    @Override
    public long nextId() {
        return this.handler.nextId();
    }
}
