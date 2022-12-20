package org.example.threadhandlfile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.Duration;
import java.time.Instant;

public class OneThreadFileDemo {
    public static void main(String[] args) {
        File filedir = new File("/mnt/disk1/testfile");
        File files[] = filedir.listFiles();
        int fileCount = 0;
        for (File file : files) {
            if(file.isFile()){
                fileCount++;
            }
        }
        System.out.println("文件数量：" + fileCount);

        Instant start = Instant.now();
        for(int i = 0;i<fileCount;i++){
            int j = i+1;
            try(BufferedReader reader = new BufferedReader(new FileReader("/mnt/disk1/testfile/" + "file_" + j))){
                String thisLine = null;
                while((thisLine = reader.readLine()) != null){
                    //Thread.sleep(1000);
                    System.out.println("file_" + j + "内容：" + thisLine);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toMillis();
        System.out.println("所有文件都已处理完！耗时：" + timeElapsed);
    }
}
