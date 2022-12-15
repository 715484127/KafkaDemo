package org.example.floattest;

import java.math.BigDecimal;

public class TestFloat {
    public static void main(String[] args) {
        float a = 1.0F;
        float b = 0.9F;
        float c = 0.8F;
        System.out.println(a-b == b-c);

        BigDecimal aa = new BigDecimal(1.0);
        BigDecimal bb = new BigDecimal(0.9);
        BigDecimal cc = new BigDecimal(0.8);
        if(aa.subtract(bb).compareTo(bb.subtract(cc)) == 0){
            System.out.println("true");
        }
    }
}
