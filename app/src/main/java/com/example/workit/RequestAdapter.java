package com.example.workit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RequestAdapter extends RecyclerView.Adapter<RequestAdapter.ViewHolder> {
    List<RequestItems> RequestItems;
    Context context;

    public RequestAdapter(List<RequestItems>RequestItems)
    {
        this.RequestItems = RequestItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item1,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        context = parent.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final RequestItems requestItems = RequestItems.get(position);

        holder.name.setText(requestItems.getName());
        holder.lastname.setText(requestItems.getLastname());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"The position is:"+position,Toast.LENGTH_SHORT).show();
            }
        });
        holder.accepter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("test button ");
                Retrofit retrofit=RetrofitClient.getmInstance();
                JsonPlaceHolderApi myapi = retrofit.create(JsonPlaceHolderApi.class);
                Call<ResponseBody> call = myapi.acceptrequest(requestItems.getIdservice(),requestItems.getId());
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        System.out.println("employee accepted");
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        System.out.println(t.getMessage());

                    }
                });

                Call<ResponseBody> call1 = myapi.updateservice(requestItems.getIdservice());
                call1.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        System.out.println("stat changed");

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        System.out.println(t.getMessage());

                    }
                });






            }
        });

        Picasso.get().load("http://41.226.11.252:11866/uploads/"+requestItems.getId()+".png").into(holder.img);



    }

    @Override
    public int getItemCount() {
        return RequestItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView name;
        TextView lastname;
        CardView cardView;
        Button accepter;
        ImageView img;

        public ViewHolder(View itemView)
        {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.name);
            lastname = (TextView)itemView.findViewById(R.id.lastname);
            cardView = (CardView)itemView.findViewById(R.id.cardView);
            accepter=itemView.findViewById(R.id.accepteroddre);
            img=itemView.findViewById(R.id.imgjob);
        }


    }
}