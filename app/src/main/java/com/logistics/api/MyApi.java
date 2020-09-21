package com.logistics.api;

import com.logistics.bean.LoginBean;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * @描述 server端api
 */

public interface MyApi {

    String BASE_URL = "http://wuliu.5ysj.net/";

    //登录
    @FormUrlEncoded
    @POST("api/warehouse/login")
    Observable<LoginBean> login(@FieldMap Map<String, String> paramMapg);

}