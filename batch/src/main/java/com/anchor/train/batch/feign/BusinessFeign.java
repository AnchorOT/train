package com.anchor.train.batch.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

//@FeignClient("business")
@FeignClient(value = "business" ,url = "http://localhost:8002/business")
public interface BusinessFeign {
    @GetMapping("/hello")
    String hello();
}
