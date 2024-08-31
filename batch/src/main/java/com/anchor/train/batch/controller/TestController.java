package com.anchor.train.batch.controller;

import com.anchor.train.batch.feign.BusinessFeign;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Resource
    BusinessFeign businessFeign;

    private static final Logger LOG = LoggerFactory.getLogger(TestController.class);

    @GetMapping("/hello")
    public String hello() {
        System.out.println(businessFeign.hello());
        String hello = businessFeign.hello();
        LOG.info(hello);
        return "Hello World! Batch! "+hello;
    }
}
