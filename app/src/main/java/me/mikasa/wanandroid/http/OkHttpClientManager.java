package me.mikasa.wanandroid.http;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by mikasa on 2018/12/28.
 */
public class OkHttpClientManager {
    private static OkHttpClient okHttpClient;
    public static OkHttpClient getOkHttpClient(){
        if (okHttpClient==null){
            synchronized (OkHttpClientManager.class){
                if (okHttpClient==null){
                    okHttpClient=new OkHttpClient.Builder()
                            .cookieJar(new WanAndroidCookieJar())
                            .connectTimeout(10,TimeUnit.SECONDS)
                            .readTimeout(10,TimeUnit.SECONDS)
                            .build();
                }
            }
        }
        return okHttpClient;
    }
}
