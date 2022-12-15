package org.example.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;

public class TestUnsalf {
    public static void main(String[] args) {
        Thread mainThread = Thread.currentThread();
        Unsafe unsafe = null;
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null);
        } catch (Exception e) {

        }

        //Unsafe finalUnsafe = unsafe;
        Unsafe finalUnsafe = unsafe;
        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(5);
                System.out.println("subThread try to unpark mainThread");
                finalUnsafe.unpark(mainThread);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        System.out.println("park main mainThread");
        unsafe.park(false,0L);
        System.out.println("unpark mainThread success");

    }
}
