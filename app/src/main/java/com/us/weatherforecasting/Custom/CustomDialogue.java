package com.us.weatherforecasting.Custom;


import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.us.weatherforecasting.R;

public class CustomDialogue extends Dialog {


    Context context;

    String[] cities;
    RecyclerView rec;
   onCitySelector listenerd;
    public  CustomDialogue(@NonNull Context context,String[] c,int LayoutResource,onCitySelector d)
    {
        super(context);
        this.context=context;
        this.cities=c;
        this.listenerd=d;
        this.setContentView(LayoutResource);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        rec=this.findViewById(R.id.rc);
        rec.setLayoutManager( new LinearLayoutManager(context));
        rec.setAdapter(new Adapter(context,cities,listenerd));
    }
}

class Adapter extends RecyclerView.Adapter<Adapter.Holder>{

Context context;
String[] cities;
onCitySelector onCitySelector;

    public Adapter(Context context, String[] cities,onCitySelector listenerd) {
        this.context = context;
        this.cities = cities;
        this.onCitySelector=listenerd;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.card,parent,false);

        return  new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        final int replace=position;

        holder.citytext.setText(cities[position]);
        holder.note.setText(String.valueOf(cities[position].toCharArray()[0]));
        holder.citytext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onCitySelector.onSelected(replace);
            }
        });

    }

    @Override
    public int getItemCount() {
        return cities.length;
    }

    class Holder extends  RecyclerView.ViewHolder
   {
       TextView note,citytext;

       public Holder(@NonNull View itemView) {



           super(itemView);

           note=itemView.findViewById(R.id.note);
           citytext=itemView.findViewById(R.id.citytext);
       }
   }
}