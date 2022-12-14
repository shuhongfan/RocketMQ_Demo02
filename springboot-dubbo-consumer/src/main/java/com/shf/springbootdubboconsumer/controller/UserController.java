package com.shf.springbootdubboconsumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.shf.springbootdubbointerface.service.IUseService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Reference
    private IUseService useService;


    @RequestMapping("/sayHello")
    public String sayHello(String name){
        return useService.sayHello(name);
    }

}
