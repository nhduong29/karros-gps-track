package com.example.demo.rest.response;

import java.util.Date;

public class GpsTrack {
	private Long id;
	private String name;
	private String description;
	private String uploadBy;
	private Date uploadDate;

	public GpsTrack(Long id, String name, String description, String uploadBy, Date uploadDate) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.uploadBy = uploadBy;
		this.uploadDate = uploadDate;
	}

	public GpsTrack() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

}
