package com.hearien.demo.idworker;

/**
 * @author 王海洋
 * @className: IdWorker
 * @description:
 * @create 2019/8/20 17:53
 **/
public interface IdWorker {

    int MIN_HANDLER_ID = IdWorkerHandler.MIN_WORKER_INDEX;

    int MAX_HANDLER_ID = IdWorkerHandler.MAX_WORKER_INDEX;

    long nextId();
}
