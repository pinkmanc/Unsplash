package com.example.unsplash.net;

import com.example.unsplash.UnsplashInit;
import com.example.unsplash.data.result.UnsplashResult;

import io.reactivex.Flowable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 *  描述: API 请求接口
 *
 * @author pinkmanc on 2023/7/2 20:42
 */
public class APIService {
    private static String BASE_URL = "https://api.unsplash.com/";
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
        @GET("https://api.unsplash.com/photos/random/")
        Flowable<UnsplashResult> fetchUnsplashPT(
                @Header("Authorization") String apiKey,
                @Query("count") int count
        );
    }
}
