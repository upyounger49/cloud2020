package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import com.sun.media.jfxmedia.logging.Logger;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.patterns.IToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.rmi.runtime.Log;

import javax.annotation.Resource;
import java.util.List;

import static java.lang.System.out;

@RestController //=@Controller+@ResponseBody
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Resource
    private DiscoveryClient discoveryClient;

    @Value("${server.port}")
    private String serverPort;
    /**
     * @RequestBody 注解 微服务间调用时候
     * post是写操作 get是读操作
     * @param payment
     * @return
     */
    @PostMapping("/payment/add")
    public CommonResult add(@RequestBody Payment payment){
        final int result = paymentService.add(payment);

        if(result > 0){
            return new CommonResult(200,"插入成功！，serverPort:"+serverPort,result);
        }else {
            return new CommonResult(999,"插入失败！",null);
        }
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult getItById(@PathVariable("id") Long id){
        final Payment payment = paymentService.getPaymentById(id);

        if(payment != null){
            return new CommonResult(200,"查询成功！,serverPort:"+serverPort,payment);
        }else {
            return new CommonResult(999,"查询无记录！",null);
        }
    }

    /**
     * 获取微服务的详细信息
     * @return
     */
    @GetMapping("/payment/dicovery")
    public Object discovery(){
        //eureka下注册的微服务有什么
        final List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info(service);
        }
        //获取某个微服务下的内部信息，如CLOUD-PAYMENT-SERVICE下集群服务的详细信息
        final List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri());
        }

        return discoveryClient;
    }
}
