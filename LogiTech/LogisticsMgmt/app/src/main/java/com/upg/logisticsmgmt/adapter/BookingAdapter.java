package com.upg.logisticsmgmt.adapter;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.upg.logisticsmgmt.AddDriverActivity;
import com.upg.logisticsmgmt.BookingActivity;
import com.upg.logisticsmgmt.MainActivity;
import com.upg.logisticsmgmt.R;
import com.upg.logisticsmgmt.pojo.Booking;
import com.upg.logisticsmgmt.pojo.Vehicle;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class BookingAdapter extends BaseAdapter {

    private Activity context;
    private ArrayList<Booking> arrayList;
    private TextView fromTxtId, toTxtId, createdOn,weightTxtId;
    private Button addDriverBtn;

    public BookingAdapter(Activity context, ArrayList<Booking> arrayList) {
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
        convertView = LayoutInflater.from(context).inflate(R.layout.booking_row, parent, false);

        addDriverBtn = convertView.findViewById(R.id.addDriverBtn);
        addDriverBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.booking = arrayList.get(position);
                Intent intent = new Intent(context, AddDriverActivity.class);
                context.startActivity(intent);
                context.finish();
            }
        });

        fromTxtId = convertView.findViewById(R.id.adminfromTxtId);
        fromTxtId.setText("From : "+arrayList.get(position).getFrom());

        toTxtId = convertView.findViewById(R.id.admintoTxtId);
        toTxtId.setText("To : "+arrayList.get(position).getTo());

        createdOn = convertView.findViewById(R.id.admincreatedOn);
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        String date = simpleDateFormat.format(new Date(arrayList.get(position).getCreatedOn()));
        createdOn.setText("Ordered On : "+date);

        weightTxtId = convertView.findViewById(R.id.adminweightTxtId);
        weightTxtId.setText("Weight : "+arrayList.get(position).getWeight() + " "+arrayList.get(position).getWeightUnit());
        return convertView;
    }
}
