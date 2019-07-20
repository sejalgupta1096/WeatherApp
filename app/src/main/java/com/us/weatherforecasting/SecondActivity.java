package com.us.weatherforecasting;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.us.weatherforecasting.Custom.CustomDialogue;
import com.us.weatherforecasting.Custom.onCitySelector;
import com.us.weatherforecasting.Schema.Data;

public class SecondActivity extends AppCompatActivity {

    ImageView image;
    TextView temp,tempunit,city,weather,humidity,pressure;

    String[] cities={"Patna",
            "Agartala",
            "Kohima",
            "Mississippiiii",
            "Chandigarh",
            "Ranchi",
            "Thiruvananthapuram",
            "Mumbai",
            "Bhubaneswar",
            "Chandigarh",
            "Panaji",
            "Gandhinagar",
            "Dispur",
            "Amaravati",
            "Dehradun",
            "Shimla",
            "Bhopal",
            "Jaipur",
            "Aizawl",
            "Srinagar",
            "Kolkata",
            "Raipur",
            "Chennai",
            "Lucknow",
            "Gangtok",
            "Itanagar",
            "Bangalore",
            "Shillong",
            "Imphal"};


    String URL="http://api.openweathermap.org/data/2.5/weather?q=Jaipur,India&APPID=4d4eaa01be3ae2192875f61474fcee82";




    CustomDialogue customDialogue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second2);

        image=findViewById(R.id.image);
        temp=findViewById(R.id.temp);
        tempunit=findViewById(R.id.unit);
        weather=findViewById(R.id.weather1);
        humidity=findViewById(R.id.humidity);
        pressure=findViewById(R.id.pressure);
        city=findViewById(R.id.city);



        customDialogue= new CustomDialogue(this, cities, R.layout.dialog, new onCitySelector() {
            @Override
            public void onSelected(int position) {
                customDialogue.dismiss();
           fechdata(cities[position]);
            }
        });

        city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customDialogue.show();


            }
        });




//        final Gson gson=new GsonBuilder().create();
//
//        StringRequest request=new StringRequest(URL, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                 Toast.makeText(Main2Activity.this,""+ response,Toast.LENGTH_SHORT).show();
//
//                Data d=gson.fromJson(response,Data.class);
//                Toast.makeText(SecondActivity.this,""+d.getCoord().getLat(),Toast.LENGTH_SHORT).show();
//
//                temp.setText(""+(int)(d.getMain().getTemp()-273));
//                city.setText(d.getName());
//
//
//                weather.setText(d.getWeather().get(0).getMain());
//                humidity.setText(""+d.getMain().getHumidity());
//                pressure.setText(""+d.getMain().getPressure()+" p");
//
//                //setting image
//                if(weather.equals("Haze"))
//                {
//                    image.setImageResource(R.drawable.haze);
//                }
//                if(weather.equals("Windy"))
//                {
//                    image.setImageResource(R.drawable.windy);
//                }
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });
//        RequestQueue queue= Volley.newRequestQueue(this);
//        queue.add(request);
    }

    public void fechdata(final String citys){


        String URL="http://api.openweathermap.org/data/2.5/weather?q="+citys+"&appid=770297e3792414ec205e9282b0ca25cb";


        final Gson gson= new GsonBuilder().create();

        StringRequest request=new StringRequest(URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {




                Data d=gson.fromJson(response, Data.class);

                Toast.makeText(SecondActivity.this,
                        ""+d.getCoord().getLat(), Toast.LENGTH_SHORT).show();





                temp.setText(""+(int)(d.getMain().getTemp()-273));

                city.setText(d.getName());

                String we=d.getWeather().get(0).getMain();

                weather.setText(we);

                humidity.setText(""+d.getMain().getHumidity());

                pressure.setText(""+d.getMain().getPressure()+" P");




                //setting images



            }


        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {



                    }
                });

        RequestQueue queue= Volley.newRequestQueue(this);

        queue.add(request);
    }


}
