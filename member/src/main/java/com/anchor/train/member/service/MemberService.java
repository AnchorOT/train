package com.anchor.train.member.service;

import cn.hutool.core.collection.CollUtil;
import com.anchor.train.common.exception.BusinessException;
import com.anchor.train.common.exception.BusinessExceptionEnum;
import com.anchor.train.common.util.SnowUtil;
import com.anchor.train.member.domain.Member;
import com.anchor.train.member.domain.MemberExample;
import com.anchor.train.member.mapper.MemberMapper;
import com.anchor.train.member.req.MemberRegisterReq;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    @Resource
    MemberMapper memberMapper;
    public long count(){
        return memberMapper.countByExample(null);
    }

    public long register(MemberRegisterReq req){
        String mobile = req.getMobile();
        MemberExample example = new MemberExample();
        example.createCriteria().andMobileEqualTo(mobile);
        List<Member> members = memberMapper.selectByExample(example);

        if(CollUtil.isNotEmpty(members)){
//            return members.get(0).getId();
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_EXIST);
        }
        Member member = new Member();
        member.setId(SnowUtil.getSnowflakeNextId());
        member.setMobile(mobile);
        memberMapper.insertSelective(member);
        return member.getId();
    }
}
