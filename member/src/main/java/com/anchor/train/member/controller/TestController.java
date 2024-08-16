package com.anchor.train.member.controller;

import com.anchor.train.member.service.MemberService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
public class TestController {
    @Resource
    private MemberService memberService;
    @GetMapping("/count")
    public long count() {
        return memberService.count();
    }
}
