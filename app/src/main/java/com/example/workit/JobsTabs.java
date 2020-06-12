package com.example.workit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.MediaController;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

import java.io.Serializable;
import java.util.ArrayList;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageButton;

public class JobsTabs extends AppCompatActivity  {
    private TabLayout tabLayout;
    private AppBarLayout appBarLayout;
    private ViewPager viewpager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobs_tabs);
        tabLayout =(TabLayout) findViewById(R.id.tabLayout_id);
        appBarLayout = (AppBarLayout) findViewById(R.id.appbarid);
        viewpager = (ViewPager) findViewById(R.id.viewpager2);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        Intent intent = getIntent();

        Fragment f=new FragMyJobs();
        Bundle bundle = new Bundle();
        f.setArguments(bundle);
        adapter.AddFragment(new FragProfile(),"Profile");
        adapter.AddFragment(f,"My offers");
        adapter.AddFragment(new FragAllJobs(),"All Jobs");
        adapter.AddFragment(new FragMessages(),"My tasks");
        viewpager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewpager);




    }
}
