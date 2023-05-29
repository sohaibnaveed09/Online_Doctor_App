package com.example.onlinedoctor;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterforFragmentAdd extends RecyclerView.Adapter<AdapterforFragmentAdd.MyViewHolder> {

    Context context;
    ArrayList<Bemari> arrayList;

    public AdapterforFragmentAdd(Context ct,ArrayList<Bemari> arrayList){
        this.context=ct;
        this.arrayList=arrayList;

    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view =inflater.inflate(R.layout.fragment_add_recyelerview,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

       Bemari bemari=arrayList.get(position);
       holder.txt.setText(bemari.txt);
       holder.itemView.setOnClickListener(view -> {
           Intent intent=new Intent(context,List_of_Doctors.class);
           context.startActivity(intent);
       });

    }

    @Override
    public int getItemCount() {
     return arrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txt;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txt=itemView.findViewById(R.id.Google_txt);

        }
    }
}

