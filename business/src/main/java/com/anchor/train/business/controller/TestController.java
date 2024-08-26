package com.anchor.train.business.controller;

import cn.hutool.core.util.RandomUtil;

import com.anchor.train.business.service.TestService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Resource
    private TestService testService;

    @GetMapping("/hello")
    public String hello() throws InterruptedException {
        int i = RandomUtil.randomInt(1, 10);
        if (i <= 2) {
            throw new RuntimeException("测试异常");
        }
        return "Hello World! Business!";
    }


    @GetMapping("/hello1")
    public String hello1() throws InterruptedException {
        testService.hello2();
        return "Hello World! Business1!";
    }

}
