package com.anchor.train.batch.job;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import com.anchor.train.batch.feign.BusinessFeign;
import com.anchor.train.common.resp.CommonResp;
import jakarta.annotation.Resource;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.util.Date;

public class DailyTrainJob implements Job {
    @Resource
    BusinessFeign businessFeign;
    private static final Logger LOG = LoggerFactory.getLogger(DailyTrainJob.class);
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        MDC.put("LOG_ID", System.currentTimeMillis() + RandomUtil.randomString(3));
        LOG.info("开始生成每日车次数据");
        Date date = new Date();
        DateTime dateTime = DateUtil.offsetDay(date, 15);
        Date jdkDate = dateTime.toJdkDate();
        CommonResp<Object> commonResp = businessFeign.genDaily(jdkDate);
        LOG.info("生成每日车次数据结束  15天后 {}", commonResp);
    }
}
