package com.example.corona;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.leo.simplearcloader.SimpleArcLoader;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    CardView cardViewGraph;
    PieChart pieChart;
    TextView pieTotalCases,pieRecovered,pieTotalDeaths,pieTotalActiveCases;
    SimpleArcLoader simpleArcLoader;
    TextView tvCases,tvRecovred,tvCritical,tvActive,tvTotalCases,tvTotalDeaths,tvTotalRecovered;
    ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        cardViewGraph=(CardView) findViewById(R.id.cardViewGraph);
        pieChart=(PieChart) findViewById(R.id.piechart);
        pieTotalCases=(TextView)findViewById(R.id.totalCases);
        pieRecovered=(TextView)findViewById(R.id.recoveredCases);
        pieTotalDeaths=(TextView)findViewById(R.id.Deaths);
        pieTotalActiveCases=(TextView)findViewById(R.id.activeCases);
        simpleArcLoader=(SimpleArcLoader)findViewById(R.id.loader);
        scrollView=(ScrollView)findViewById(R.id.scrollStatus);
        tvCases=(TextView)findViewById(R.id.tvCases);
        tvRecovred=(TextView)findViewById(R.id.tvRecovered);
        tvCritical=(TextView)findViewById(R.id.tvCritical);
        tvActive=(TextView)findViewById(R.id.tvActive);
        tvTotalCases=(TextView)findViewById(R.id.tvTotoalCases);
        tvTotalDeaths=(TextView)findViewById(R.id.tvTotalDeaths);
        tvTotalRecovered=(TextView)findViewById(R.id.tvTotalRecovered);

        fetchData();

    }

    private void fetchData() {
        simpleArcLoader.start();
        String url="https://corona.lmao.ninja/v2/all";
        StringRequest request=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response.toString());
                    tvCases.setText(jsonObject.getString("cases"));
                    tvRecovred.setText(jsonObject.getString("recovered"));
                    tvCritical.setText(jsonObject.getString("critical"));
                    tvActive.setText(jsonObject.getString("active"));
                    tvTotalDeaths.setText(jsonObject.getString("deaths"));
                    tvTotalCases.setText(jsonObject.getString("tests"));

                    pieChart.addPieSlice(new PieModel("Cases",Integer.parseInt(tvCases.getText().toString()), Color.parseColor("#ffff00")));
                    pieChart.addPieSlice(new PieModel("Recovered",Integer.parseInt(tvRecovred.getText().toString()), Color.parseColor("#00ff00")));
                    pieChart.addPieSlice(new PieModel("Active",Integer.parseInt(tvActive.getText().toString()), Color.parseColor("#0000cc")));
                    pieChart.addPieSlice(new PieModel("Deaths",Integer.parseInt(tvTotalDeaths.getText().toString()), Color.parseColor("#ff0000")));
                    pieChart.startAnimation();

                    simpleArcLoader.stop();
                    simpleArcLoader.setVisibility(View.GONE);
                    scrollView.setVisibility(View.VISIBLE);







                } catch (JSONException e) {
                    e.printStackTrace();
                    simpleArcLoader.start();
                    simpleArcLoader.setVisibility(View.VISIBLE);
                    scrollView.setVisibility(View.GONE);

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                simpleArcLoader.start();
                simpleArcLoader.setVisibility(View.VISIBLE);
                scrollView.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue queue= Volley.newRequestQueue(this);
        queue.add(request);
    }

    public void TrackCountries(View view) {
        Intent intent=new Intent(getApplicationContext(),CountryActivity.class);
        startActivity(intent);
    }
}