package com.example.workit;

import java.sql.Date;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface JsonPlaceHolderApi  {
    @FormUrlEncoded
    @POST("adduser")
    Call<String> adduser(
            @Field("email") String email,
            @Field("name") String name,
            @Field("lastname") String lastname,
            @Field("adress") String adress,
            @Field("password") String password,
            @Field("phone") String phone


            );
    @GET("getallusers")
    Call<user[]> showL(

    );
    @FormUrlEncoded
    @POST("getallservises")
    Call<Services[]> getalljobs(
            @Field("idp") String idp

    );
    @FormUrlEncoded
    @POST("deleteservises")
    Call<String> deleteservice(
            @Field("uid") String uid

    );
    @FormUrlEncoded
    @POST("getuser")
    Call<String> log(
            @Field("email") String email,
            @Field("password") String password
    );
    @Multipart
    @POST("/upload")
    Call<ResponseBody> postImage(@Part MultipartBody.Part image, @Part("upload") RequestBody name);

    @FormUrlEncoded
    @POST("addjob")
    Call<ResponseBody> addjob(
            @Field("idp") String idp,
            @Field("title") String title,
            @Field("price") int price,
            @Field("description") String description,
            @Field("deadline")Date deadline,
            @Field("placelng") Double placelng,
            @Field("placelat") Double placelat
            );

    @FormUrlEncoded
    @POST("getservicebyprovider")
    Call<Services[]> getmyjobs(
            @Field("uid") String providerid

    );
    @FormUrlEncoded
    @POST("setrequest")
    Call<ResponseBody> setrequest(
            @Field("idservice") String idservice,
            @Field("idemploye") String idemploye

    );

    @FormUrlEncoded
    @POST("setacceptedrequest")
    Call<ResponseBody> acceptrequest(
            @Field("idservice") String idservice,
            @Field("idemploye") String idemploye

    );
    @FormUrlEncoded
    @POST("updateserviceetat")
    Call<ResponseBody> updateservice(
            @Field("id") String id


    );
    @FormUrlEncoded
    @POST("updateuser")
    Call<String> updateuser(
            @Field("id") String id,
            @Field("email") String email,
            @Field("name") String name,
            @Field("lastname") String lastname,
            @Field("adress") String adress,
            @Field("phone") String phone


    );


    @GET("getallrequests")
    Call<requests[]> getallrequests();
    @GET("getallacceptedrequests")
    Call<requests[]> getallacceptedrequests();

}