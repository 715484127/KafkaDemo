package org.example.threadpool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class DynamicThreadPoolDemo {
    public static void main(String[] args) throws InterruptedException {
        DynamicThreadPoolDemo dynamicThreadPoolDemo = new DynamicThreadPoolDemo();
        dynamicThreadPoolDemo.dynamicModifyExecutor();
    }

    /**
     * 自定义线程池
     * @return
     */
    private ThreadPoolExecutor buildThreadPoolExecutor(){
        ThreadPoolExecutor myThreadPool = new ThreadPoolExecutor(
                2,
                5,
                60,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(10),
                new NamedThreadFactory("myThread")
        );
        return myThreadPool;
    }

    /**
     * 自定义ThreadFactory
     */
    class NamedThreadFactory implements ThreadFactory{
        private String threadName;

        public NamedThreadFactory(String threadName) {
            this.threadName = threadName;
        }

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(null, r, threadName);
        }
    }

    /**
     * 提交任务给线程池，并修改线程池参数
     */
    private void dynamicModifyExecutor() throws InterruptedException {
        ThreadPoolExecutor executor = buildThreadPoolExecutor();
        for(int i = 0; i < 15; i++){
            executor.submit(() ->{
                threadPoolStatus(executor,"创建任务");
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        threadPoolStatus(executor,"改变之前");
        executor.setCorePoolSize(10);
        executor.setMaximumPoolSize(10);
        threadPoolStatus(executor,"改变之后");
        Thread.currentThread().join();
    }

    /**
     * 打印线程池状态
     */
    private void threadPoolStatus(ThreadPoolExecutor executor, String name){
        LinkedBlockingQueue queue = (LinkedBlockingQueue) executor.getQueue();
        System.out.println(Thread.currentThread().getName() + "-" + name + "-:"
                + "核心线程数：" + executor.getCorePoolSize()
                + " 活动线程数：" + executor.getActiveCount()
                + " 最大线程数：" + executor.getMaximumPoolSize()
                + " 线程池活跃度：" + divide(executor.getActiveCount(),executor.getMaximumPoolSize())
                + " 任务完成数：" + executor.getCompletedTaskCount()
                + " 队列大小：" + queue.size() + queue.remainingCapacity()
                + " 当前排队线程数：" + queue.size()
                + " 队列剩余大小：" + queue.remainingCapacity()
                + " 队列使用度：" + divide(queue.size(),queue.size()+queue.remainingCapacity()));
    }

    private String divide(int num1,int num2){
        return String.format("%1.2f%%",
                Double.parseDouble(num1 + "") / Double.parseDouble(num2 + "") * 100);
    }
}
