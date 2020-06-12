package com.example.workit;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Type;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.content.Context.MODE_PRIVATE;


public class FragProfile extends Fragment {
    View view;
    public FragProfile() {
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.profile_frag,container,false);
        final Intent intent=new Intent(getContext(),modifierprofile.class);
        SharedPreferences mPrefs = getActivity().getSharedPreferences("pref",MODE_PRIVATE);
        ArrayList<user> users;
        Gson gson = new Gson();
        String json = mPrefs.getString("allusers", "");
        Type type = new TypeToken<ArrayList< user >>() {}.getType();
        users = gson.fromJson(json,type);


        String userid = mPrefs.getString("user id", "");

        TextView email=view.findViewById(R.id.email);
        TextView name=view.findViewById(R.id.name);
        TextView adress=view.findViewById(R.id.adress);
        TextView number=view.findViewById(R.id.mobileNumber);
        CircleImageView profileimg=view.findViewById(R.id.profile);
        for(int i=0;i<users.size();i++)
        {

            if (users.get(i).getUniqueid().equals(userid))
            {
                email.setText(users.get(i).getEmail());
                name.setText(users.get(i).getName()+" "+users.get(i).getLastname());
                adress.setText(users.get(i).getAdress());
                number.setText(users.get(i).getPhone());
                intent.putExtra("email",users.get(i).getName());
                intent.putExtra("name",users.get(i).getName());
                intent.putExtra("lastname",users.get(i).getLastname());
                intent.putExtra("adress",users.get(i).getAdress());
                intent.putExtra("number",users.get(i).getPhone());

                Picasso.get().load("http://41.226.11.252:11866/uploads/"+userid+".png").into(profileimg);
                break;

            }

        }
        Button test=view.findViewById(R.id.editprofile);
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(intent);
            }
        });







        return view;
    }

}
