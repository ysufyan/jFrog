package com.yasir.assessment.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;

import com.yasir.assessment.client.ArtifactoryClient;
import com.yasir.assessment.service.dto.ArtifactDTO;
import com.yasir.assessment.service.dto.ArtifactSearchDTO;
import com.yasir.assessment.service.dto.ArtifactStatsDTO;

@Stateless
public class ArtifactoryServiceImpl implements ArtifactoryService {

	public ArtifactSearchDTO searchAllArtifacts(final String repo) {

		ArtifactoryClient client = new ArtifactoryClient();
		ArtifactSearchDTO searchResult = client.searchAllArtifacts(repo);
		return searchResult;

	}

	public ArtifactStatsDTO findArtifactStats(final String artifactPath) {

		ArtifactoryClient client = new ArtifactoryClient();
		ArtifactStatsDTO artifactStatsDTO = client.findArtifactStats(artifactPath);
		return artifactStatsDTO;
	}

	public Map<String, ArtifactDTO> findTwoMostlyDownloadedArtifacts(final String repo) {

		long maxDownloaded = 0;
		long secondMaxDownloaded = 0;
		Map<String, ArtifactDTO> twoMostlyDownloadedArtifacts = new HashMap<String, ArtifactDTO>();
		ArtifactSearchDTO searchResult = searchAllArtifacts(repo);
		List<ArtifactDTO> artifacts = searchResult.getArtifacts();

		for (ArtifactDTO artifact : artifacts) {
			ArtifactStatsDTO artifactStatsDTO = findArtifactStats("http://35.238.219.235/artifactory/api/storage/" + artifact.getRepo()
					+ "/" + artifact.getPath() + "/" + artifact.getName() + "?stats");
			if (artifactStatsDTO.getDownloadCount() > maxDownloaded) {
				maxDownloaded = artifactStatsDTO.getDownloadCount();
				twoMostlyDownloadedArtifacts.put("first", artifact);
			}

			if (artifactStatsDTO.getDownloadCount() > secondMaxDownloaded && artifactStatsDTO.getDownloadCount() < maxDownloaded) {
				secondMaxDownloaded = artifactStatsDTO.getDownloadCount();
				twoMostlyDownloadedArtifacts.put("second", artifact);
			}
		}

		return twoMostlyDownloadedArtifacts;

	}

}
