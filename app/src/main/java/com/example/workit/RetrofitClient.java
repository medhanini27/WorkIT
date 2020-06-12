package com.example.workit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private JsonPlaceHolderApi jsonPlaceHolderApi;

    private static Retrofit mInstance;
    public static Retrofit getmInstance() {
        if (mInstance==null){
            mInstance = new Retrofit.Builder()
                    .baseUrl("http://41.226.11.252:11866/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();}
        return mInstance;
    }

}
