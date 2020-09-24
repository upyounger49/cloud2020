package com.atguigu.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * 自己的负载均衡
 */
public interface Balancer {

    /**
     * 每个自定义负载均衡接口
     * 传一个服务集群  负载均衡算法之后 返回一个服务来处理请求
     * @param serviceInstances 机器总数
     * @return
     */
    ServiceInstance instances(List<ServiceInstance> serviceInstances);
}
