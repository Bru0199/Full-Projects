package com.upg.logisticsmgmt.adapter;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.upg.logisticsmgmt.R;
import com.upg.logisticsmgmt.pojo.Booking;
import com.upg.logisticsmgmt.pojo.BookingWithLocation;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class UserTrackingAdapter extends BaseAdapter {

    private Activity context;
    private ArrayList<BookingWithLocation> arrayList;
    private TextView fromTxtId, toTxtId, createdOn,weightTxtId, currentLocation;
    private Button addDriverBtn;

    public UserTrackingAdapter(Activity context, ArrayList<BookingWithLocation> arrayList) {
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
        convertView = LayoutInflater.from(context).inflate(R.layout.user_track_row, parent, false);

        fromTxtId = convertView.findViewById(R.id.usrfromTxtId);
        fromTxtId.setText("From : "+arrayList.get(position).getFrom());

        toTxtId = convertView.findViewById(R.id.usrtoTxtId);
        toTxtId.setText("To : "+arrayList.get(position).getTo());

        createdOn = convertView.findViewById(R.id.usrcreatedOn);
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        String date = simpleDateFormat.format(new Date(arrayList.get(position).getCreatedOn()));
        createdOn.setText("Ordered On : "+date);

        weightTxtId = convertView.findViewById(R.id.usrweightTxtId);
        weightTxtId.setText("Weight : "+arrayList.get(position).getWeight() + " "+arrayList.get(position).getWeightUnit());

        currentLocation = convertView.findViewById(R.id.curLoc);
        currentLocation.setText("Current Location : "+arrayList.get(position).getCurrentLocation());

        return convertView;
    }
}
