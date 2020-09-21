package com.logistics.api.factory;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by paopao on 2018/2/5.
 */

public class StringResponseBodyConverter implements Converter<ResponseBody, String>{
    @Override
    public String convert(ResponseBody value) throws IOException {
        try {
            return value.string();
        } finally {
            value.close();
        }
    }
}
