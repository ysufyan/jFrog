package com.yasir.assessment.client;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Properties;

import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.yasir.assessment.service.dto.ArtifactDTO;
import com.yasir.assessment.service.dto.ArtifactSearchDTO;
import com.yasir.assessment.service.dto.ArtifactStatsDTO;

@Stateless
public class ArtifactoryClient {

	static String SEARCH_URL;
	static String STATS_URL;
	private static String authHeader;

	static {
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties");
		Properties properties = new Properties();
		try {
			properties.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		SEARCH_URL = properties.getProperty("SEARCH_URL");
		STATS_URL = properties.getProperty("STATS_URL");

		try {
			authHeader = (String) new InitialContext().lookup("java:global/auth");
		} catch (NamingException e) {
			e.printStackTrace();
		}

	}

	public ArtifactSearchDTO searchAllArtifacts(final String repo) {

		ArtifactSearchDTO searchResult = null;
		try {
			HttpRequest request = HttpRequest.newBuilder().uri(new URI(SEARCH_URL)).headers(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN)
					.headers(HttpHeaders.AUTHORIZATION, authHeader).headers(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
					.POST(HttpRequest.BodyPublishers.ofString("items.find({\"repo\":{\"$eq\":\"jcenter-cache\"}})")).build();

			HttpResponse<String> response = HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());

			Gson gson = new Gson();
			searchResult = gson.fromJson(response.body(), ArtifactSearchDTO.class);

		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return searchResult;

	}

	public ArtifactStatsDTO findArtifactStats(final ArtifactDTO artifact) {
		ArtifactStatsDTO artifactStatsDTO = null;

		String artifactStatPath = STATS_URL + artifact.getRepo() + "/" + artifact.getPath() + "/" + artifact.getName() + "?stats";
		try {
			HttpRequest request = HttpRequest.newBuilder().uri(new URI(artifactStatPath)).headers(HttpHeaders.AUTHORIZATION, authHeader)
					.headers(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON).GET().build();
			HttpResponse<String> response = HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());

			Gson gson = new Gson();
			artifactStatsDTO = gson.fromJson(response.body(), ArtifactStatsDTO.class);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return artifactStatsDTO;
	}

}
