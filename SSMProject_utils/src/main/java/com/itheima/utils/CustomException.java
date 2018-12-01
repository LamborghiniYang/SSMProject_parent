package com.itheima.utils;

/**
 * @Auther: wyan
 * @Date: 2018/11/28 18:16
 * @Description:
 */
public class CustomException extends  Exception {

    private String errorCode;
    private String errorMsg;

    public CustomException(String errorCode,String errorMsg){
        this.errorCode=errorCode;
        this.errorMsg=errorMsg;
    }

    @Override
    public String getMessage() {
        return  this.errorMsg;
    }
}
