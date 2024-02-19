package com.upg.logisticsmgmt.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.upg.logisticsmgmt.R;
import com.upg.logisticsmgmt.VehicleManagment;
import com.upg.logisticsmgmt.pojo.Vehicle;

import java.util.ArrayList;

public class VehicleAdapter  extends BaseAdapter {

    private Context context;
    private ArrayList<Vehicle> arrayList;
    private TextView truckNumTxt, driverIdTxt, permitTxt;
    public VehicleAdapter(Context context, ArrayList<Vehicle> arrayList) {
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
        convertView = LayoutInflater.from(context).inflate(R.layout.vehicle_row, parent, false);
        truckNumTxt = convertView.findViewById(R.id.truckIdTxt);
        driverIdTxt = convertView.findViewById(R.id.driverIdTxt);
        permitTxt = convertView.findViewById(R.id.permitIdTxt);


        truckNumTxt.setText("Truck Number : " + arrayList.get(position).getTruckNumber());
        driverIdTxt.setText("Driver Id : "+arrayList.get(position).getDriverId());
        permitTxt.setText("Permit Id : " +arrayList.get(position).getPermitId());
        return convertView;
    }
}
