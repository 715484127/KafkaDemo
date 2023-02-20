package org.example.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestStream1 {
    public static void main(String[] args) {
        List<String> list = new ArrayList(6);
        list.add("1");
        list.add("2");
        list.add("3");
        List<String> list1 = list.stream().filter(s-> {
            if(s.equals("2")) {
                System.out.println(s);
                return true;
            }
            return false;
        }).collect(Collectors.toList());

        System.out.println("step1"); 

    }
}
