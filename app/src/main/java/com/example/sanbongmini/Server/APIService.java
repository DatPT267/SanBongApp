package com.example.sanbongmini.Server;


import android.content.Context;

public class APIService {
    private static String base_URL = "http://192.168.31.5/phongthinghiem/public/api/";

    public static DataService getService(Context context){
        return APIUtils.getClientRetrofit(base_URL, context).create(DataService.class);
    }
}