package com.example.servicefeign.servcie.impl;

import com.example.servicefeign.servcie.FeignService;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: wrk
 * Date: 2019/5/23
 * Time: 14:03
 * Description:
 */
@Component
public class FeignServiceHystric implements FeignService {

    @Override
    public String getHI(String name) {
        return "hi,you have a trouble***************"+name;
    }
}
