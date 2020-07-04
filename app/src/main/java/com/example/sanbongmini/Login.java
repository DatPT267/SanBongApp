package com.example.sanbongmini;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sanbongmini.Model.Token;
import com.example.sanbongmini.Server.APIService;
import com.example.sanbongmini.Server.DataService;
import com.example.sanbongmini.SqlLite.UserReaderSqllite;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
    Button btndangnhap;
    EditText edtEmail, edtPassword;
    DataService dataService;
    TextView txtTrangThai, txtDangKi;
    private UserReaderSqllite userReaderSqllite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AnhXa();

        userReaderSqllite = new UserReaderSqllite(Login.this);

        dataService = APIService.getService(Login.this);


        txtDangKi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Login.this, Register.class);
                startActivity(intent1);
            }
        });

        btndangnhap = findViewById(R.id.btn_dangnhap);
        btndangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmail.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();
                System.out.println(email + " " + password);

                Call<Token> callback = dataService.login(email, password);
                callback.enqueue(new Callback<Token>() {
                    @Override
                    public void onResponse(Call<Token> call, Response<Token> response) {
                        if (response.code() == 200) {
                            System.out.println(response.body().getAccessToken());
                            System.out.println(response.body().getTokenType());
                            Log.e("Loi nay :", response.code() + "");
                            Log.e("Loi nay :", response.message() + "");
                            insertToken(response.body());

                            txtTrangThai.setText("Đăng Nhập Thành Công");

                            Intent intent1 = new Intent(Login.this, Home.class);
                            startActivity(intent1);


                        } else {
                            System.out.println(response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<Token> call, Throwable t) {
                        txtTrangThai.setText("Đăng nhập thất bại");
                        System.out.println(t.getMessage());
                    }
                });


            }
        });
    }

    private void AnhXa() {
        btndangnhap = findViewById(R.id.btn_dangnhap);
        edtEmail = this.findViewById(R.id.edt_tentk);
        edtPassword = this.findViewById(R.id.edt_pass);
        txtTrangThai = this.findViewById(R.id.txtTrangThai);
        txtDangKi = this.findViewById(R.id.txtDangKi);
    }

    public void insertToken(Token token) {

        token.setAccessToken(token.getAccessToken());
        token.setTokenType(token.getTokenType());
        token.setExpiresAt(token.getExpiresAt());
        long result = userReaderSqllite.insertToken(token);
        if (result > 0) {
            Toast.makeText(this, "Dang Nhap Thanh Cong", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Dang Nhap That Bai", Toast.LENGTH_SHORT).show();
        }
    }
}
