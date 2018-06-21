package com.example.tejj.express_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity {

    private String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        //creating the api interface
        Api api = retrofit.create(Api.class);


        //making the call object
        //here we are using the api method that we created inside the api interface

        Call<String> call = api.getUsers();
        Toast.makeText(getApplicationContext(),"Hello people",Toast.LENGTH_SHORT).show();

        //then finallly we are making the call using enqueue()
        //it takes callback interface as an argument
        //and callback is having two methods onRespnose() and onFailure
        //if the request is successfull we will get the correct response and onResponse will be executed
        //if there is some error we will get inside the onFailure() method

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String httpresponse = response.body();

                Log.e(TAG,"YAY success");
                Log.e(TAG,httpresponse);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e(TAG,t.toString());
                Toast.makeText(getApplicationContext(),"Failed to get data",Toast.LENGTH_SHORT).show();
            }
        });




    }
}
