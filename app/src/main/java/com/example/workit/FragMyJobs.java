package com.example.workit;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.ramotion.foldingcell.FoldingCell;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class FragMyJobs extends Fragment  {
View myView1;
FoldingCellListAdapter adapter1;


@Nullable
@Override
public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
    getActivity().setTitle("Alle Patienten");
    myView1 = inflater.inflate(R.layout.my_jobs_frag, container, false);
    final SharedPreferences mPrefs = getActivity().getSharedPreferences("pref",Context.MODE_PRIVATE);

    // get our list view
    final ListView theListView1 = (ListView) myView1.findViewById(R.id.mainListView);


     final ArrayList<Services> myJobs;
    Gson gson = new Gson();
    String json = mPrefs.getString("MyObject", "");
    Type type = new TypeToken<ArrayList< Services >>() {}.getType();
    myJobs = gson.fromJson(json,type);


    final ArrayList<requests> myreq;
    Gson gson1 = new Gson();
    String json1 = mPrefs.getString("allrequests", "");
    Type type1 = new TypeToken<ArrayList< requests >>() {}.getType();
    myreq = gson1.fromJson(json1,type1);




    adapter1 = new FoldingCellListAdapter(getContext(), myJobs);


    // set elements to adapter
    theListView1.setAdapter(adapter1);

    // set on click event listener to list view
     theListView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
            // toggle clicked cell state
            ((FoldingCell) view).toggle(false);


            // register in adapter that state for selected cell is toggled
            adapter1.registerToggle(pos);
        }
    });


    adapter1.setplaceBtnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(getContext(), "DEFAULT HANDLER FOR ALL BUTTONS", Toast.LENGTH_SHORT).show();
        }
    });



    for(int i = 0; i<myJobs.size(); i++) {
        final int finalI = i;
        final ArrayList<Services> finalMyJobs = myJobs;


        myJobs.get(i).setDeleteBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getContext(), "delete"+ finalI, Toast.LENGTH_SHORT).show();
                Retrofit retrofit=RetrofitClient.getmInstance();
                JsonPlaceHolderApi myapi = retrofit.create(JsonPlaceHolderApi.class);
                Call<String> call = myapi.deleteservice(myJobs.get(finalI).getUid());
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Intent intent = new Intent(getContext(), loading.class);
                        startActivity(intent);

                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });

            }
        });



        myJobs.get(finalI).setShowreqBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> l=new ArrayList<>();
                Intent intent=new Intent(getContext(),RequestList.class);

                for(int k=0;k<myreq.size();k++)
                {

                    if(myJobs.get(finalI).getUid().equals(myreq.get(k).getIdservice()))
                    {System.out.println(myJobs.get(finalI).getTitle());
                        l.add(myreq.get(k).getIdemploye());
                        System.out.println(myreq.get(k).getIdemploye());}
                    intent.putExtra("id de service",myreq.get(k).getIdservice());}

                    SharedPreferences.Editor prefsEditor = mPrefs.edit();
                    Gson gson = new Gson();
                    String json = gson.toJson(l);
                    prefsEditor.putString("emplyee liste", json);
                    prefsEditor.commit();
                    System.out.println(l);
                    if(l.size()!=0)
                    {startActivity(intent);}














            }
        });





























    }








    Button button7;

    button7 = myView1.findViewById(R.id.button27);
    button7.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

           Intent intent = new Intent(getActivity().getApplication(), addtask.class);
            startActivity(intent);


        }
    });

    return myView1;
}



}