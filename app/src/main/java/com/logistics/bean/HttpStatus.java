package com.logistics.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by paopao on 2018/2/5.
 */

public class HttpStatus {

    @SerializedName("code")
    private int mCode;
    @SerializedName("msg")
    private String mMessage;

    public int getCode() {
        return mCode;
    }

    public String getMessage() {
        return mMessage;
    }

    /**
     * API是否请求失败
     *
     * @return 失败返回true, 成功返回false
     */
    public boolean isCodeInvalid() {
        return mCode !=1;
    }


}
