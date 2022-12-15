package org.example.proxy.jdk;

import org.example.proxy.SmsService;

public class TestJDKProxy {
    public static void main(String[] args) {
        SmsService smsService = (SmsService) JdkProxyFactory.getProxy(new SmsServiceImpl());
        smsService.send("JDK Proxy");
    }
}
