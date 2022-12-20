package org.example.threadhandlfile;

import java.io.*;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CountDownLatchFileDemo {
    public static void main(String[] args) throws InterruptedException {
        File filedir = new File("/mnt/disk1/testfile");
        File files[] = filedir.listFiles();
        int fileCount = 0;
        for (File file : files) {
            if(file.isFile()){
                fileCount++;
            }
        }
        System.out.println("文件数量：" + fileCount);
        CountDownLatch countDownLatch = new CountDownLatch(fileCount);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                2,
                5,
                5,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(fileCount),
                new ThreadPoolExecutor.AbortPolicy());
        Instant start = Instant.now();
        for(int i = 0;i<fileCount;i++){
            int j = i+1;
            threadPoolExecutor.submit(()->{
                try(BufferedReader reader = new BufferedReader(new FileReader("/mnt/disk1/testfile/" + "file_" + j))){
                    String thisLine = null;
                    while((thisLine = reader.readLine()) != null){
                        //Thread.sleep(1000);
                        System.out.println("file_" + j + "内容：" + thisLine);
                    }

                    countDownLatch.countDown();
                }catch (Exception e){
                    e.printStackTrace();
                }
            });
        }
        countDownLatch.await();
        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toMillis();
        System.out.println("所有文件都已处理完！耗时：" + timeElapsed);
    }
}
