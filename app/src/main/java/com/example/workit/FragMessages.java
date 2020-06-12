package com.example.workit;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.ramotion.foldingcell.FoldingCell;

import java.lang.reflect.Type;
import java.util.ArrayList;


public class FragMessages extends Fragment {
    View myView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle("Alle Patienten");
        myView = inflater.inflate(R.layout.messages_frag, container, false);

        SharedPreferences mPrefs = getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);

        final ArrayList<Services> myJobs;
        Gson gson = new Gson();
        String json = mPrefs.getString("alljobs", "");
        Type type = new TypeToken<ArrayList< Services >>() {}.getType();
        myJobs = gson.fromJson(json,type);


        final ArrayList<requests> myreq;
        Gson gson1 = new Gson();
        String json1 = mPrefs.getString("allacceptedrequests", "");
        Type type1 = new TypeToken<ArrayList< requests >>() {}.getType();
        myreq = gson1.fromJson(json1,type1);

        String userid = mPrefs.getString("user id", "");


        final ArrayList<user> users;
        Gson gson2 = new Gson();
        String json2 = mPrefs.getString("allusers", "");
        Type type2 = new TypeToken<ArrayList< user >>() {}.getType();
        users = gson2.fromJson(json2,type2);













        // prepare elements to display
        final ArrayList<Services> myTasks =new ArrayList<>();

        System.out.println(myreq.get(0).getIdservice());
        System.out.println(myreq.get(0).getIdemploye());
        System.out.println(myJobs.size());





        for(int i=0;i<myreq.size();i++)
        {
            if(myreq.get(i).getIdemploye().equals(userid))
            {
                for(int j=0;j<myJobs.size();j++)
                {   System.out.println(myJobs.get(j).getUid());
                    if(myreq.get(i).getIdservice().equals(myJobs.get(j).getUid()))
                    {System.out.println("in test");
                        myTasks.add(myJobs.get(j));}
                }

            }


        }
        System.out.println(myTasks);










        // get our list view
        ListView theListView3 = (ListView) myView.findViewById(R.id.mainListView3);



        // create custom adapter that holds elements and their state (we need hold a id's of unfolded elements for reusable elements)
        final FoldingCellListAdapter2 adapter = new FoldingCellListAdapter2(getContext(), myTasks);



        for(int i = 0; i<myTasks.size(); i++) {
            final int finalI = i;
            final ArrayList<Services> finalMyJobs = myTasks;


        myTasks.get(i).setCall1BtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id=finalMyJobs.get(finalI).getIdp();
                for(int j = 0; j<users.size(); j++) {
                    if(users.get(j).getUniqueid().equals(id))
                    {
                        int MY_PERMISSIONS_REQUEST_CALL_PHONE=0;
                        String number = ("tel:"+users.get(j).getPhone());
                        Intent mIntent = new Intent(Intent.ACTION_CALL);
                        mIntent.setData(Uri.parse(number));
                        // Here, thisActivity is the current activity
                        if (ContextCompat.checkSelfPermission(getActivity(),
                                Manifest.permission.CALL_PHONE)
                                != PackageManager.PERMISSION_GRANTED) {

                            ActivityCompat.requestPermissions(getActivity(),
                                    new String[]{Manifest.permission.CALL_PHONE},
                                    MY_PERMISSIONS_REQUEST_CALL_PHONE);

                            // MY_PERMISSIONS_REQUEST_CALL_PHONE is an
                            // app-defined int constant. The callback method gets the
                            // result of the request.
                        } else {
                            //You already have permission
                            try {
                                startActivity(mIntent);
                            } catch(SecurityException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                }

            }
        });



            myTasks.get(i).setShowplace1BtnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    System.out.println("callllll");

                    Toast.makeText(getContext(), "CUSTOM HANDLER FOR  BUTTON"+ finalI, Toast.LENGTH_SHORT).show();
                    System.out.println(finalMyJobs.get(finalI).getPlacelat());
                    System.out.println(finalMyJobs.get(finalI).getPlacelng());
                    //
                    LatLng location;
                    location=new LatLng(finalMyJobs.get(finalI).getPlacelat(),finalMyJobs.get(finalI).getPlacelng());

                    Intent main = new Intent(getContext(),serviceplace.class);
                    main.putExtra("posetion",location);
                    startActivity(main);

                }
            });











        }



        // set elements to adapter
        theListView3.setAdapter(adapter);

        // set on click event listener to list view
        theListView3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                // toggle clicked cell state
                ((FoldingCell) view).toggle(false);
                // register in adapter that state for selected cell is toggled
                adapter.registerToggle(pos);
            }
        });

        return myView;
    }
}
