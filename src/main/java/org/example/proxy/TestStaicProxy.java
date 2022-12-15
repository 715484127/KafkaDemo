package org.example.proxy;

public class TestStaicProxy {
    public static void main(String[] args) {
        SmsService smsService = new SmsServiceImpl();
        ProxySmsServiceStatic proxySmsServiceStatic = new ProxySmsServiceStatic(smsService);
        proxySmsServiceStatic.send("Test Static Proxy");
    }
}
