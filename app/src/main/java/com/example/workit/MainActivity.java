package com.example.workit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.graphics.drawable.AnimationDrawable;
import android.widget.Toast;

import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class MainActivity extends AppCompatActivity {
    Button button4;
    Button button;
    Button login;
    LinearLayout container;
    AnimationDrawable anim;
    JsonPlaceHolderApi myapi;
    EditText password ;
    EditText mail ;
    String mailu;
    String pass;
    SharedPreferences mPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        password=findViewById(R.id.editText2);
        mail=findViewById(R.id.editText);
        mPrefs = getSharedPreferences("pref",MODE_PRIVATE);








        button4 = findViewById(R.id.button4);


        container = (LinearLayout) findViewById(R.id.maingradient);

        anim = (AnimationDrawable) container.getBackground();
        anim.setEnterFadeDuration(6000);
        anim.setExitFadeDuration(2000);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent signup = new Intent(getApplicationContext(), SignUp.class);
                startActivity(signup);
                finish();


            }
        });
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intro = new Intent(getApplicationContext(), IntroActivity.class);
                intro.putExtra("a",1);
                startActivity(intro);
                finish();


            }
        });
        login = findViewById(R.id.button5);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mailu=mail.getText().toString();
                pass=password.getText().toString();
                Retrofit retrofit=RetrofitClient.getmInstance();
                myapi=retrofit.create(JsonPlaceHolderApi.class);
                Call<String> call = myapi.log(mailu,pass);


                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.body().equals("wrong password")){Toast.makeText(MainActivity.this,"wrong password",Toast.LENGTH_LONG).show();}

                        else if (response.body().equals("user not found")){Toast.makeText(MainActivity.this,"wrong email",Toast.LENGTH_LONG).show();}
                        else {
                                    Toast.makeText(MainActivity.this,"login succeded",Toast.LENGTH_LONG).show();
                            SharedPreferences.Editor prefsEditor = mPrefs.edit();
                            prefsEditor.putString("user id", response.body());
                            prefsEditor.commit();
                        Intent intro = new Intent(getApplicationContext(),loading.class);

                        startActivity(intro);


                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        System.out.println(t.getMessage());
                    }
                });

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (anim != null && !anim.isRunning())
            anim.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (anim != null && anim.isRunning())
            anim.stop();
    }
}
