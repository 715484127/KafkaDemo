package org.example.threadlocal;

import java.util.ArrayList;
import java.util.List;

public class TestThreadLocal {

    private List<String> messages = new ArrayList<String>();

    public static final ThreadLocal<TestThreadLocal> holder = ThreadLocal.withInitial(TestThreadLocal::new);

    public static void add(String message) {
        holder.get().messages.add(message);
    }

    public static List<String> clear() {
        List<String> messages = holder.get().messages;
        holder.remove();

        System.out.println("size: " + holder.get().messages.size());
        return messages;
    }

    public static void main(String[] args) {
        TestThreadLocal.add("一枝花算不算浪漫");
        System.out.println(holder.get().messages);
        TestThreadLocal.clear();

    }
}
