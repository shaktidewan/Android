package com.example.volleyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textView = findViewById(R.id.text);
        //First step is to create Request Queue
        RequestQueue queue = Volley.newRequestQueue(this);
        String url="http://dummy.restapiexample.com/api/v1/employees";

        //Second step string request: GET,PUT
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        textView.setText("Response:"+response.substring(0,400));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                    textView.setText("This endpoint doesnot exist");
            }
        }
        );

        //request object added to queue
        queue.add(stringRequest);

    }
}
