//package com.anchor.train.batch.job;
//
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//// 适合单体应用 不适合集群
////没办法实时更新任务状态和策略
//@Component
//@EnableScheduling
//public class SpringBootTestJob {
//    @Scheduled(cron = "0/5 * * * * ?")
//    //可通过增加分布式锁解决
//    private void test(){
//        System.out.println("spring boot test");
//    }
//}
