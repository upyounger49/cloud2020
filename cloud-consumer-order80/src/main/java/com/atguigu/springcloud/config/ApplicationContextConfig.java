package com.atguigu.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig {

    /**
     * RestTemplate 用于module之间的接口调用
     * LoadBalanced 负载均衡注解 一个服务（集群名称）下有多台机器 需要在调用时候协调
     * 什么机制来调 调哪一个服务器（支付模块）
     * LoadBalanced 该注解 赋予RestTemplate负载均衡的能力
     * @return
     */
    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
