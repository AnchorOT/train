package com.anchor.train.member.controller;

import com.anchor.train.member.service.MemberService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
public class MemberController {
    @Resource
    private MemberService memberService;
    @GetMapping("/count")
    public long count() {
        return memberService.count();
    }
    @PostMapping("/register")
    public long register(String mobile) {
        return memberService.register(mobile);
    }
}
