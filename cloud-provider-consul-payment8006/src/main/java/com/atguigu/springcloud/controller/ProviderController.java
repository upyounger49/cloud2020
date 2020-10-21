package com.atguigu.springcloud.controller;

import cn.hutool.core.lang.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProviderController {

    @Value("${server.port}")
    private String serverPort;

    @RequestMapping("/payment/consul")
    public String getPayment(){
        return "consul :"+serverPort+"\t"+ UUID.randomUUID();
    }
}
