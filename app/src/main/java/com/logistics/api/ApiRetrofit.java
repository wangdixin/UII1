package com.logistics.api;

import com.logistics.api.base.BaseApiRetrofit;
import com.logistics.api.factory.CustomGsonConverterFactory;
import com.logistics.api.factory.StringConverterFactory;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observable;

/**
 * @描述 使用Retrofit对网络请求进行配置
 */
public class ApiRetrofit extends BaseApiRetrofit {

    public MyApi mApi;
    public static ApiRetrofit mInstance;

    private ApiRetrofit() {
        super();
        //在构造方法中完成对Retrofit接口的初始化
        mApi = new Retrofit.Builder()
                .baseUrl(mApi.BASE_URL)
                .client(getClient())
//                .addConverterFactory(GsonConverterFactory.create(buildGson()))
                .addConverterFactory(CustomGsonConverterFactory.create())
                .addConverterFactory(StringConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
                .create(MyApi.class);
    }


    public static ApiRetrofit getInstance() {
        if (mInstance == null) {
            synchronized (ApiRetrofit.class) {
                if (mInstance == null) {
                    mInstance = new ApiRetrofit();
                }
            }
        }
        return mInstance;
    }

    public Observable login(String account, String pwd) {
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("account",account);
        paramMap.put("password",pwd);
        return mApi.login(paramMap);
    }

}
