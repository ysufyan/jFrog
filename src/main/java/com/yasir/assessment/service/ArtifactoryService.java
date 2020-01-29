package com.yasir.assessment.service;

import java.util.Map;

import com.yasir.assessment.service.dto.ArtifactDTO;
import com.yasir.assessment.service.dto.ArtifactSearchDTO;
import com.yasir.assessment.service.dto.ArtifactStatsDTO;

public interface ArtifactoryService {

	public ArtifactSearchDTO searchAllArtifacts(final String repo);

	public ArtifactStatsDTO findArtifactStats(final String artifactPath);

	public Map<String, ArtifactDTO> findTwoMostlyDownloadedArtifacts(final String repo);

}
