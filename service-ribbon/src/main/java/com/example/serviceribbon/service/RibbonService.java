package com.example.serviceribbon.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created with IntelliJ IDEA.
 * User: wrk
 * Date: 2019/5/22
 * Time: 16:28
 * Description:
 */
@Service
public class RibbonService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "hiError")//如果在一定时间得不到回应，直接调用断路器中的方法
    public String getEurekaHi(String name){
        return restTemplate.getForObject("http://service-hi/hi?name="+name,String.class);
    }

    public String hiError(String name){
        return "hi**"+name+"***error";
    }

}
