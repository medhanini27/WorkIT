package com.example.workit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyJobsAdapter extends RecyclerView.Adapter<MyJobsAdapter.ViewHolder> {

    List<MyJobs> MyJobs;
    Context context;

    public MyJobsAdapter(List<MyJobs>MyJobsList)
    {
        this.MyJobs = MyJobsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        context = parent.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        MyJobs country = MyJobs.get(position);


        holder.titlejob.setText(country.getJobtitle());
        holder.desjob.setText(country.getJobtitle());


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"The position is:"+position,Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return MyJobs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView titlejob;
        TextView desjob;
        CardView cardView;

        public ViewHolder(View itemView)
        {
            super(itemView);
            titlejob = (TextView)itemView.findViewById(R.id.job_Place_holder);
            desjob = (TextView)itemView.findViewById(R.id.des_place_holder);

            cardView = (CardView)itemView.findViewById(R.id.cardView);
        }

    }
}
