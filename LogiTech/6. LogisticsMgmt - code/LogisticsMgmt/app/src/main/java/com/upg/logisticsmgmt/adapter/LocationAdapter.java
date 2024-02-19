package com.upg.logisticsmgmt.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.upg.logisticsmgmt.R;
import com.upg.logisticsmgmt.pojo.Vehicle;

import java.util.ArrayList;

public class LocationAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Vehicle> arrayList;
    private TextView truckNumTxt, locationTxt;
    public LocationAdapter(Context context, ArrayList<Vehicle> arrayList) {
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
        convertView = LayoutInflater.from(context).inflate(R.layout.location_row, parent, false);

        truckNumTxt = convertView.findViewById(R.id.truckNum);
        truckNumTxt.setText(" " + arrayList.get(position).getTruckNumber());

        locationTxt = convertView.findViewById(R.id.loc);
        locationTxt.setText(" " + arrayList.get(position).getLocation());
        return convertView;
    }
}
