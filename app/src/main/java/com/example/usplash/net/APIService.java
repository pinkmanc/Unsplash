package com.example.usplash.net;

import com.example.usplash.UnsplashInit;
import com.example.usplash.data.result.UnsplashResult;

import io.reactivex.Flowable;
import io.reactivex.plugins.RxJavaPlugins;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.Query;

/**
 *  描述: API 请求接口
 *
 * @author pinkmanc on 2023/7/2 20:42
 */
public class APIService {
    private static String BASE_URL = "https://unsplash.com/";
    public APIs apis;
    private static APIService instance;
    public static APIService getInstance(){
        if(instance==null){
            instance=new APIService();
        }
        return instance;
    }
    private APIService(){
        Retrofit storeRestAPI=new Retrofit.Builder().baseUrl(BASE_URL)
                .client(UnsplashInit.mOkHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apis=storeRestAPI.create(APIs.class);

    }
    public interface APIs{
        /* Unsplash.com 图片*/
        @GET("https://unsplash.com/")
        Flowable<UnsplashResult> fetchUnsplashPT(
                @Query("count") int count,
                @Query("page") int page
        );
    }
}
