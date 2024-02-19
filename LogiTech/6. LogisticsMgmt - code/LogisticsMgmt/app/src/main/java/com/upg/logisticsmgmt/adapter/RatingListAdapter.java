package com.upg.logisticsmgmt.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.upg.logisticsmgmt.R;
import com.upg.logisticsmgmt.pojo.Driver;
import com.upg.logisticsmgmt.pojo.Rating;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class RatingListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Rating> arrayList;
    private TextView rateBy, rateOn, ratingValue;
    public RatingListAdapter(Context context, ArrayList<Rating> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.rating_list_row, parent, false);
        rateBy = convertView.findViewById(R.id.rateBy);
        rateOn = convertView.findViewById(R.id.rateOn);
        ratingValue = convertView.findViewById(R.id.rateValue);


        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date(arrayList.get(position).getCreatedOn()));

        rateBy.setText("Rating By : " + arrayList.get(position).getCreatedBy());
        rateOn.setText("Rated On : "+date);
        ratingValue.setText("Rating Value : " +arrayList.get(position).getRatingValue());
        return convertView;
    }
}
