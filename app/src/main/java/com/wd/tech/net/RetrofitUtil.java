package com.wd.tech.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;

import com.wd.tech.App;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtil {
    private static RetrofitUtil instance;
    private final OkHttpClient okHttpClient;
    private final Retrofit retrofit;

    private RetrofitUtil() {
        //日志拦截器
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //OKhttpclient
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5,TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(new Interceptor() {
                    @NotNull
                    @Override
                    public Response intercept(@NotNull Chain chain) throws IOException {
                        Request request = chain.request();
                        Request.Builder builder = request.newBuilder();
                        SpUtil instance = SpUtil.getInstance();
                        int userId = instance.getSpInt("userId");
                        String sessionId = instance.getSpString("sessionId");
                        if (userId!=-1){
                            builder.addHeader("userId",userId+"");
                        }
                        if (!TextUtils.isEmpty(sessionId)){
                            builder.addHeader("sessionId",sessionId);
                        }
                        builder.addHeader("ak","aa");
                        Request build = builder.build();
                        return chain.proceed(build);
                    }
                })
                .build();
        //retrofit
        retrofit = new Retrofit.Builder()
                .baseUrl(ApiUrl.URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
    public static RetrofitUtil getInstance(){
        if (instance == null){
            instance = new RetrofitUtil();
        }
        return instance;
    }
    //创建service
    public ApiService createService(){
        ApiService apiService = retrofit.create(ApiService.class);
        return apiService;
    }
    //网络判断
    public boolean  net(){
        ConnectivityManager connectivityManager= (ConnectivityManager) App.context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if(activeNetworkInfo!=null){
            boolean available = activeNetworkInfo.isAvailable();
            return available;
        }
        return false;
    }
}
