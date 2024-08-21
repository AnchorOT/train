package com.anchor.train.member.controller;

import com.anchor.train.common.context.LoginMemberContext;
import com.anchor.train.common.resp.CommonResp;
import com.anchor.train.common.resp.PageResp;
import com.anchor.train.member.req.PassengerQueryReq;
import com.anchor.train.member.req.PassengerSaveReq;
import com.anchor.train.member.resp.PassengerQueryResp;
import com.anchor.train.member.service.PassengerService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/passenger")
public class PassengerController {
    @Resource
    private PassengerService passengerService;



    @PostMapping("/save")
    public CommonResp<Object> save(@Valid @RequestBody PassengerSaveReq req) {
       passengerService.save(req);
        return new CommonResp<>();
    }
    @GetMapping("/query-list")
    public CommonResp<PageResp<PassengerQueryResp>> queryList(@Valid PassengerQueryReq req) {
        req.setMemberId(LoginMemberContext.getId());
        PageResp<PassengerQueryResp> passengerList = passengerService.queryList(req);
        return new CommonResp<>(passengerList);
    }

}
