package com.yasir.assessment.endpoint;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yasir.assessment.service.ArtifactoryService;
import com.yasir.assessment.service.dto.ArtifactDTO;

@Path("/search")
@Produces(MediaType.APPLICATION_JSON)
public class ArtifactoryEndpoint {

	private static Logger LOGGER = LoggerFactory.getLogger(ArtifactoryEndpoint.class);
	@EJB
	private ArtifactoryService artifactoryService;

	@GET
	@Path("/two-mostly-downloaded")
	public List<ArtifactDTO> findTwoMostlyDownloadedArtifacts(@QueryParam("repo") final String repo) {
		Map<String, ArtifactDTO> twoMostlyDownloadedArtifacts = artifactoryService
				.findTwoMostlyDownloadedArtifacts(repo);
		List<ArtifactDTO> returnValue = new ArrayList<ArtifactDTO>();
		returnValue.add(twoMostlyDownloadedArtifacts.get("first"));
		returnValue.add(twoMostlyDownloadedArtifacts.get("second"));
		return returnValue;
	}

}
