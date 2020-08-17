package com.tmw.novel.uitl;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * 线程工具类
 *
 * @author TMW
 * @date 2020/8/17 15:00
 */
public class ThreadPoolUtil {

    public static ExecutorService executorService;

    private ThreadPoolUtil() {
        executorService = MoreExecutors.listeningDecorator(new ThreadPoolExecutor(10, 20, 60, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(), new ThreadFactoryBuilder().setThreadFactory(Thread::new).setNameFormat("default-%d-thread").build()));
    }

    /**
     * 默认线程池
     *
     * @return
     */
    public static ExecutorService getExecutorService() {
        return executorService;
    }

    /**
     * 自定义线程池
     *
     * @param nThreads
     * @return
     */
    public static ListeningExecutorService getExecutorService(int nThreads) {
        return MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(nThreads,
                new ThreadFactoryBuilder().setThreadFactory(Thread::new).setNameFormat("custom-%d-thread").build()));
    }
}
