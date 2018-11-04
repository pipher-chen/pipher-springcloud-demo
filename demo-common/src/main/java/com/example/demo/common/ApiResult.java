package com.example.demo.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by pipher
 */
public class ApiResult<T> {
    private static final Logger logger = LoggerFactory.getLogger(ApiResult.class);
    private boolean success;
    private String errorCode;
    private String errorMsg;
    private T data;

    public ApiResult() {
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static ApiResult success(){
        return success((Object)null);
    }

    public static ApiResult success(Object data){
        ApiResult apiResult = new ApiResult();
        apiResult.setSuccess(true);
        apiResult.setData(data);
        if (logger.isDebugEnabled()) {
            logger.debug("输出响应：" + apiResult);
        }
        return apiResult;
    }

    public static ApiResult failed(String errorCode,String errorMsg){
        ApiResult apiResult = new ApiResult();
        apiResult.setSuccess(false);
        apiResult.setErrorCode(errorCode);
        apiResult.setErrorMsg(errorMsg);
        if (logger.isDebugEnabled()) {
            logger.debug("输出响应：" + apiResult);
        }
        return apiResult;
    }

    public <T> ApiResult<T> wrapIndicateTypeRestResponse(T data) {
        ApiResult apiResult = new ApiResult();
        apiResult.setSuccess(this.isSuccess());
        apiResult.setErrorMsg(this.getErrorMsg());
        apiResult.setErrorCode(this.getErrorCode());
        apiResult.setData(data);
        return apiResult;
    }

    public static ApiResult failed(ApiReturnCodeEnum error){
        ApiResult apiResult = new ApiResult();
        apiResult.setSuccess(false);
        apiResult.setErrorCode(error.getCode());
        apiResult.setErrorMsg(error.getMsg());
        return apiResult;
    }

    @Override
    public String toString() {
        return "ApiResult{" +
                "success="+success+
                ",code=" + errorCode +
                ", message='" + errorMsg + '\'' +
                ", data=" + data +
                '}';
    }


//    psvm + tab + tab
//    public static void main(String[] args) {
//        System.out.println( );//suot + tab + tab
//
//    }
}