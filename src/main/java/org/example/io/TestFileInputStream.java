package org.example.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class TestFileInputStream {
    public static void main(String[] args) {
        int content;
        try(InputStream fileIn = new FileInputStream("/home/august/gitSource/KafkaDemo/src/main/resources/application.yaml")){
            System.out.println("available=" + fileIn.available());
            while ((content = fileIn.read()) != -1) {
                System.out.println(content);
                System.out.println((char) content);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
