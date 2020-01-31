package com.yasir.assessment.api.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yasir.assessment.service.dto.ArtifactDTO;
import com.yasir.assessment.service.dto.ArtifactSearchDTO;

public class ApiTest {

	@Test
	public void apiShouldReturnTwoArtifacts() {

		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties");
		Properties properties = new Properties();
		String authHeader = null;
		String apiURL = null;

		try {
			properties.load(is);
			apiURL = properties.getProperty("API_URL");
			HttpRequest request = HttpRequest.newBuilder().uri(new URI(apiURL))
					.headers(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON).GET().build();

			HttpResponse<String> response = HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());

			Gson gson = new Gson();
			List<ArtifactDTO> apiResponse = gson.fromJson(response.body(), new TypeToken<ArrayList<ArtifactSearchDTO>>() {
			}.getType());
			
			assertNotNull(apiResponse);
			assertEquals(2, apiResponse.size());

		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

	}

}
