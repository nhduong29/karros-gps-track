package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "track_point")
public class TrackPoint {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String description;

	private Number latitude;

	private Number longitude;

	private Number elevation;

	@ManyToOne
	@JoinColumn(name = "track_segment_id", nullable = false)
	private TrackSegment trackSegment;

	public TrackPoint() {
		super();
	}

	public TrackPoint(Long id, String name, String description, Number latitude, Number longitude, Number elevation,
			TrackSegment trackSegment) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.latitude = latitude;
		this.longitude = longitude;
		this.elevation = elevation;
		this.trackSegment = trackSegment;
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

	public Number getLatitude() {
		return latitude;
	}

	public void setLatitude(Number latitude) {
		this.latitude = latitude;
	}

	public Number getLongitude() {
		return longitude;
	}

	public void setLongitude(Number longitude) {
		this.longitude = longitude;
	}

	public Number getElevation() {
		return elevation;
	}

	public void setElevation(Number elevation) {
		this.elevation = elevation;
	}

	public TrackSegment getTrackSegment() {
		return trackSegment;
	}

	public void setTrackSegment(TrackSegment trackSegment) {
		this.trackSegment = trackSegment;
	}

}
