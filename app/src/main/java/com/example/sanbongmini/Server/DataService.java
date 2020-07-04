package com.example.sanbongmini.Server;


import com.example.sanbongmini.Model.Token;
import com.example.sanbongmini.Model.User;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface DataService {
    @GET("auth/user")
    Call<User> getUser();

    @GET("list-user")
    Call<List<User>> getUsers();

    @POST("auth/signup")
    @FormUrlEncoded
    Call<User> SignUp(@Field("name") String name,
                      @Field("email") String email,
                      @Field("password") String password,
                      @Field("password_confirmation") String password_confirmation
    );

    @POST("auth/login")
    @FormUrlEncoded
    Call<Token> login(@Field("email") String email,
                      @Field("password") String password);

    @GET("auth/delete-user/{id}")
    Call<User> DeleteUser(@Path("id") int id);

//    @PUT("auth/update-user/{id}")
//    @FormUrlEncoded
//    Call<User> UpdateUser(@Part("id") int id, @Body User user);

    @POST("auth/update-user/{id}")
//    @FormUrlEncoded
    Call<User> updateUser(@Path("id") int id, @Body() User user);

    @GET("auth/logout")
    Call<Token> logoutUser();


}
