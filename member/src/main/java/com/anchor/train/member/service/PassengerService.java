package com.anchor.train.member.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import com.anchor.train.common.context.LoginMemberContext;
import com.anchor.train.common.util.SnowUtil;
import com.anchor.train.member.domain.Passenger;
import com.anchor.train.member.domain.PassengerExample;
import com.anchor.train.member.mapper.PassengerMapper;
import com.anchor.train.member.req.PassengerQueryReq;
import com.anchor.train.member.req.PassengerSaveReq;
import com.anchor.train.member.resp.PassengerQueryResp;
import com.github.pagehelper.PageHelper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassengerService {
    @Resource
    private PassengerMapper passengerMapper;

    public void save(PassengerSaveReq req) {
        DateTime now = DateTime.now();
        Passenger passenger = BeanUtil.copyProperties(req, Passenger.class);
        passenger.setId(SnowUtil.getSnowflakeNextId());
        passenger.setMemberId(LoginMemberContext.getId());
        passenger.setCreateTime(now);
        passenger.setUpdateTime(now);
        passengerMapper.insert(passenger);
    }
    public List<PassengerQueryResp> queryList(PassengerQueryReq req) {
        PassengerExample passengerExample = new PassengerExample();
        PassengerExample.Criteria criteria = passengerExample.createCriteria();
        if (ObjectUtil.isNotNull(req.getMemberId())){

            criteria.andMemberIdEqualTo(req.getMemberId());
        }
        PageHelper.startPage(2,2);
        List<Passenger> passengerList = passengerMapper.selectByExample(passengerExample);
        return BeanUtil.copyToList(passengerList, PassengerQueryResp.class);
    }
}
