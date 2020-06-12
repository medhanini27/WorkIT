package com.example.workit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class loading extends AppCompatActivity  {


    SharedPreferences mPrefs ;
    String userid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        mPrefs = getSharedPreferences("pref",MODE_PRIVATE);
        userid= mPrefs.getString("user id","");

        //get user job
        new Thread(new Runnable() {
            @Override
            public void run() {
                ArrayList<Services> myJobs = new ArrayList<>();
                Retrofit retrofit=RetrofitClient.getmInstance();
                JsonPlaceHolderApi myapi = retrofit.create(JsonPlaceHolderApi.class);
                Call<Services[]> call = myapi.getmyjobs(userid);
                Intent intent = new Intent(getApplicationContext(), JobsTabs.class);

                Response<Services[]> response = null;
                try {
                    response = call.execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                for(int i = 0; i<response.body().length; i++)

                {
                    Services s=new Services(response.body()[i].getId(),response.body()[i].getUid(),response.body()[i].getIdp(),response.body()[i].getTitle(),response.body()[i].getPrice(),response.body()[i].getDescription(),response.body()[i].getDatecreation(),response.body()[i].getDeadline(),response.body()[i].getPlacelat(),response.body()[i].getPlacelng(),response.body()[i].getEtat());
                    myJobs.add(s);
                }
                SharedPreferences.Editor prefsEditor = mPrefs.edit();
                Gson gson = new Gson();
                String json = gson.toJson(myJobs);
                prefsEditor.putString("MyObject", json);
                prefsEditor.commit();

                startActivity(intent);

            }
        }).start();



        //get all job
        new Thread(new Runnable() {
            @Override
            public void run() {
                ArrayList<Services> myJobs = new ArrayList<>();
                Retrofit retrofit=RetrofitClient.getmInstance();
                JsonPlaceHolderApi myapi = retrofit.create(JsonPlaceHolderApi.class);
                Call<Services[]> call = myapi.getalljobs(userid);
                Intent intent = new Intent(getApplicationContext(), JobsTabs.class);

                Response<Services[]> response = null;
                try {
                    response = call.execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                for(int i = 0; i<response.body().length; i++)

                {
                    Services s=new Services(response.body()[i].getId(),response.body()[i].getUid(),response.body()[i].getIdp(),response.body()[i].getTitle(),response.body()[i].getPrice(),response.body()[i].getDescription(),response.body()[i].getDatecreation(),response.body()[i].getDeadline(),response.body()[i].getPlacelat(),response.body()[i].getPlacelng(),response.body()[i].getEtat());
                    myJobs.add(s);
                }

                SharedPreferences.Editor prefsEditor = mPrefs.edit();
                Gson gson = new Gson();
                String json = gson.toJson(myJobs);
                prefsEditor.putString("alljobs", json);
                prefsEditor.commit();

                startActivity(intent);

            }
        }).start();


        // get all users
        new Thread(new Runnable() {
            @Override
            public void run() {
                ArrayList<user> users = new ArrayList<>();
                Retrofit retrofit=RetrofitClient.getmInstance();
                JsonPlaceHolderApi myapi = retrofit.create(JsonPlaceHolderApi.class);
                Call<user[]> call = myapi.showL();
                Intent intent = new Intent(getApplicationContext(), JobsTabs.class);

                Response<user[]> response = null;
                try {
                    response = call.execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                for(int i = 0; i<response.body().length; i++)

                {
                    user user=new user(response.body()[i].getName(),response.body()[i].getEmail(),response.body()[i].getLastname(),response.body()[i].getAdress(),response.body()[i].getUniqueid(),response.body()[i].getCreated(),response.body()[i].getUploaded(),response.body()[i].getPhone());
                    users.add(user);
                }
                SharedPreferences.Editor prefsEditor = mPrefs.edit();
                Gson gson = new Gson();
                String json = gson.toJson(users);
                prefsEditor.putString("allusers", json);
                prefsEditor.commit();

                startActivity(intent);

            }
        }).start();



        //get all request
        new Thread(new Runnable() {
            @Override
            public void run() {
                ArrayList<requests> requests = new ArrayList<>();
                Retrofit retrofit=RetrofitClient.getmInstance();
                JsonPlaceHolderApi myapi = retrofit.create(JsonPlaceHolderApi.class);
                Call<requests[]> call = myapi.getallrequests();
                Intent intent = new Intent(getApplicationContext(), JobsTabs.class);

                Response<requests[]> response = null;
                try {
                    response = call.execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                for(int i = 0; i<response.body().length; i++)

                {
                    requests requests1=new requests(response.body()[i].getId(),response.body()[i].getIdservice(),response.body()[i].getIdemploye(),response.body()[i].getDate());
                    requests.add(requests1);
                }
                SharedPreferences.Editor prefsEditor = mPrefs.edit();
                Gson gson = new Gson();
                String json = gson.toJson(requests);
                prefsEditor.putString("allrequests", json);
                prefsEditor.commit();

                startActivity(intent);

            }
        }).start();

        // get accepted request
        new Thread(new Runnable() {
            @Override
            public void run() {
                ArrayList<requests> requests = new ArrayList<>();
                Retrofit retrofit=RetrofitClient.getmInstance();
                JsonPlaceHolderApi myapi = retrofit.create(JsonPlaceHolderApi.class);
                Call<requests[]> call = myapi.getallacceptedrequests();
                Intent intent = new Intent(getApplicationContext(), JobsTabs.class);

                Response<requests[]> response = null;
                try {
                    response = call.execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                for(int i = 0; i<response.body().length; i++)

                {
                    requests requests1=new requests(response.body()[i].getId(),response.body()[i].getIdservice(),response.body()[i].getIdemploye(),response.body()[i].getDate());
                    requests.add(requests1);
                }

                SharedPreferences.Editor prefsEditor = mPrefs.edit();
                Gson gson = new Gson();
                String json = gson.toJson(requests);
                prefsEditor.putString("allacceptedrequests", json);
                prefsEditor.commit();

                startActivity(intent);

            }
        }).start();



    }}

