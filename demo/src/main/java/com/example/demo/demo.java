package com.example.demo;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class demo {

    private final CloseableHttpClient httpClient = HttpClients.createDefault();

    public static void callAPIs() throws Exception {
        demo obj = new demo();

        try {
            /**
             * TODO: Try GET methods as below by replacing URL in sendGet method
             * TODO: Try POST methods as below by replacing URL in sendPost method
             * */
            obj.sendGet("https://reqres.in/api/users/2");
            obj.sendPost("https://reqres.in/api/users");
        } finally {
            obj.close();
        }
    }

    private void close() throws IOException {
        httpClient.close();
    }

    private void sendGet(String url) throws Exception {

        HttpGet request = new HttpGet(url);
        try (CloseableHttpResponse response = httpClient.execute(request)) {

            // Get HttpResponse Status
            System.out.println(response.getStatusLine().toString());

            HttpEntity entity = response.getEntity();

            if (entity == null) {
                System.out.println("Null returned");
                return;
            }
            // return it as a String
            String result = EntityUtils.toString(entity);
            System.out.println("GET API Result: " + result + "\n");
        }
    }

    private void sendPost(String url) throws Exception {

        HttpPost post = new HttpPost(url);

        /**
         * TODO : Add parameters here
         */
        List<NameValuePair> urlParameters = new ArrayList<>();
        urlParameters.add(new BasicNameValuePair("name", "morpheus"));
        urlParameters.add(new BasicNameValuePair("job", "leader"));


        post.setEntity(new UrlEncodedFormEntity(urlParameters));

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(post)) {

            System.out.println("POST API Result: "+EntityUtils.toString(response.getEntity()));
        }
    }
}
