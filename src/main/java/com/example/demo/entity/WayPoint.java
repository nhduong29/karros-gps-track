package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "way_point")
public class WayPoint {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String desc;

	private Number latitude;

	private Number longitude;

	private Number elevation;

	@ManyToOne
	@JoinColumn(name = "gps_id", nullable = false)
	private GPS gps;

	public WayPoint(Long id, String name, String desc, Number latitude, Number longitude, Number elevation, GPS gps) {
		super();
		this.id = id;
		this.name = name;
		this.desc = desc;
		this.latitude = latitude;
		this.longitude = longitude;
		this.elevation = elevation;
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

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
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

	public GPS getGps() {
		return gps;
	}

	public void setGps(GPS gps) {
		this.gps = gps;
	}

}