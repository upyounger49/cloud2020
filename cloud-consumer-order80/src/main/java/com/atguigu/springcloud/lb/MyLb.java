package com.atguigu.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MyLb implements Balancer {

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    private final int getNextServerIncrement(){
        int current;
        int next;
        do{
            current = atomicInteger.get();
            next = current > 2147483647  ? 0 : current + 1; //这是自己的LB算法
         }while (!atomicInteger.compareAndSet(current,next));

        return next;
    }

    @Override
    public ServiceInstance instances(List<ServiceInstance> serviceInstances) {
        int index = getNextServerIncrement() % serviceInstances.size();

        return serviceInstances.get(index);
    }
}
