package com.example.demo.rest.response;

import java.util.Date;

public class GpsTrack {
	private Long id;
	private Long fileId;
	private String name;
	private String description;
	private String uploadBy;
	private Date uploadDate;

	public GpsTrack() {
		super();
	}

	public GpsTrack(Long id, Long fileId, String name, String description, String uploadBy, Date uploadDate) {
		super();
		this.id = id;
		this.fileId = fileId;
		this.name = name;
		this.description = description;
		this.uploadBy = uploadBy;
		this.uploadDate = uploadDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getFileId() {
		return fileId;
	}

	public void setFileId(Long fileId) {
		this.fileId = fileId;
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
