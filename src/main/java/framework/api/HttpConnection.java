package framework.api;

import framework.logger.MyLogger;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.nio.charset.StandardCharsets;

public class HttpConnection {

    private static final MyLogger LOGGER = new MyLogger();

    public static HttpResponse getResponse(String link) {
        URI uri = URI.create(link);
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(uri);
        HttpResponse response = null;
        try {
            response = httpClient.execute(request);
        } catch (IOException e) {
            LOGGER.error("IOException", e);
        }
        return response;
    }

    public static HttpResponse postResponse(String link) {
        URI uri = URI.create(link);
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost(uri);
        HttpResponse response = null;
        try {
            response = httpClient.execute(request);
        } catch (IOException e) {
            LOGGER.error("IOException", e);
        }
        return response;
    }

    public static HttpResponse postResponseAuth(String link, String user, String password, String json) {
        URI uri = URI.create(link);
        HttpPost httpPost = new HttpPost(uri);
        String auth = user + ":" + password;
        byte[] encodedAuth = Base64.encodeBase64(
                auth.getBytes(StandardCharsets.ISO_8859_1));
        String authHeader = "Basic " + new String(encodedAuth);
        httpPost.setHeader(HttpHeaders.AUTHORIZATION, authHeader);
        HttpClient client = HttpClientBuilder.create().build();
        StringEntity entity = null;
        try {
            entity = new StringEntity(json);
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("UnsupportedEncodingException ", e);
        }
        httpPost.setEntity(entity);
        httpPost.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        HttpResponse response = null;
        try {
            response = client.execute(httpPost);
        } catch (IOException e) {
            LOGGER.error("IOException", e);
        }
        return response;
    }

    public static HttpResponse postResponseAuth(String link, String user, String password) {
        URI uri = URI.create(link);
        HttpPost httpPost = new HttpPost(uri);
        String auth = user + ":" + password;
        byte[] encodedAuth = Base64.encodeBase64(
                auth.getBytes(StandardCharsets.ISO_8859_1));
        String authHeader = "Basic " + new String(encodedAuth);
        httpPost.setHeader(HttpHeaders.AUTHORIZATION, authHeader);
        HttpClient client = HttpClientBuilder.create().build();
        httpPost.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        HttpResponse response = null;
        try {
            response = client.execute(httpPost);
        } catch (IOException e) {
            LOGGER.error("IOException", e);
        }
        return response;
    }

    public static HttpResponse getResponseAuth(String link, String user, String password) {
        URI uri = URI.create(link);
        HttpGet httpGet = new HttpGet(uri);
        String auth = user + ":" + password;
        byte[] encodedAuth = Base64.encodeBase64(
                auth.getBytes(StandardCharsets.ISO_8859_1));
        String authHeader = "Basic " + new String(encodedAuth);
        httpGet.setHeader(HttpHeaders.AUTHORIZATION, authHeader);
        httpGet.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        HttpClient client = HttpClientBuilder.create().build();
        HttpResponse response = null;
        try {
            response = client.execute(httpGet);
        } catch (IOException e) {
            LOGGER.error("IOException", e);
        }
        return response;
    }

    public static JSONObject getJSONObject(HttpResponse httpResponse) {
        HttpResponse response = httpResponse;
        HttpEntity entity = response.getEntity();
        String stringResponse = null;
        try {
            stringResponse = EntityUtils.toString(entity);
        } catch (IOException e) {
            LOGGER.error("IOException", e);
        }
        LOGGER.info("getStringResponse " + stringResponse);
        JSONParser parser = new JSONParser();
        JSONObject jsonResp = null;
        try {
            jsonResp = (JSONObject) parser.parse(stringResponse);
        } catch (ParseException e) {
            LOGGER.error("ParseException ", e);
        }
        return jsonResp;
    }

    public static JSONArray getJSONArray(HttpResponse httpResponse) {
        HttpResponse response = httpResponse;
        HttpEntity entity = response.getEntity();
        String stringResponse = null;
        try {
            stringResponse = EntityUtils.toString(entity);
        } catch (IOException e) {
            LOGGER.error("IOException", e);
        }
        LOGGER.info("getStringResponse " + stringResponse);
        JSONParser parser = new JSONParser();
        JSONArray jsonArray = null;
        try {
            jsonArray = (JSONArray) parser.parse(stringResponse);
        } catch (ParseException e) {
            LOGGER.error("ParseException", e);
        }
        return jsonArray;
    }

    public static String getStringResponse(String link) {
        HttpResponse response = postResponse(link);
        HttpEntity entity = response.getEntity();
        String stringResponse = null;
        try {
            stringResponse = EntityUtils.toString(entity);
        } catch (IOException e) {
            LOGGER.error("IOException", e);
        }
        LOGGER.info(" getStringResponse = " + stringResponse);
        return stringResponse;
    }
}