package com.yasir.assessment.client;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.spi.ApplicationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.yasir.assessment.service.dto.ArtifactDTO;
import com.yasir.assessment.service.dto.ArtifactSearchDTO;
import com.yasir.assessment.service.dto.ArtifactStatsDTO;

public class ArtifactoryClient {

	private static String SEARCH_URL;
	private static String STATS_URL;
	private static String authHeader;
	private static Logger LOGGER = LoggerFactory.getLogger(ArtifactoryClient.class);

	static {
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties");
		Properties properties = new Properties();
		try {
			properties.load(is);
			SEARCH_URL = properties.getProperty("SEARCH_URL");
			STATS_URL = properties.getProperty("STATS_URL");
			authHeader = (String) new InitialContext().lookup("java:global/auth");
		} catch (Exception e) {
			LOGGER.error("Message: " + e.getMessage() + " Cause: " + e.getCause());
			throw new ApplicationException(e);
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
			LOGGER.error("Message: " + e.getMessage() + " Cause: " + e.getCause());
			throw new ApplicationException(e);
		} catch (IOException e) {
			LOGGER.error("Message: " + e.getMessage() + " Cause: " + e.getCause());
			throw new ApplicationException(e);
		} catch (InterruptedException e) {
			LOGGER.error("Message: " + e.getMessage() + " Cause: " + e.getCause());
			throw new ApplicationException(e);
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
			LOGGER.error("Message: " + e.getMessage() + " Cause: " + e.getCause());
			throw new ApplicationException(e);
		} catch (IOException e) {
			LOGGER.error("Message: " + e.getMessage() + " Cause: " + e.getCause());
			throw new ApplicationException(e);
		} catch (InterruptedException e) {
			LOGGER.error("Message: " + e.getMessage() + " Cause: " + e.getCause());
			throw new ApplicationException(e);
		}

		return artifactStatsDTO;

	}

}
