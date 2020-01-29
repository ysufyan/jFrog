package com.yasir.assessment.client;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.Gson;
import com.yasir.assessment.service.dto.ArtifactSearchDTO;
import com.yasir.assessment.service.dto.ArtifactStatsDTO;

public class ArtifactoryClient {

	public ArtifactSearchDTO searchAllArtifacts(final String repo) {

		ArtifactSearchDTO searchResult = null;
		try {
			HttpRequest request = HttpRequest.newBuilder().uri(new URI("http://35.238.219.235/artifactory/api/search/aql"))
					.headers("Content-Type", "text/plain;charset=UTF-8").headers("Authorization", "Basic YWRtaW46a2o5U081emdaVQ==")
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

	public ArtifactStatsDTO findArtifactStats(final String artifactPath) {
		ArtifactStatsDTO artifactStatsDTO = null;
		try {
			HttpRequest request = HttpRequest.newBuilder().uri(new URI(artifactPath))
					.headers("Authorization", "Basic YWRtaW46a2o5U081emdaVQ==").GET().build();
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
