package com.anchor.train.member.controller;

import com.anchor.train.member.req.MemberRegisterReq;
import com.anchor.train.member.service.MemberService;
import com.anchor.train.common.resp.CommonResp;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
public class MemberController {
    @Resource
    private MemberService memberService;

    @GetMapping("/count")
    public CommonResp<Long> count() {

        Long count = memberService.count();
        CommonResp<Long> commonResp = new CommonResp<Long>();
        commonResp.setContent(count);
        return commonResp;
    }

    @PostMapping("/register")
    public CommonResp<Long> register(@Validated MemberRegisterReq req) {
        long register = memberService.register(req);
//        CommonResp<Long> commonResp = new CommonResp<Long>();
//        commonResp.setContent(register);
//        return commonResp;
        return new CommonResp<>(register);
    }
}
