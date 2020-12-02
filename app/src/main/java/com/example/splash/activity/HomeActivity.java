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

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button hospital = (Button) findViewById(R.id.btnhospital);
        hospital.setOnClickListener(this);
        Button doctor = (Button) findViewById(R.id.btndoctor);
        doctor.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        Intent intent;
        switch (view.getId()) {
            case R.id.btndoctor:
                intent = new Intent(HomeActivity.this, DoctorActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.btnhospital:
                RequestHandlerService handlerService = new RequestHandlerService();
                OutputParser parser = new OutputParser();
                ArrayList<DoctorOrHospital> entities = new ArrayList<DoctorOrHospital>();
                try{
                    String output = handlerService.serveUrlRequest("https://f9863fa6-c95f-41d6-ba63-579cd6bc881b.mock.pstmn.io/get");
                    entities = parser.getHospitals(output);
                } catch (IOException ex) {
                    System.out.println("Exception occurred while fetching URL " + ex);
                }

                intent = new Intent(HomeActivity.this, DisplayActivity.class);
                intent.putExtra("displayEntityList", entities);
                startActivity(intent);
                finish();
                break;
            default:
                break;
        }
    }
}
