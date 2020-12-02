package com.example.splash.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RequestHandlerService {

    // serve request for a given url
    public String serveUrlRequest(final String url) throws IOException {

        final StringBuffer response = new StringBuffer();
        try {
            Thread thread = new Thread(new Runnable() {

                @Override
                public void run() {
                    try  {
                        URL obj = new URL(url);
                        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
                        connection.setRequestMethod("GET");
                        int responseCode = connection.getResponseCode();
                        System.out.println("GET Response Code :: " + responseCode);


                        if (responseCode == HttpURLConnection.HTTP_OK) { // success
                            System.out.println("Connection to " + url + " succeeded");
                            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                            String inputLine;

                            while ((inputLine = in.readLine()) != null) {
                                //System.out.println(inputLine);
                                response.append(inputLine);
                            }
                            in.close();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
            // Wait until thread has completed the execution.
            thread.join();
        } catch (InterruptedException ex) {
            System.out.println("User interruption detected while waiting to get URL output.");
        }

        return response.toString();
    }


}
