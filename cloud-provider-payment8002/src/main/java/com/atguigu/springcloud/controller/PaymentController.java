package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import com.sun.media.jfxmedia.logging.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController //=@Controller+@ResponseBody
public class PaymentController {

    @Resource
    private PaymentService paymentService;

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
            return new CommonResult(200,"插入成功！,serverPort:"+serverPort,result);
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
}
