package com.holo.support.util;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class OkHttpUtil {
    private static  OkHttpUtil instance;
    private Retrofit retrofit;

    private OkHttpUtil(){
        init();
    }
    public static OkHttpUtil getInstance(){
        if (instance == null) {
            instance = new OkHttpUtil();
        }
        return instance;
    }

    private void init() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);//打印请求参数
        OkHttpClient okHttpClient=new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .connectTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5,TimeUnit.SECONDS)
                .readTimeout(5,TimeUnit.SECONDS)
                .build();
        retrofit=new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("http://mobile.bwstudent.com/health/")//域名
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//使用rxjava进行回调数据
                .addConverterFactory(GsonConverterFactory.create())//响应结果的解析器
                .build();
    }

    public <T> T create(final  Class<T> service){
        return  retrofit.create(service);
    }
}
