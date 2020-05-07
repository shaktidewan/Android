package com.example.jsonparsing;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getCarResponse();
    }

    private void getCarResponse() {

        Retrofit haudeRetrofit = new Retrofit.Builder().baseUrl("https://navneet7k.github.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterface requestInterface = haudeRetrofit.create(RequestInterface.class);
        Call<List<CarModelClass>> mycall = requestInterface.getCarJson();

        mycall.enqueue(new Callback<List<CarModelClass>>() {
            @Override
            public void onResponse(Call<List<CarModelClass>> call, Response<List<CarModelClass>> response) {
                Toast.makeText(MainActivity.this, "Connection Successful", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<List<CarModelClass>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Connection unsuccessful", Toast.LENGTH_LONG).show();            }
        });


    }
}
