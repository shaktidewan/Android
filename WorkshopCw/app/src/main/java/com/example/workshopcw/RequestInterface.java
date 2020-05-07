package com.example.workshopcw;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

interface RequestInterface {
    //now  we make a GET request
    @GET("cars_list.json")
//Car model class ko list banayera function ma declare gareko
    Call<List <CarModelClass>> getCarJson();
}
