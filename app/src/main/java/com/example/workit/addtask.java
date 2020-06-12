package com.example.workit;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.mapbox.mapboxsdk.geometry.LatLng;

import org.jetbrains.annotations.NotNull;

import java.util.Calendar;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class addtask extends AppCompatActivity  {
    private static LatLng dest ;
    static Button addtask;
    Button addplace;
    DatePicker datelimite;
    static java.sql.Date DATES;
    static TextView title;
    static TextView price;
    static TextView description;
    static String titleS;
    static int priceS;
    static String descriptionS;
    static Double placelat;
    static Double placelng;
    SharedPreferences mPrefs;
    String userid;
     static   Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addtask);
        intent = getIntent();
        mPrefs = getSharedPreferences("pref",MODE_PRIVATE);

       userid= mPrefs.getString("user id","");



        title=findViewById(R.id.title);
        price=findViewById(R.id.price);
        description=findViewById(R.id.description);
        addtask=findViewById(R.id.button2);
        addtask.setEnabled(false);
        addplace=findViewById(R.id.taskplace);

        try {
            String s=(String)intent.getExtras().get("titleS");
            title.setText(s);

        }
        catch (NullPointerException ignored){
        }
        try {
            String s=(String)intent.getExtras().get("priceS");
            price.setText(s);

        }
        catch (NullPointerException ignored){
        }
        try {
            String s=(String)intent.getExtras().get("descriptionS");
            description.setText(s);

        }
        catch (NullPointerException ignored){
        }

        addplace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addplacemap = new Intent(getApplicationContext(),addplacemap.class );
                addplacemap.putExtra("titleS",title.getText().toString());
                addplacemap.putExtra("priceS",price.getText().toString());
                addplacemap.putExtra("descriptionS",description.getText().toString());
                startActivity(addplacemap);
            }
        });
        addtask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             //
                dest=(LatLng) intent.getExtras().get("posetion");

             System.out.println(dest);
             titleS=title.getText().toString();
             priceS=Integer.parseInt(price.getText().toString());
             descriptionS=description.getText().toString();
             //
             placelat=dest.getLatitude();
             placelng=dest.getLongitude();
//
                Retrofit retrofit=RetrofitClient.getmInstance();
                JsonPlaceHolderApi myapi = retrofit.create(JsonPlaceHolderApi.class);
                Call<ResponseBody> call = myapi.addjob(userid,titleS,priceS,descriptionS,DATES,placelng,placelat);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(@NotNull Call<ResponseBody> call, @NotNull Response<ResponseBody> response) {
                        System.out.println("succes add");
                    }

                    @Override
                    public void onFailure(@NotNull Call<ResponseBody> call, @NotNull Throwable t) {
                        System.out.println(t.getMessage());
                    }
                });




                Intent intent2 = new Intent(getApplicationContext(), loading.class);
                startActivity(intent2);


            }
        });



        Button btn = (Button) findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Initialize a new date picker dialog fragment
                DialogFragment dFragment = new DatePickerFragment();

                // Show the date picker dialog fragment
                dFragment.show(getFragmentManager(), "Date Picker");
            }
        });



    }


    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState){
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);





            // DatePickerDialog THEME_DEVICE_DEFAULT_DARK
            DatePickerDialog dpd2 = new DatePickerDialog(getActivity(),
                    AlertDialog.THEME_DEVICE_DEFAULT_LIGHT,this,year,month,day);

            // Return the DatePickerDialog
            return  dpd2;
        }

        public void onDateSet(DatePicker view, int year, int month, int day){
            System.out.println(year);
            System.out.println(month+1);
            System.out.println(day);
            DATES= new java.sql.Date(year,month+1,day);
            titleS=title.getText().toString();
            String pricetest=price.getText().toString();
            descriptionS=description.getText().toString();
            dest=(LatLng) intent.getExtras().get("posetion");

            System.out.println(dest);
            System.out.println(titleS);
            System.out.println(descriptionS);

            if((dest != null) && (titleS != null)  && (descriptionS != null) && (!pricetest.isEmpty())){


                addtask.setEnabled(true);


            }




        }








    }
}
