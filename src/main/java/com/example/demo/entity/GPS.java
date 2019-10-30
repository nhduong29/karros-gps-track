package com.example.demo.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "gps")
public class GPS {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@Size(max = 10000)
	private String description;

	private String author;

	@OneToOne(mappedBy = "gps")
	private File file;

	@OneToMany(mappedBy = "gps", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Track> tracks = new HashSet<>();

	@OneToMany(mappedBy = "gps", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<WayPoint> waypoints = new HashSet<>();

	public GPS() {
		super();
	}

	public GPS(String name, String description, String author, Set<Track> tracks, Set<WayPoint> waypoints) {
		super();
		this.name = name;
		this.description = description;
		this.author = author;
		this.tracks = tracks;
		this.waypoints = waypoints;
	}

	public GPS(String name, String description, String author) {
		super();
		this.name = name;
		this.description = description;
		this.author = author;
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

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
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
