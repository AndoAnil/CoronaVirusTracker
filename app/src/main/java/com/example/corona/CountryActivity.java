package com.example.corona;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.leo.simplearcloader.SimpleArcLoader;

import org.eazegraph.lib.models.PieModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CountryActivity extends AppCompatActivity {
    EditText search;
    ListView recyclerView;
    SimpleArcLoader simpleArcLoader;
    public static List<CountryHelper> list=new ArrayList<>();
    MyAdapter myAdapter;
    CountryHelper countryHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);
        search=(EditText)findViewById(R.id.search);
        getSupportActionBar().setTitle("Affected Countries");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        recyclerView=(ListView) findViewById(R.id.recyclerView);
        simpleArcLoader=(SimpleArcLoader)findViewById(R.id.simple_arc);
        feetchData();

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
               myAdapter.getFilter().filter(s);
               myAdapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    private void feetchData(){
        simpleArcLoader.start();
        String url="https://corona.lmao.ninja/v2/countries";
        StringRequest request=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray=new JSONArray(response);
                    for(int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        String country =jsonObject.getString("country");
                        String cases=jsonObject.getString("cases");
                        String todayDeaths=jsonObject.getString("todayDeaths");
                        String Deaths=jsonObject.getString("deaths");
                        String todayCases=jsonObject.getString("todayCases");
                        String recovered=jsonObject.getString("recovered");
                        String active=jsonObject.getString("active");
                        String critical=jsonObject.getString("critical");

                        JSONObject jsonObject1=jsonObject.getJSONObject("countryInfo");
                        String flagUrl=jsonObject1.getString("flag");

                        countryHelper=new CountryHelper(flagUrl,country,cases,todayDeaths,Deaths,todayCases,recovered,active,critical);
                        list.add(countryHelper);
                    }
                    myAdapter=new MyAdapter(CountryActivity.this,list);
                    recyclerView.setAdapter(myAdapter);
                    recyclerView.setVisibility(View.VISIBLE);
                    simpleArcLoader.stop();
                    simpleArcLoader.setVisibility(View.GONE);

                } catch (JSONException e) {
                    e.printStackTrace();
                    recyclerView.setVisibility(View.GONE);
                    simpleArcLoader.stop();
                    simpleArcLoader.setVisibility(View.VISIBLE);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                recyclerView.setVisibility(View.GONE);
                simpleArcLoader.stop();
                simpleArcLoader.setVisibility(View.VISIBLE);
                Toast.makeText(CountryActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue queue= Volley.newRequestQueue(this);
        queue.add(request);
    }

}