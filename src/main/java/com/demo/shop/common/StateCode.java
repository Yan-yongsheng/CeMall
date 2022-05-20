package com.demo.shop.common;

public enum StateCode {
    SUCCESS(200, "请求成功！"),
    FAIL(999, "请求失败！");

    private Integer code;
    private String msg;

    StateCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
