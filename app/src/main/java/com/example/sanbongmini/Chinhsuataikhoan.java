package com.example.sanbongmini;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sanbongmini.Model.User;
import com.example.sanbongmini.Server.APIService;
import com.example.sanbongmini.Server.DataService;
import com.example.sanbongmini.SqlLite.UserReaderSqllite;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Chinhsuataikhoan extends AppCompatActivity {
    EditText edtName, edtEmail, edtPassword, edtPasswordConfirmation;
    Button btnUpdate;
    private UserReaderSqllite userReaderSqllite;
    int id;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chinhsua);
        AnhXa();
        userReaderSqllite = new UserReaderSqllite(Chinhsuataikhoan.this);
        DataService dataService = APIService.getService(Chinhsuataikhoan.this);
        Call<User> callback = dataService.getUser();
        callback.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                edtName.setText(response.body().getName().toString());
                edtEmail.setText(response.body().getEmail().toString());
                id = response.body().getId();

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = edtName.getText().toString();
                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();
                if(password.equals(edtPasswordConfirmation.getText().toString())){
                    User user1 = new User(name, email, password);
                    DataService dataService = APIService.getService(Chinhsuataikhoan.this);
                    Call<User> callback =dataService.updateUser(id, user1);
                    callback.enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            if(!response.isSuccessful())
                            {
                                Log.e("Loi nay :", response.message());
                                Log.e("Loi nay", String.valueOf(response.code()));

                                return;
                            }
                            Toast.makeText(Chinhsuataikhoan.this, "Chinh sua tai khoan thanh cong", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {

                        }
                    });
                }




            }
        });


    }

    private void AnhXa() {
        edtName = this.findViewById(R.id.edt_tentk);
        edtEmail = this.findViewById(R.id.edt_email);
        edtPassword = this.findViewById(R.id.edt_pass);
        edtPasswordConfirmation = this.findViewById(R.id.edt_password);
        btnUpdate = this.findViewById(R.id.btn_chinhsuatk);
    }
}
