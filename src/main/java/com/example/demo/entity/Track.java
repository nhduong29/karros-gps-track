package com.example.demo.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "track")
public class Track {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String desc;

	@OneToMany(mappedBy = "track")
	private Set<TrackSegment> trackSegments = new HashSet<>();

	@ManyToOne
	@JoinColumn(name = "gps_id", nullable = false)
	private GPS gps;

	public Track(Long id, String name, String desc, Set<TrackSegment> trackSegments, GPS gps) {
		super();
		this.id = id;
		this.name = name;
		this.desc = desc;
		this.trackSegments = trackSegments;
		this.gps = gps;
	}

	public final Long getId() {
		return id;
	}

	public final void setId(Long id) {
		this.id = id;
	}

	public final String getName() {
		return name;
	}

	public final void setName(String name) {
		this.name = name;
	}

	public final String getDesc() {
		return desc;
	}

	public final void setDesc(String desc) {
		this.desc = desc;
	}

	public final Set<TrackSegment> getTrackSegments() {
		return trackSegments;
	}

	public final void setTrackSegments(Set<TrackSegment> trackSegments) {
		this.trackSegments = trackSegments;
	}

	public final GPS getGps() {
		return gps;
	}

	public final void setGps(GPS gps) {
		this.gps = gps;
	}

}
