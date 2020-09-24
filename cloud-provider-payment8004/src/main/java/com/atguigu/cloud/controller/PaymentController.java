package com.atguigu.cloud.controller;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.lang.copier.SrcToDestCopier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @RequestMapping("/payment/zk")
    public String getPayment(){
        return "zookeeper :"+serverPort+"\t"+ UUID.randomUUID();
    }
}
