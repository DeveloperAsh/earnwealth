package com.pune.earnwealth.web;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by Ashish on 07/11/16.
 */
public class HttpHandler {

    private static final String TAG =  HttpHandler.class.getSimpleName();

    public HttpHandler() {
    }

    public String makeGetServiceCall(String reqUrl) {
        Log.d("LOGS_GET","URL: "+reqUrl);
        String response = null;
        try {
            URL url = new URL(reqUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            // read the response
            InputStream in = new BufferedInputStream(conn.getInputStream());
            response = convertStreamToString(in);
        } catch (MalformedURLException e) {
            Log.e(TAG, "MalformedURLException: " + e.getMessage());
        } catch (ProtocolException e) {
            Log.e(TAG, "ProtocolException: " + e.getMessage());
        } catch (IOException e) {
            Log.e(TAG, "IOException: " + e.getMessage());
        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e.getMessage());
        }
        Log.d("LOGS_GET","Response: "+response);

        return response;
    }

    public static String postServiceCall(String url, String postData, String contentType) throws Exception
    {


        URL obj = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", contentType);
        //Writing POST data
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
        bw.write(postData);
        bw.flush();
        bw.close();
        //Running request
        int respCode = conn.getResponseCode();
        // TODO Handle error codes!
        // Reading response
        BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
        StringBuilder sb = new StringBuilder();
        String output;
        while ((output = br.readLine()) != null)
            sb.append(output);

         return sb.toString();

    }


    private String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    public String upLoadImage(File imagePath) {
        String response = null;
//        HttpClient clientLogin = HttpClientBuilder.create().build();
//        String URLLogin = Constants.MainURL+Constants.UPLOAD_IMAGE;
//        HttpPost postLogin = new HttpPost(URLLogin);
//        try {
//            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
//            //example for setting a HttpMultipartMode
//            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
//            if (imagePath!= null) {
//                FileBody fileBody = new FileBody(imagePath);
//                builder.addPart("file", fileBody);
//            }
//            HttpEntity entity = builder.build();
//            postLogin.setEntity(entity);
//            CommonCode.logWritter("entity: " + entity.toString());
//            HttpResponse responseLogin;
//            responseLogin = clientLogin.execute(postLogin);
//            String resFromServerLogin = EntityUtils.toString(responseLogin.getEntity());
//            return resFromServerLogin;
//        }catch (Exception e){
//            e.printStackTrace();
//        }

        return response;
    }
}
