package com.example.onlinedoctor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterForAdd extends RecyclerView.Adapter<AdapterForAdd.PhoneViewHold> {

    ArrayList<AttributesForAdd> Array;
    final private ListItemClickListener mOnClickListener;

    public AdapterForAdd(ArrayList<AttributesForAdd> Array, ListItemClickListener listener) {
        this.Array = Array;
        mOnClickListener = listener;
    }

    @NonNull

    @Override
    public PhoneViewHold onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.doctor_lists, parent, false);
        return new PhoneViewHold(view);

    }

    @Override
    public void onBindViewHolder(@NonNull PhoneViewHold holder, int position) {


        AttributesForAdd helper = Array.get(position);
        holder.D_Name.setText(helper.getD_Name());
        holder.D_Exp.setText(helper.getD_Exp());
        holder.D_Qual.setText(helper.getD_Qual());
    }

    @Override
    public int getItemCount() {
        return Array.size();

    }

    public interface ListItemClickListener {
        void onphoneListClick(int clickedItemIndex);
    }

    public class PhoneViewHold extends RecyclerView.ViewHolder implements View.OnClickListener {


        TextView D_Name;
        TextView D_Exp;
        TextView D_Qual;


        public PhoneViewHold(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            //hooks

            D_Name = itemView.findViewById(R.id.D_Name);
            D_Exp = itemView.findViewById(R.id.D_Exp);
            D_Qual = itemView.findViewById(R.id.D_Qual);

        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onphoneListClick(clickedPosition);
        }
    }

}
