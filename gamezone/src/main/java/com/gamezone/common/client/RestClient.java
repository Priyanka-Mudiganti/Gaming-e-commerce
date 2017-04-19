package com.gamezone.common.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class RestClient {
	private static final String BASE_URL = "http://localhost:8080/gamezone";
	
	public static JsonObject sendGetRequest(String requestPath) throws IOException {
		Gson gson = new Gson();
		URL url = new URL(BASE_URL+requestPath);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		StringBuilder output =  new StringBuilder();
		
		try {
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			String op = "";
			System.out.println("Output from Server .... \n");
			while ((op = br.readLine()) != null) {
				output.append(op);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			conn.disconnect();
		}
		System.out.println(output.toString());
		return gson.fromJson(output.toString(), JsonObject.class);
	}

}
