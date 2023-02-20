package org.example.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestStream {
    public static void main(String[] args) {
        List<String> list = new ArrayList(6);
        list.add("1");
        list.add("2");
        list.add("3");
        Stream<String> list1 = list.stream().filter(s-> {
            if(s.equals("2")) {
                System.out.println(s);  //list1.count() 执行后才会执行
                return true;
            }
            return false;
        });

        System.out.println("step1"); //先执行
        long a = list1.count();
    }
}
