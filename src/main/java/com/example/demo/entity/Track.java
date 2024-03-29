package com.example.demo.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "track")
public class Track {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Size(max = 10000)
	private String name;

	@Size(max = 10000)
	private String description;

	@JsonManagedReference
	@OneToMany(mappedBy = "track", targetEntity = TrackSegment.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<TrackSegment> trackSegments = new HashSet<>();

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "gps_id", nullable = false)
	private GPS gps;

	public Track() {
		super();
	}

	public Track(Long id, String name, String description, Set<TrackSegment> trackSegments, GPS gps) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.trackSegments = trackSegments;
		this.gps = gps;
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

	public Set<TrackSegment> getTrackSegments() {
		return trackSegments;
	}

	public void setTrackSegments(Set<TrackSegment> trackSegments) {
		this.trackSegments = trackSegments;
	}

	public GPS getGps() {
		return gps;
	}

	public void setGps(GPS gps) {
		this.gps = gps;
	}

}
