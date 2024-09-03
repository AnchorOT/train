package com.anchor.train.business.controller.admin;

import com.anchor.train.business.req.TrainSeatQueryReq;
import com.anchor.train.business.req.TrainSeatSaveReq;
import com.anchor.train.business.resp.TrainSeatQueryResp;
import com.anchor.train.business.service.TrainSeatService;
import com.anchor.train.common.resp.CommonResp;
import com.anchor.train.common.resp.PageResp;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/train-seat")
public class TrainSeatAdminController {

    @Resource
    private TrainSeatService trainSeatService;

    @PostMapping("/save")
    public CommonResp<Object> save(@Valid @RequestBody TrainSeatSaveReq req) {
        trainSeatService.save(req);
        return new CommonResp<>();
    }

    @GetMapping("/query-list")
    public CommonResp<PageResp<TrainSeatQueryResp>> queryList(@Valid TrainSeatQueryReq req) {
        PageResp<TrainSeatQueryResp> list = trainSeatService.queryList(req);
        return new CommonResp<>(list);
    }


}
