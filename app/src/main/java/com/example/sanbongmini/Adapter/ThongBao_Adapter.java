package com.example.sanbongmini.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sanbongmini.Model.ThongBao_model;
import com.example.sanbongmini.R;

import java.util.ArrayList;

public class ThongBao_Adapter extends RecyclerView.Adapter<ThongBao_Adapter.ViewHolder> {
    private ArrayList<ThongBao_model> thongbao;
    Context context;

    public ThongBao_Adapter(ArrayList<ThongBao_model> thongbao, Context context) {
        this.thongbao = thongbao;
        this.context = context;
    }

    @NonNull
    @Override
    public ThongBao_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.item_thongbao, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ThongBao_Adapter.ViewHolder holder, int position) {
        holder.Hinh.setImageResource(thongbao.get(position).getHinh());
        holder.Mota.setText(thongbao.get(position).getMota());
    }

    @Override
    public int getItemCount() {
        return thongbao.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView Hinh;
        TextView Mota;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Hinh = itemView.findViewById(R.id.hinh);
            Mota = itemView.findViewById(R.id.mota);
        }
    }
}



