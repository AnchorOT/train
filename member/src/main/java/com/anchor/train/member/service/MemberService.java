package com.anchor.train.member.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.RandomUtil;
import com.anchor.train.common.exception.BusinessException;
import com.anchor.train.common.exception.BusinessExceptionEnum;
import com.anchor.train.common.util.SnowUtil;
import com.anchor.train.member.domain.Member;
import com.anchor.train.member.domain.MemberExample;
import com.anchor.train.member.mapper.MemberMapper;
import com.anchor.train.member.req.MemberRegisterReq;
import com.anchor.train.member.req.MemberSendCodeReq;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    private static final Logger LOG = LoggerFactory.getLogger(MemberService.class);
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
    public void SendCode(MemberSendCodeReq req){

        String mobile = req.getMobile();
        MemberExample example = new MemberExample();
        example.createCriteria().andMobileEqualTo(mobile);
        List<Member> members = memberMapper.selectByExample(example);
        //手机号不存在  插入记录
        if(CollUtil.isEmpty(members)){
            LOG.info("手机号不存在，插入一条记录");
            Member member = new Member();
            member.setId(SnowUtil.getSnowflakeNextId());
            member.setMobile(mobile);
            memberMapper.insertSelective(member);
        }else {
            LOG.info("手机号存在");
        }
        //生成验证码
        String code = RandomUtil.randomString(4);
        code = "8888";
        LOG.info("生成短信验证码：{}",code);
        //保存短信记录表 手机号 短信验证码， 有效期 是否已使用 业务类型 发送时间 使用时间
        LOG.info("保存短信记录");
        // 对接短信通道 发送短信
        LOG.info("对接短信通道");
    }
}
