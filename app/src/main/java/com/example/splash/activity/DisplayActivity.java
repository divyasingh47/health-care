package com.example.splash.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.example.splash.R;
import com.example.splash.entity.DoctorOrHospital;
import com.example.splash.util.DisplayAdapter;

import java.util.ArrayList;

public class DisplayActivity extends AppCompatActivity {

    ArrayList<DoctorOrHospital> displayList;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        Intent intent = getIntent();
        displayList = (ArrayList<DoctorOrHospital>) intent.getSerializableExtra("displayEntityList");

        DisplayAdapter adapter = new DisplayAdapter(this, displayList);
        listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(adapter);
    }

}