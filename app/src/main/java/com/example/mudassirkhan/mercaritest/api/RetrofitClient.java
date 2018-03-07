package com.example.mudassirkhan.mercaritest.api;


import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Mudassir Khan on 2/20/2018
 */

public class RetrofitClient {

    //base url i have set
    private static final String BASE_URL = "https://raw.githubusercontent.com/m-rec/f278b935bf419a41b5d1a2f0a8cbbf5ed590bae8/master/app/src/main/assets/";

    private static volatile Retrofit retrofit;

    private RetrofitClient() {
    }

    //create an Retrofit Client object
    public static Retrofit getClient() {
        Retrofit instance = retrofit;
        if (instance == null) {
            synchronized (RetrofitClient.class) {
                OkHttpClient okHttpClient = new OkHttpClient().newBuilder().addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        Response originalRequest = chain.proceed(chain.request());
                        originalRequest.header("Content-Type", "application/x-www-form-urlencoded");
                        originalRequest.header("Accept", "application/x-www-form-urlencoded");
                        return originalRequest;
                    }
                }).build();

                instance = retrofit;
                if (instance == null) {
                    instance = retrofit = new retrofit2.Retrofit.Builder()
                            .baseUrl(BASE_URL)
                            .client(okHttpClient)
                            .addCallAdapterFactory(new ErrorHandlingAdapter.ErrorHandlingCallAdapterFactory())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                }
            }
        }

        return instance;
    }

}
