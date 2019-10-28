package com.example.demo.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "gps")
public class GPS {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String description;

	private String author;

	@OneToOne(mappedBy = "gps")
	private File flie;

	@OneToMany(mappedBy = "gps")
	private Set<Track> tracks = new HashSet<>();

	@OneToMany(mappedBy = "gps")
	private Set<WayPoint> waypoints = new HashSet<>();

	public GPS() {
		super();
	}

	public GPS(Long id, String name, String description, String author, File flie, Set<Track> tracks,
			Set<WayPoint> waypoints) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.author = author;
		this.flie = flie;
		this.tracks = tracks;
		this.waypoints = waypoints;
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

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public File getFlie() {
		return flie;
	}

	public void setFlie(File flie) {
		this.flie = flie;
	}

	public Set<Track> getTracks() {
		return tracks;
	}

	public void setTracks(Set<Track> tracks) {
		this.tracks = tracks;
	}

	public Set<WayPoint> getWaypoints() {
		return waypoints;
	}

	public void setWaypoints(Set<WayPoint> waypoints) {
		this.waypoints = waypoints;
	}

}
