package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.rest.response.GpsTrack;
import com.example.demo.service.GpsTrackService;

@RestController
@RequestMapping("/api/gps")
public class GpsTrackController {
	@Autowired
	private GpsTrackService gpsTrackService;

	@GetMapping("/tracks")
	public Page<GpsTrack> getLatestTrack(Pageable pageable) {
		return gpsTrackService.getLatestTrack(pageable);
	}
}
