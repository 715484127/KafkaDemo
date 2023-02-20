package org.example.completablefuture;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class TestCompletableFuture {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        List<CompletableFuture<String>> cfList = new ArrayList<>();
        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(()->{
            try {
                System.out.println("thread cf1 sleep star");
                //Thread.sleep(5000);
                System.out.println("thread cf1 sleep end");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            int a = 1+1;
            return "thread cf1 return";
        });

        CompletableFuture<String> cf2 = CompletableFuture.supplyAsync(()->{
            try {
                System.out.println("thread cf2 sleep star");
                Thread.sleep(10000);
                System.out.println("thread cf2 sleep end");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            int a = 1+1;
            return "thread cf2 return";
        });

        cfList.add(cf1);
        cfList.add(cf2);

        CompletableFuture completableFuture = CompletableFuture.allOf(cfList.toArray(new CompletableFuture[2]));

        CompletableFuture<List<String>> listCompletableFuture = completableFuture.thenApplyAsync(v -> {
            List<String> r_list = new ArrayList<>();
            for (CompletableFuture<String> c:cfList) {
                try {
                    String temp = c.get();
                    r_list.add(temp);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (ExecutionException e) {
                    throw new RuntimeException(e);
                }
            }
            return r_list;
        });
//        completableFuture.join();
//        String cf1_return = cf1.get();
//        System.out.println(cf1_return);
        List<String> list = (List<String>) listCompletableFuture.get();
        list.size();
        System.out.println("main thread......");
    }
}
