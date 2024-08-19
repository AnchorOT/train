package com.anchor.train.member.controller;

import com.anchor.train.common.resp.CommonResp;
import com.anchor.train.member.req.MemberLoginReq;
import com.anchor.train.member.req.MemberRegisterReq;
import com.anchor.train.member.req.MemberSendCodeReq;
import com.anchor.train.member.resp.MemberLoginResp;
import com.anchor.train.member.service.MemberService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
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
    public CommonResp<Long> register(@Valid MemberRegisterReq req) {
        long register = memberService.register(req);
//        CommonResp<Long> commonResp = new CommonResp<Long>();
//        commonResp.setContent(register);
//        return commonResp;
        return new CommonResp<>(register);
    }
    @PostMapping("/send-code")
    public CommonResp<Long> sendCode(@Valid @RequestBody MemberSendCodeReq req) {
        memberService.SendCode(req);
        return new CommonResp<>();
    }
    @PostMapping("/login")
    public CommonResp<MemberLoginResp> login(@Valid @RequestBody MemberLoginReq req) {
        MemberLoginResp resp = memberService.login(req);
        return new CommonResp<>(resp);
    }
}
