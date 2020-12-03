package com.example.splash.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.splash.R;
import com.example.splash.entity.DoctorOrHospital;
import com.example.splash.service.RequestHandlerService;
import com.example.splash.util.OutputParser;

import java.io.IOException;
import java.util.ArrayList;

public class DoctorActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);

        Button dentist = (Button) findViewById(R.id.btndentist);
        dentist.setOnClickListener(this);
        Button cardiologist = (Button) findViewById(R.id.btncardio);
        cardiologist.setOnClickListener(this);
        Button gynecologist = (Button) findViewById(R.id.btngyn);
        gynecologist.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btndentist:

            case R.id.btncardio:

            case R.id.btngyn:

            case R.id.btnneuro:

            case R.id.btnpsy:

            case R.id.btngeneral:
                Intent intent;
                RequestHandlerService handlerService = new RequestHandlerService();
                OutputParser parser = new OutputParser();
                ArrayList<DoctorOrHospital> entities = new ArrayList<DoctorOrHospital>();

                try{
                    String output = handlerService.serveUrlRequest("https://429c9fe8-db96-42f4-95c7-720daad96c2e.mock.pstmn.io/get");
                    entities = parser.getHospitals(output);
                } catch (IOException ex) {
                    System.out.println("Exception occurred while fetching URL " + ex);
                }
                intent = new Intent(DoctorActivity.this, DisplayActivity.class);
                intent.putExtra("displayEntityList", entities);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}