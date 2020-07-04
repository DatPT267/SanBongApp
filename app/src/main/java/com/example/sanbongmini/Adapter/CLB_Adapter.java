package com.example.sanbongmini.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sanbongmini.Model.CLB_model;
import com.example.sanbongmini.R;

import java.util.ArrayList;

public class CLB_Adapter extends RecyclerView.Adapter<CLB_Adapter.ViewHolder> {
    private ArrayList<CLB_model> clb;
    Context context;

    public CLB_Adapter(ArrayList<CLB_model> clb, Context context) {
        this.clb = clb;
        this.context = context;
    }

    @NonNull
    @Override
    public CLB_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.item_clb, parent, false);
        return new CLB_Adapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CLB_Adapter.ViewHolder holder, int position) {
        holder.hinhclb.setImageResource(clb.get(position).getHinhclb());
        holder.mota1clb.setText(clb.get(position).getMota1clb());
        holder.mota2clb.setText(clb.get(position).getMota2clb());
    }

    @Override
    public int getItemCount() {
        return clb.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView hinhclb;
        TextView mota1clb, mota2clb;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            hinhclb = itemView.findViewById(R.id.hinhclb);
            mota1clb = itemView.findViewById(R.id.motaclb1);
            mota2clb = itemView.findViewById(R.id.motaclb2);
        }
    }
}
