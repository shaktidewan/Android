package com.example.workshopcw;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CarsAdapter extends RecyclerView.Adapter<CarsAdapter.ViewHolder> {

    //For cardsview
    private ArrayList<CarModelClass> carModelClasses= new ArrayList<>();
    private Context context;
    //generate constructor by right click
    public CarsAdapter(ArrayList<CarModelClass> carModelClasses, Context context) {
        this.carModelClasses = carModelClasses;
        this.context = context;
    }

    @NonNull
    @Override
    public CarsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //now let's use layout inflater to use list_item.xml file
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new CarsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarsAdapter.ViewHolder holder, int position) {
    //json ko data Ui ma set garne aba
        holder.car_name.setText(carModelClasses.get(position).getName());
        holder.car_desc.setText(carModelClasses.get(position).getDesc());
        Picasso.get().load(carModelClasses.get(position).getImage()).into(holder.car_image);
    }

    @Override
    public int getItemCount() {
        return carModelClasses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //id tane kaam garnu parxa
        private ImageView car_image;
        private TextView car_name;
        private TextView car_desc;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            car_image = itemView.findViewById(R.id.car_image);
            car_name = itemView.findViewById(R.id.car_name);
            car_desc = itemView.findViewById(R.id.car_desc);
        }
    }
}
