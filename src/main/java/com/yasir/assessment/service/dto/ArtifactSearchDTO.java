package com.yasir.assessment.service.dto;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class ArtifactSearchDTO implements Serializable {
	
	private static final long serialVersionUID = 4580368302809706443L;
	
	@SerializedName(value = "results")
	private List<ArtifactDTO> artifacts;
	private RangeDTO range;

	public List<ArtifactDTO> getArtifacts() {
		return artifacts;
	}

	public void setArtifacts(List<ArtifactDTO> artifacts) {
		this.artifacts = artifacts;
	}

	public RangeDTO getRange() {
		return range;
	}

	public void setRange(RangeDTO range) {
		this.range = range;
	}

}
