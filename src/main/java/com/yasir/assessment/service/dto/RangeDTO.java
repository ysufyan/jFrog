package com.yasir.assessment.service.dto;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class RangeDTO implements Serializable {
	
	private static final long serialVersionUID = -6139474689618736397L;
	
	@SerializedName(value = "start_pos")
	private Long startPos;
	@SerializedName(value = "end_pos")
	private Long endPos;
	private Long  total;
	
	public Long getStartPos() {
		return startPos;
	}
	public void setStartPos(Long startPos) {
		this.startPos = startPos;
	}
	public Long getEndPos() {
		return endPos;
	}
	public void setEndPos(Long endPos) {
		this.endPos = endPos;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	
	
	
}
