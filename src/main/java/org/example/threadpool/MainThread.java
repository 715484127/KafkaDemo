package org.example.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MainThread {
    static volatile int i = 0;
    public static void main(String[] args) {
//        Thread t1 = new Thread(new TestThreadPool());
//        t1.start();
//        Thread t2 = new Thread(new TestThreadPool());
//        t2.start();

//        for(int i=0;i<10;i++){
//            new Thread(new TestThreadPool(i)).start();
//        }
        ThreadPoolExecutor pool = new ThreadPoolExecutor(50,100,10L, TimeUnit.SECONDS,new ArrayBlockingQueue<>(10), new ThreadPoolExecutor.CallerRunsPolicy());
        for(int j=0;j<100;j++){
            pool.execute(new TestThreadPool(i));
        }
        pool.shutdown();

    }
}
