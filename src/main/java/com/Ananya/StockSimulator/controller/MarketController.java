package com.Ananya.StockSimulator.controller;

import java.io.IOException;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class MarketController {

    private static final String API_URL = "https://www.alphavantage.co/query";
    private static final String API_KEY = "SQGJ3TRRT89A8SOL"; // Replace with your API key

    public double fetchStockPrice(String ticker) {
        String url = API_URL + "?function=TIME_SERIES_INTRADAY&symbol=" + ticker + "&interval=5min&apikey=" + API_KEY;
    
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(url);
            CloseableHttpResponse response = httpClient.execute(request);
            String jsonResponse = EntityUtils.toString(response.getEntity());
    
            JSONObject jsonObject = new JSONObject(jsonResponse);
            JSONObject timeSeries = jsonObject.getJSONObject("Time Series (5min)");
            String latestTime = timeSeries.keys().next();
            JSONObject latestData = timeSeries.getJSONObject(latestTime);
    
            return latestData.getDouble("4. close");
    
        } catch (IOException e) {
            e.printStackTrace();
            return 0.0;
        }
    }  
}

