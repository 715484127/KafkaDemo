package org.example.proxy;

public class SmsServiceImpl implements SmsService{
    @Override
    public String send(String msg) {
        System.out.println("send:" + msg);
        return msg;
    }
}
