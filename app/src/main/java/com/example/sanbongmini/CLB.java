package com.example.sanbongmini;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sanbongmini.Adapter.CLB_Adapter;
import com.example.sanbongmini.Model.CLB_model;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class CLB extends AppCompatActivity {
    RecyclerView recyclerView;
    ImageButton imagethemclb;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clb);
        imagethemclb = findViewById(R.id.themclb);
        imagethemclb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CLB.this, TaoCLB.class);
                startActivity(intent);
            }
        });


        recyclerView = findViewById(R.id.accclb);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        ArrayList<CLB_model> clb = new ArrayList<>();
        clb.add(new CLB_model(R.drawable.chamthuongthu,"CLB Sơn Trà", "Sân rộng, chạy êm"));
        clb.add(new CLB_model(R.drawable.chamthuongthu,"CLB Sơn Trà", "Sân rộng, chạy êm"));
        clb.add(new CLB_model(R.drawable.chamthuongthu,"CLB Sơn Trà", "Sân rộng, chạy êm"));
        clb.add(new CLB_model(R.drawable.chamthuongthu,"CLB Sơn Trà", "Sân rộng, chạy êm"));
        clb.add(new CLB_model(R.drawable.chamthuongthu,"CLB Sơn Trà", "Sân rộng, chạy êm"));
        clb.add(new CLB_model(R.drawable.chamthuongthu,"CLB Sơn Trà", "Sân rộng, chạy êm"));
        recyclerView.setAdapter(new CLB_Adapter(clb, this));

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.clb);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.thongbao:
                        startActivity(new Intent(getApplicationContext(),ThongBao.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.sanbong:
                        startActivity(new Intent(getApplicationContext(),SanBong.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.clb:
                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.taikhoan:
                        startActivity(new Intent(getApplicationContext(),Account.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

    }
}
