package com.example.demo.rest.response;

import java.util.Date;

public class GpsTrack {
	private String name;

	private String uploadBy;

	private Date uploadDate;

	private String description;

	public GpsTrack(String name, String uploadBy, Date uploadDate, String description) {
		super();
		this.name = name;
		this.uploadBy = uploadBy;
		this.uploadDate = uploadDate;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUploadBy() {
		return uploadBy;
	}

	public void setUploadBy(String uploadBy) {
		this.uploadBy = uploadBy;
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
