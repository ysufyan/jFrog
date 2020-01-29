package com.yasir.assessment.service.dto;

import java.io.Serializable;
import java.util.Date;

public class ArtifactStatsDTO implements Serializable {

	private static final long serialVersionUID = -5175578569241811624L;

	private String uri;
	private Long downloadCount;
	private Long lastDownloaded;
	private Long remoteDownloadCount;
	private Long remoteLastDownloaded;

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public Long getDownloadCount() {
		return downloadCount;
	}

	public void setDownloadCount(Long downloadCount) {
		this.downloadCount = downloadCount;
	}

	public Long getLastDownloaded() {
		return lastDownloaded;
	}

	public void setLastDownloaded(Long lastDownloaded) {
		this.lastDownloaded = lastDownloaded;
	}

	public Long getRemoteDownloadCount() {
		return remoteDownloadCount;
	}

	public void setRemoteDownloadCount(Long remoteDownloadCount) {
		this.remoteDownloadCount = remoteDownloadCount;
	}

	public Long getRemoteLastDownloaded() {
		return remoteLastDownloaded;
	}

	public void setRemoteLastDownloaded(Long remoteLastDownloaded) {
		this.remoteLastDownloaded = remoteLastDownloaded;
	}

}
