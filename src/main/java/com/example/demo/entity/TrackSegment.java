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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "track_segment")
public class TrackSegment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "track_id", nullable = false)
	private Track track;

	@JsonManagedReference
	@OneToMany(mappedBy = "trackSegment", targetEntity = TrackPoint.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<TrackPoint> trackPoints = new HashSet<>();

	public TrackSegment() {
		super();
	}

	public TrackSegment(Long id, Track track, Set<TrackPoint> trackPoints) {
		super();
		this.id = id;
		this.track = track;
		this.trackPoints = trackPoints;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Track getTrack() {
		return track;
	}

	public void setTrack(Track track) {
		this.track = track;
	}

	public Set<TrackPoint> getTrackPoints() {
		return trackPoints;
	}

	public void setTrackPoints(Set<TrackPoint> trackPoints) {
		this.trackPoints = trackPoints;
	}

}
