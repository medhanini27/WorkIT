package com.example.workit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class RequestList extends AppCompatActivity {
    RecyclerView recyclerView;
    RequestAdapter RequestAdapter;
    ArrayList<RequestItems> requestList  = new ArrayList<RequestItems>();






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_list);
        final SharedPreferences mPrefs = getSharedPreferences("pref", Context.MODE_PRIVATE);

        final List<String> idsemployee;
        Gson gson1 = new Gson();
        String json1 = mPrefs.getString("emplyee liste", "");
        Type type1 = new TypeToken<List<String>>() {}.getType();
        idsemployee = gson1.fromJson(json1,type1);


        final ArrayList<user> users;
        Gson gson = new Gson();
        String json = mPrefs.getString("allusers", "");
        Type type = new TypeToken<ArrayList< user >>() {}.getType();
        users = gson.fromJson(json,type);

        Intent intent=getIntent();
        String idservice=intent.getExtras().getString("id de service");






        for(int i=0;i<idsemployee.size();i++)
        {


            RequestItems requestItems = new RequestItems();
            for(int j=0;j<users.size();j++)
            {
                if(idsemployee.get(i).equals(users.get(j).getUniqueid()))
                {



                    requestItems.setName(users.get(j).getName());
                    requestItems.setLastname(users.get(j).getLastname());
                    requestItems.setId(users.get(j).getUniqueid());
                    requestItems.setIdservice(idservice);
                    requestList.add(requestItems);

                }


            }


        }


        RequestAdapter = new RequestAdapter(requestList);

        recyclerView = (RecyclerView)findViewById(R.id.my_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(RequestAdapter);









    }

}