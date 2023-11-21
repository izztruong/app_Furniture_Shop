package com.example.app_furniture_shop.Database;

import com.example.app_furniture_shop.Interface.APIManagerService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String BaseUrl = "http://192.168.1.31:3000";

    public static APIManagerService getService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(APIManagerService.class);
    }
}
