package org.example.threadpool;

public class TestThreadPool implements Runnable{
    private volatile int i;
    TestThreadPool(int i){
        this.i = i;
    }
    @Override
    public void run() {
        synchronized (this.getClass()){
            i++;
        }

        System.out.println("================" + Thread.currentThread().getName()+ "==============" + i);
    }
}
