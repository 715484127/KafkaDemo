package org.example.proxy;

public class ProxySmsServiceStatic implements SmsService{
    SmsService smsService;

    public ProxySmsServiceStatic(SmsService smsService) {
        this.smsService = smsService;
    }

    @Override
    public String send(String msg) {
        System.out.println("before---------------------");
        smsService.send(msg);
        System.out.println("after----------------------");
        return msg;
    }
}
