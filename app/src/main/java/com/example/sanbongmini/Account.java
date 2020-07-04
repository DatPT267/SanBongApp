package com.example.sanbongmini;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sanbongmini.Model.Token;
import com.example.sanbongmini.Server.APIService;
import com.example.sanbongmini.Server.DataService;
import com.example.sanbongmini.SqlLite.UserReaderSqllite;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Account extends AppCompatActivity {
    RecyclerView recyclerView;
    LinearLayout layoutDN, layoutDX, layoutCSTK;
    TextView txtchinhsua,txtdangxuat, txtDangNhap;
    private UserReaderSqllite userReaderSqllite;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        AnhXa();

        userReaderSqllite = new UserReaderSqllite(Account.this);

        txtchinhsua = findViewById(R.id.chinhsua);
        txtchinhsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Account.this, Chinhsuataikhoan.class);
                startActivity(intent);
            }
        });

        if(getAllToken() >= 1){
            layoutDN.setVisibility(View.GONE);
            txtdangxuat = findViewById(R.id.dangxuat);
            txtdangxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Token token = userReaderSqllite.getToken();
                DataService dataService = APIService.getService(Account.this);
                Call<Token> callback = dataService.logoutUser();
                callback.enqueue(new Callback<Token>() {
                    @Override
                    public void onResponse(Call<Token> call, Response<Token> response) {
                        Log.e("Loi nay :", response.code() + "");
                        Log.e("Loi nay :", response.message() + "");
                        System.out.println("da dang xuat");
                        userReaderSqllite.deleteToken("Bearer");
                        Toast.makeText(Account.this, "Đăng xuất thành công", Toast.LENGTH_SHORT).show();
                        Intent intent1 = new Intent(Account.this, Login.class);
                        startActivity(intent1);
                    }

                    @Override
                    public void onFailure(Call<Token> call, Throwable t) {
                        Toast.makeText(Account.this, "Đăng xuất thất bại", Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });
        }else {
            layoutDX.setVisibility(View.GONE);
            layoutCSTK.setVisibility(View.GONE);
            txtDangNhap.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent1 = new Intent(Account.this, Login.class);
                    startActivity(intent1);

                }
            });
        }




        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.taikhoan);

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
                        startActivity(new Intent(getApplicationContext(),CLB.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.taikhoan:
                        return true;
                }
                return false;
            }
        });
    }

    private void AnhXa() {
        layoutDN = this.findViewById(R.id.layoutDN);
        layoutDX = this.findViewById(R.id.layoutDX);
        layoutCSTK = this.findViewById(R.id.layoutCSTK);
        txtDangNhap = this.findViewById(R.id.txtdangnhap);
    }
    public int getAllToken(){
        List<Token> tokenList = userReaderSqllite.getAllToken();

        return tokenList.size();

    }

}