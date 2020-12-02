package com.example.splash;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

public class locationPermission extends AppCompatActivity {

    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_permission);
        if(ContextCompat.checkSelfPermission(locationPermission.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            startActivity(new Intent(locationPermission.this, MapActivity.class));
            finish();
            return;
        }
        btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dexter.withActivity(locationPermission.this)
                        .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                        .withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                                     startActivity(new Intent(locationPermission.this, MapActivity.class));
                                     finish();
                            }

                            @Override
                            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                                 if(permissionDeniedResponse.isPermanentlyDenied()){
                                     AlertDialog.Builder builder = new AlertDialog.Builder(locationPermission.this);
                                     builder.setTitle("Permission denied" )
                                             .setMessage("Permission to access device location is permanently denied. you need to go to the settings to allow the permission. ")
                                             .setNegativeButton("cancel", null)
                                             .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                 @Override
                                                 public void onClick(DialogInterface dialog, int which) {
                                                     Intent intent = new Intent();
                                                     intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                                     intent.setData(Uri.fromParts("package", getPackageName(), null));
                                                 }
                                             })
                                             .show();
                                 }else{
                                     Toast.makeText(locationPermission.this, "Permission Denied", Toast.LENGTH_SHORT).show();
                                 }
                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {

                                permissionToken.continuePermissionRequest();
                            }
                        })
                        .check();

            }
        });
    }
}