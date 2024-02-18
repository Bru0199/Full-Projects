package com.upg.logisticsmgmt.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.upg.logisticsmgmt.R;
import com.upg.logisticsmgmt.pojo.Driver;
import com.upg.logisticsmgmt.pojo.Vehicle;

import java.util.ArrayList;

public class DriverListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Driver> arrayList;
    private TextView driverId, driverName, driverPhone;
    public DriverListAdapter(Context context, ArrayList<Driver> arrayList) {
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
        convertView = LayoutInflater.from(context).inflate(R.layout.driver_list_row, parent, false);
        driverId = convertView.findViewById(R.id.driverIdText);
        driverName = convertView.findViewById(R.id.driverName);
        driverPhone = convertView.findViewById(R.id.driverPhoneNumber);


        driverId.setText("Driver Id : " + arrayList.get(position).getDriverId());
        driverName.setText("Driver Name : "+arrayList.get(position).getDriverName());
        driverPhone.setText("Phone Number : " +arrayList.get(position).getDriverPhoneNum());
        return convertView;
    }
}
