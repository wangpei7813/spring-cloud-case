package com.wang.userservice.async;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池的使用 需要整体的进行把控 所以需要有2种方法区进行限制
 * 1 约定建立一个目录下，都在指定的位置去编写代码
 * 2 统一使用封装好了的工具类去创建线程池，用完之后 要进行关闭资源
 *
 * @author Alienware
 */
@Slf4j
public class UserInfoAsyncQueue {

    private static final int QUEUE_SIZE = 10000;

    private static final int THREAD_SIZE = Runtime.getRuntime().availableProcessors() / 2;

    private static ExecutorService userInfoLoader = new ThreadPoolExecutor(THREAD_SIZE,
            THREAD_SIZE,
            0L,
            TimeUnit.MILLISECONDS,
            new ArrayBlockingQueue<>(QUEUE_SIZE),
            r -> {
                Thread t = new Thread(r);
                t.setName("load_user_info");
                return t;
            },
            (r, executor) -> {
                log.warn("------------打印拒绝策略---------");
                //do something ......
            });

    public static void submit(Runnable runnable) {
        userInfoLoader.submit(runnable);
    }


}
