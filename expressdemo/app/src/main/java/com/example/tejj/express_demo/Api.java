package com.example.tejj.express_demo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    String BASE_URL = "https://express-demo-jj.herokuapp.com/";

    @GET("/")
    Call<List<User>> getUsers();
}
