package com.example.demo.common;

/**
 * Created by pipher on 2018/6/12.
 */
public enum ApiReturnCodeEnum {


    Success("0000", "请求成功"),
    NEED_LOGIN("0001","长时间未使用，请重新登录"),

    QUERY_FAIL("0002", "数据查询失败！");












    private String code;
    private String msg;

    private ApiReturnCodeEnum(String code, String message) {
        this.code = code;
        this.msg = message;
    }

    public String getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

    public static ApiReturnCodeEnum getApiReturnCodeEnum(String code) {
        ApiReturnCodeEnum[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            ApiReturnCodeEnum e = var1[var3];
            if(e.getCode().equals(code)) {
                return e;
            }
        }

        return null;
    }
}
