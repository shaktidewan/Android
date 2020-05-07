package com.example.jsonparsing;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

interface RequestInterface {

    @GET("cars_list.json")

    Call<List<CarModelClass>> getCarJson();
}
