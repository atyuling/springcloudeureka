package com.example.servicehi8762;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@RestController//需要spring-boot-starter-web这个依赖
public class ServiceHi8762Application {

    public static void main(String[] args) {
        SpringApplication.run(ServiceHi8762Application.class, args);
    }

    @Value("${server.port}")
    private String port;

    @RequestMapping("/hi")
    public String hello(@RequestParam(value = "name",defaultValue = "eureka122533")String name){
        return "hello,i'm"+name+",from"+port;
    }

}
