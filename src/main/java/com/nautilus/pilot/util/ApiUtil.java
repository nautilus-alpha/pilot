package com.nautilus.pilot.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

public class ApiUtil {
    private static final HttpClient client = HttpClientBuilder.create().build();
    private static final String URL_RECENT_PRICES = "https://api.upbit.com/v1/candles/minutes/3?market=KRW-BTC&count=200";
    private static HttpGet requestRecentPrice = new HttpGet(URL_RECENT_PRICES);

    static {
        requestRecentPrice.setHeader("Content-Type", "application/json");
    }

    public static JSONArray getRecentPrices() throws Exception {
        HttpResponse response = client.execute(requestRecentPrice);
        HttpEntity entity = response.getEntity();
        JSONParser parser = new JSONParser();
        JSONArray array = (JSONArray) parser.parse(EntityUtils.toString(entity, "UTF-8"));
        return array;
    }
}
