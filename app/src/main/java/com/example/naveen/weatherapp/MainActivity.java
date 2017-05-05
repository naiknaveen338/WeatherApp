package com.example.naveen.weatherapp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    CustomEditText editText;
    CustomButton button;
    String cityname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        editText = (CustomEditText) findViewById(R.id.city);
        button = (CustomButton) findViewById(R.id.search);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (editText.getText().length() > 0) {
                    cityname = editText.getText().toString();
                    getWeather();
                } else {
                    editText.setError("Enter city name");
                }
            }
        });


    }

    Configs configs = new Configs();

    private void getWeather() {
        final ProgressDialog loading = ProgressDialog.show(MainActivity.this, "Please wait...", "", false, false);
        String link = configs.getQuery(cityname);
        Log.w("link", link);

        //Creating a json array request to get the json from our api
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, link, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.w("response of category", "" + response);
                        loading.dismiss();
                        showWeather(response);
                        //showSubCategoryProducts(response);
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                loading.dismiss();
            }
        }) {

        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);
    }

    private void showWeather(JSONObject response) {
        List<WeatherHelper> weatherHelpersss = new ArrayList<WeatherHelper>();
        try {
            JSONObject jsonObject = new JSONObject(String.valueOf(response));
            JSONObject jsonObject1 = jsonObject.getJSONObject("city");
            String name = jsonObject1.getString("name");
            Log.w("name", name);

            JSONArray jsonArray = jsonObject.getJSONArray("list");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                JSONObject jsonObject3 = jsonObject2.getJSONObject("temp");

                String min = jsonObject3.getString("day");
                String day = jsonObject3.getString("min");
                String max = jsonObject3.getString("max");
                String night = jsonObject3.getString("night");
                String eve = jsonObject3.getString("eve");
                String morn = jsonObject3.getString("morn");
                Log.w("morn", min + "," + day + "," + max + "," + night + "," + eve + "," + morn);

                String date_time = jsonObject2.getString("dt");
                Log.w("morn", date_time);

               /* SimpleDateFormat sdf = new SimpleDateFormat("MMMM d, yyyy 'at' h:mm a");
                String date = sdf.format(date_time);
                Log.w("time",date);
*/
                long timestamp = Long.parseLong(date_time);
                Date expiry = new Date(timestamp * 1000);
                String dt = String.valueOf(expiry).substring(0, 11);
                Log.w("time", "" + dt);

               /* TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
                java.util.Date time= new java.util.Date((Long.parseLong(date_time)*1000));
                Log.w("time",""+String.valueOf(time).substring(0,10));*/

                JSONArray jsonArray1 = jsonObject2.getJSONArray("weather");
                JSONObject jsonObject4 = jsonArray1.getJSONObject(0);
                String description = jsonObject4.getString("description");
                Log.w("description", description);


                WeatherHelper weatherHelper = new WeatherHelper(day, min, max, night, eve, morn, description, dt, name);
                weatherHelpersss.add(weatherHelper);

                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(MainActivity.this);
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setAdapter(new WeatherAdapter(MainActivity.this, weatherHelpersss));

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
