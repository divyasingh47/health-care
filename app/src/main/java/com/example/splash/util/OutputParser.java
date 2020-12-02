package com.example.splash.util;

import com.example.splash.entity.DoctorOrHospital;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class OutputParser {

    // Parse list of top 10 hospitals
    public ArrayList<DoctorOrHospital> getHospitals (String jsonStr) {
        ArrayList<DoctorOrHospital> entities = new ArrayList<DoctorOrHospital>();
        if (jsonStr != null) {
            try {
                JSONObject jsonObj = new JSONObject(jsonStr);

                // Getting JSON Array node
                JSONArray contacts = jsonObj.getJSONArray("contacts");

                // looping through All Contacts
                for (int i = 0; i < contacts.length(); i++) {
                    JSONObject c = contacts.getJSONObject(i);

                    String name = c.getString("name");
                    String address = c.getString("address");
                    address += ", " + c.getString("location");
                    String contact = c.getString("contact");
                    int rating = c.getInt("rating") % 10;

                    entities.add(new DoctorOrHospital(name, address, contact, rating));
                }
            } catch ( final JSONException e) {
                System.out.println("Json parsing error: " + e.getMessage());
            }
        }
        return entities;
    }
}
