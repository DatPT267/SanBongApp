package com.example.sanbongmini.Server;

import android.content.Context;

import com.example.sanbongmini.SqlLite.UserReaderSqllite;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIUtils {
    private static Retrofit retrofit = null;
    private static UserReaderSqllite userReaderSqllite;

    public static Retrofit getClientRetrofit(String baseUrl, Context context){
        userReaderSqllite = new UserReaderSqllite(context);

        OkHttpClient mBuildder = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request originalRequest = chain.request();
                        Request.Builder builder = originalRequest.newBuilder()
                                .header("Accept","application/json")
                                .addHeader("authorization", "Bearer " + userReaderSqllite.getToken());
                        Request newRequest = builder.build();
                        return chain.proceed(newRequest);
                    }
                })
                .readTimeout(10000, TimeUnit.MILLISECONDS)
                .retryOnConnectionFailure(true)
                .build();
        Gson gson = new GsonBuilder().setLenient().create();

        retrofit = new Retrofit.Builder().baseUrl(baseUrl).client(mBuildder).
                addConverterFactory(GsonConverterFactory.create(gson)).build();
        return retrofit;
    }
}
