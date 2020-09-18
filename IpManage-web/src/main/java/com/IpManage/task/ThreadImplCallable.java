package com.IpManage.task;

import java.util.concurrent.Callable;

public class ThreadImplCallable<T> implements Callable<T> {
    /**
     * 线程名称
     */
    private String threadName;

    /**
     * 构造函数
     *
     * @param threadName 线程名称
     */
    public ThreadImplCallable(String threadName) {
        this.threadName = threadName;
    }
    /**
     * 重写call方法
     */
    @Override
    public T call() throws Exception {
        System.out.println(threadName);
        return (T)threadName;
    }
}
