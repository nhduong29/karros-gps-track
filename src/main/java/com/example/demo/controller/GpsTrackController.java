package com.example.demo.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.rest.response.GpsTrack;
import com.example.demo.service.GpsService;
import com.example.demo.service.GpsTrackService;

@RestController
@RequestMapping("/api/gps")
public class GpsTrackController {
	@Autowired
	private GpsTrackService gpsTrackService;
	@Autowired
	private GpsService gpsService;

	@GetMapping("/tracks")
	public Page<GpsTrack> getLatestTrack(Pageable pageable) {
		return gpsTrackService.getLatestTrack(pageable);
	}

	@GetMapping("/{gpsId}")
	public ResponseEntity<?> getGps(@PathVariable Long gpsId) {
		GpsTrack gps = gpsService.getGpsInformation(gpsId);
		if (Objects.nonNull(gps)) {
			return ResponseEntity.ok().body(gps);
		}
		return new ResponseEntity<>("GPS is not exist", HttpStatus.BAD_REQUEST);
	}
}
