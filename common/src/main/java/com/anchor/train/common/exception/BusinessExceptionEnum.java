package com.anchor.train.common.exception;

public enum BusinessExceptionEnum {
    MEMBER_MOBILE_EXIST("手机号已存在"),
    MEMBER_MOBILE_NOT_EXIST("手机号未注册,请先获取验证码"),
    MEMBER_MOBILE_CODE_ERROR("短信验证码错误");
    private  String desc;

    BusinessExceptionEnum(String desc) {
        this.desc = desc;
    }
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "BusinessException{" +
                "desc='" + desc + '\'' +
                '}';
    }
}
