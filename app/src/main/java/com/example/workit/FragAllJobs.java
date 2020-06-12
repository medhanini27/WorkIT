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

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static android.content.Context.MODE_PRIVATE;

public class FragAllJobs extends Fragment {
    View myView3;
    String userid;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myView3 = inflater.inflate(R.layout.all_jobs_frag,container,false);

        // get our list view
        ListView theListView3 = (ListView) myView3.findViewById(R.id.mainListView2);

        SharedPreferences mPrefs = getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
        userid= mPrefs.getString("user id","");

        final ArrayList<Services> myJobs;
        Gson gson = new Gson();
        String json = mPrefs.getString("alljobs", "");
        Type type = new TypeToken<ArrayList< Services >>() {}.getType();
        myJobs = gson.fromJson(json,type);

        final ArrayList<user> users;
        Gson gson1 = new Gson();
        String json1 = mPrefs.getString("allusers", "");
        Type type1 = new TypeToken<ArrayList< user >>() {}.getType();
        users = gson1.fromJson(json1,type1);



        // prepare elements to display



        // create custom adapter that holds elements and their state (we need hold a id's of unfolded elements for reusable elements)
        final FoldingCellListAdapter1 adapter = new FoldingCellListAdapter1(getContext(), myJobs);


        // set elements to adapter
        theListView3.setAdapter(adapter);

        // set on click event listener to list view
        theListView3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                // toggle clicked cell state
                Toast.makeText(getContext(), "test"+pos, Toast.LENGTH_LONG).show();

                ((FoldingCell) view).toggle(false);

                // register in adapter that state for selected cell is toggled
                adapter.registerToggle(pos);
            }
        });


        for(int i = 0; i<myJobs.size(); i++) {
            final int finalI = i;
            final ArrayList<Services> finalMyJobs = myJobs;


            myJobs.get(i).setShowplaceBtnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

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

            myJobs.get(i).setCallBtnClickListener(new View.OnClickListener() {
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



            myJobs.get(i).setRequestBtnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("in the button");
                    Retrofit retrofit=RetrofitClient.getmInstance();
                    JsonPlaceHolderApi myapi = retrofit.create(JsonPlaceHolderApi.class);
                    Call<ResponseBody> call = myapi.setrequest(myJobs.get(finalI).getUid(),userid);
                    call.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                            Toast.makeText(getContext(), "Request for service"+myJobs.get(finalI).getTitle()+"was registered", Toast.LENGTH_LONG).show();
                            System.out.println("success added job");
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            System.out.println(t.getMessage());
                        }
                    });




                }
            });












        }










        return myView3;

    }


}
