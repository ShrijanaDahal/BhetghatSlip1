package com.samansar.bhetghatslip.API;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class RetrofitHelper<T> {
    private static RetrofitHelper helper;
    private static Retrofit retrofit;
    private final String BASE_URL = "http://sushilbhattarai.com.np/gokarnabista/api/";

    private RetrofitHelper() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .readTimeout(2, TimeUnit.MINUTES)
                .connectTimeout(2, TimeUnit.MINUTES)
                .retryOnConnectionFailure(true)
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public static RetrofitHelper getInstance() {
        if (helper == null)
            helper = new RetrofitHelper();
        return helper;
    }

    public T getService(Class className) {
        return (T) retrofit.create(className);
    }
}