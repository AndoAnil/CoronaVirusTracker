package com.example.corona;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.util.List;

public class MyAdapter extends ArrayAdapter<CountryHelper> {
    private List<CountryHelper> list;
    private List<CountryHelper> updatedAccordingSearch;
    private Context context;

    public MyAdapter( Context context, List<CountryHelper> list) {
        super(context,R.layout.custom_list,list);
        this.context=context;
        this.list=list;
        this.updatedAccordingSearch=list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list, null, true);
        ImageView flagImage=view.findViewById(R.id.flagImage);
        TextView countryName=view.findViewById(R.id.countrytv);
        countryName.setText(updatedAccordingSearch.get(position).getCountry());
        Glide.with(context).load(updatedAccordingSearch.get(position).getFlag()).into(flagImage);
        return view;
    }

    @Override
    public int getCount() {
        return updatedAccordingSearch.size();
    }

    @Nullable
    @Override
    public CountryHelper getItem(int position) {
        return updatedAccordingSearch.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return super.getFilter();
    }
}
