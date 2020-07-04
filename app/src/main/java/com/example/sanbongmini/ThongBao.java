package com.example.sanbongmini;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sanbongmini.Adapter.ThongBao_Adapter;
import com.example.sanbongmini.Model.ThongBao_model;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class ThongBao extends AppCompatActivity {
    RecyclerView recyclerView;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongbao);
        recyclerView = findViewById(R.id.acthongbao);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        ArrayList<ThongBao_model> thongbao = new ArrayList<>();
        thongbao.add(new ThongBao_model(R.drawable.ic_home, "abc"));
        thongbao.add(new ThongBao_model(R.drawable.ic_home, "abc"));
        thongbao.add(new ThongBao_model(R.drawable.ic_home, "abc"));
        thongbao.add(new ThongBao_model(R.drawable.ic_home, "abc"));
        thongbao.add(new ThongBao_model(R.drawable.ic_home, "abc"));
        thongbao.add(new ThongBao_model(R.drawable.ic_home, "abc"));
        thongbao.add(new ThongBao_model(R.drawable.ic_home, "abc"));
        recyclerView.setAdapter(new ThongBao_Adapter(thongbao, this));

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.thongbao);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.thongbao:
                        return true;
                    case R.id.sanbong:
                        startActivity(new Intent(getApplicationContext(),SanBong.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.clb:
                        startActivity(new Intent(getApplicationContext(),CLB.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.taikhoan:
                        startActivity(new Intent(getApplicationContext() ,Account.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }
}
