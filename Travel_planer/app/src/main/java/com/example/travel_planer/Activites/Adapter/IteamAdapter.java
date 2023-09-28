package com.example.travel_planer.Activites.Adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.travel_planer.Activites.CategoryDetails;
import com.example.travel_planer.Activites.Domain.IteamDomain;
import com.example.travel_planer.R;

import java.util.ArrayList;

public class IteamAdapter extends RecyclerView.Adapter<IteamAdapter.ViewHolder> {

    ArrayList<IteamDomain> viewiteams;


    public IteamAdapter(ArrayList<IteamDomain> viewiteams){
        this.viewiteams = viewiteams;
    }
    @NonNull
    @Override
    public IteamAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_category,parent,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull IteamAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.titleTxt.setText(viewiteams.get(position).getTitle());
        holder.price.setText(""+viewiteams.get(position).getPrice());

        int drawableResourceId=holder.itemView.getResources().getIdentifier(viewiteams.get(position).getPic(),
                "drawable",holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.picImg);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), CategoryDetails.class);
                intent.putExtra("object",viewiteams.get(position));
                holder.itemView.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return viewiteams.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        TextView titleTxt,price;

        ImageView picImg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            titleTxt = itemView.findViewById(R.id.name_tour);
            picImg = itemView.findViewById(R.id.img_tour);
            price = itemView.findViewById(R.id.price_tour);
        }
    }
}
