package me.mikasa.wanandroid.http;

import me.mikasa.wanandroid.api.ApiService;
import me.mikasa.wanandroid.constants.Constants;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mikasa on 2018/12/28.
 */
public class RetrofitHelper {
    public static RetrofitHelper instance;
    private Retrofit retrofit;
    private final ApiService apiService;
    public ApiService getApiService(){
        return apiService;
    }
    private RetrofitHelper(){
        //初始化retrofit
        retrofit=new Retrofit.Builder()
                .baseUrl(Constants.baseUrl)
                .client(OkHttpClientManager.getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        //初始化api
        apiService=retrofit.create(ApiService.class);
    }
    public static RetrofitHelper getInstance(){
        if (instance==null){
            synchronized (OkHttpClientManager.class){
                if (instance==null){
                    instance=new RetrofitHelper();
                }
            }
        }
        return instance;
    }

}
