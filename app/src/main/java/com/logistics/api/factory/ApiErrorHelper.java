package com.logistics.api.factory;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.gson.JsonParseException;
import com.logistics.MainActivity;
import com.logistics.utils.Constant;
import com.logistics.utils.UIUtils;

import org.json.JSONException;

import java.io.IOException;
import java.net.ConnectException;


/**
 * Created by paopao on 2018/7/13.
 */

public class ApiErrorHelper {
    private static final String TAG = "ApiErrorHelper";
    public static void handleCommonError(Context context, Throwable e) {
        if (e instanceof IOException) {
            UIUtils.showToast("连接失败");
        }else if(e instanceof JsonParseException || e instanceof JSONException){
            UIUtils.showToast("账号或密码错误!");
        }else if(e instanceof ConnectException){
            UIUtils.showToast("端口连接失败");
        } else {
            UIUtils.showToast("不存在这个条码或其他错误，请重试");
        }
    }
}
