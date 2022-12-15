package org.example.proxy.jdk;

import org.example.proxy.SmsService;

public class SmsServiceImpl implements SmsService {
    @Override
    public String send(String msg) {
        System.out.println("msg=" + msg);
        return msg;
    }
}
