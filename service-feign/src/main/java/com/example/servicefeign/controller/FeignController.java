package com.example.servicefeign.controller;

import com.example.servicefeign.servcie.FeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 * User: wrk
 * Date: 2019/5/22
 * Time: 17:33
 * Description:
 */
@RestController
public class FeignController {

    //这里会报错，不用管，因为bean是在文件启动时加载的，编译时没有加载
    @Autowired
    private FeignService feignService;

    @RequestMapping("/hi")
    public String getHI(String name){
        return feignService.getHI(name)+"***************feign****************"+feignService.getHI(name);
    }
}
