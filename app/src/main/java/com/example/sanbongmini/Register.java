package com.example.sanbongmini;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.sanbongmini.Model.Token;
import com.example.sanbongmini.Model.User;

import com.example.sanbongmini.Server.APIService;
import com.example.sanbongmini.Server.DataService;
import com.example.sanbongmini.SqlLite.UserReaderSqllite;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity {
    TextView txtdangnhaplai;
    EditText edtName, edtPassword, edtEmail, edtPasswordConfirmation;
    Button btnRegister;
    DataService  dataService;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        AnhXa();
        dataService = APIService.getService(Register.this);




        txtdangnhaplai = findViewById(R.id.dangnhaplai);
        txtdangnhaplai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString();
                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();
                String passwordConfirmation = edtPasswordConfirmation.getText().toString();
                System.out.println(password + " " + passwordConfirmation);
                if (password.equals(passwordConfirmation) ){
                    System.out.println("Mật khẩu trùng khớp");
                    User user = new User(name,email ,password );
                    Call<User> callback = dataService.SignUp(user.getName(), user.getEmail(), user.getPassword(), user.getPassword());
                    callback.enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            if(!response.isSuccessful())
                            {
                                Log.e("Loi nay :", response.code()+"");
                                Log.e("Loi nay :", response.message());
                                return;
                            }
                            Toast.makeText(Register.this, "Đăng kí thành công", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Register.this, Login.class);
                            startActivity(intent);
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {
                            Toast.makeText(Register.this, "Đăng kí thất bại", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
                else Toast.makeText(Register.this, "Mật Khẩu Không Trùng Khớp", Toast.LENGTH_SHORT).show();;

            }
        });


    }

    private void AnhXa() {

        edtEmail = this.findViewById(R.id.dkemail);
        edtName = this.findViewById(R.id.dkten);
        edtPassword = this.findViewById(R.id.dkpass);
        edtPasswordConfirmation = this.findViewById(R.id.dkpass1);
        btnRegister = this.findViewById(R.id.btnDangKi);
    }

}
