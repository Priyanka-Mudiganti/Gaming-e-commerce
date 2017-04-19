package com.gamezone.common.client;

import java.io.IOException;
import java.net.MalformedURLException;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class RestClientPost {
	private static final String BASE_URL = "http://localhost:8080/gamezone";

	public static JsonObject sendPostRequest(String requestPath, String jsonPayload) {
		Gson gson = new Gson();
		try {
			CloseableHttpClient client = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost(BASE_URL + requestPath);
			StringEntity entity = new StringEntity(jsonPayload);
			httpPost.setEntity(entity);
			httpPost.setHeader("Accept", "application/json");
			httpPost.setHeader("Content-type", "application/json");
			CloseableHttpResponse response = client.execute(httpPost);
			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
			}
			String json = EntityUtils.toString(response.getEntity(), "UTF-8");
			client.close();
			return gson.fromJson(json.toString(), JsonObject.class);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
