package com.example.servicefeign.servcie;

import com.example.servicefeign.servcie.impl.FeignServiceHystric;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created with IntelliJ IDEA.
 * User: wrk
 * Date: 2019/5/22
 * Time: 17:30
 * Description:
 */
//service-hi指的的eureka中的服务名
//FeignClient自动加载有负载均衡
@FeignClient(value = "service-hi",fallback = FeignServiceHystric.class)
public interface FeignService {
    //hi这里的指的是要访问的那个方法的RequestMapping
    @RequestMapping(value = "/hi",method = RequestMethod.GET)
    public String getHI(@RequestParam(value = "name")String name);
}
