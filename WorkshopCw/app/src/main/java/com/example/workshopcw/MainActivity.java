package com.example.workshopcw;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    //AVo json array response lai rakhna lai arraylist banaune
    ArrayList<CarModelClass> carModelClasses = new ArrayList<>();

    private RecyclerView rv;
    private  CarsAdapter carsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv = findViewById(R.id.cars_recyclerview);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        rv.setLayoutManager(manager);
        rv.setHasFixedSize(true);
        //error
        rv.setAdapter(carsAdapter);

        getCarResponse();
    }

    private void getCarResponse() {
        //avo retrofit ko instance banam
        Retrofit haudeRetrofit = new Retrofit.Builder().baseUrl("https://navneet7k.github.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //pojo java classes bahera khojnu time xa vane hera vai haru
        //avo interface banaune, to request json data
        RequestInterface requestInterface = haudeRetrofit.create(RequestInterface.class);
        Call<List<CarModelClass>> myCall = requestInterface.getCarJson();

        myCall.enqueue(new Callback<List<CarModelClass>>() {
            @Override
            public void onResponse(Call<List<CarModelClass>> call, Response<List<CarModelClass>> response) {
                carModelClasses = new ArrayList<>(response.body());

                //adpater banayepaaxi garne kaam
                carsAdapter = new CarsAdapter(carModelClasses,MainActivity.this);
                rv.setAdapter(carsAdapter);

                Toast.makeText(MainActivity.this, "Connection Successful", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<CarModelClass>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Connection Failed", Toast.LENGTH_SHORT).show();
            }
        });

    }


}
