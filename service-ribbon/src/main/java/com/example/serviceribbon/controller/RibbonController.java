package com.example.serviceribbon.controller;

import com.example.serviceribbon.service.RibbonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 * User: wrk
 * Date: 2019/5/22
 * Time: 16:28
 * Description:
 */
@RestController
public class RibbonController {

    @Autowired
    private RibbonService ribbonService;

    @RequestMapping("/hi")
    public String getHi(@RequestParam(value = "name") String name){
        //\t表示tab键按一下，\n表示回车换行  ， \r 表示回车到当前行行首
        return ribbonService.getEurekaHi(name)+"\t"+"******ribbon********"+"\t"+ribbonService.getEurekaHi(name);
    }

}
