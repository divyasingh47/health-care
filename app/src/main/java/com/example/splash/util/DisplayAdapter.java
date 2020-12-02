package com.example.splash.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.splash.R;
import com.example.splash.entity.DoctorOrHospital;

import java.util.ArrayList;

public class DisplayAdapter extends ArrayAdapter<DoctorOrHospital> {

    public DisplayAdapter(Context context, ArrayList<DoctorOrHospital> entities) {
        super(context, 0, entities);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DoctorOrHospital doctorOrHospital = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        // Lookup view for data population
        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView address = (TextView) convertView.findViewById(R.id.address);
        TextView contact = (TextView) convertView.findViewById(R.id.contact);
        TextView rating = (TextView) convertView.findViewById(R.id.rating);
        // Populate the data into the template view using the data object
        name.setText(doctorOrHospital.name);
        address.setText("Address: " + doctorOrHospital.address);
        contact.setText("Contact: " + doctorOrHospital.contact);
        rating.setText("Rating: " + String.valueOf(doctorOrHospital.rating));
        // Return the completed view to render on screen
        return convertView;
    }
}