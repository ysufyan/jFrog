package com.yasir.assessment.service.dto;

import java.io.Serializable;
import java.util.Date;

import com.google.gson.annotations.SerializedName;

public class ArtifactDTO implements Serializable {

	private static final long serialVersionUID = 7415471665458303125L;
	
	private String repo;
	private String path;
	private String name;
	private String type;
	private Long size;
	private Date created;
	@SerializedName(value = "created_by")
	private String createdBy;
	private Date modified;
	@SerializedName(value = "modified_by")
	private String modifiedBy;
	private String updated;

	public String getRepo() {
		return repo;
	}

	public void setRepo(String repo) {
		this.repo = repo;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public String getUpdated() {
		return updated;
	}

	public void setUpdated(String updated) {
		this.updated = updated;
	}

}
