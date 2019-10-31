package com.example.demo.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.rest.response.GpsTrack;
import com.example.demo.service.GpsService;
import com.example.demo.service.GpsTrackService;

@RestController
@RequestMapping("/api/gps")
public class GpsTrackController {
	private static final String DESC_SORTING = "desc";
	private static final String DEFAULT_ORDER_BY_COLUMN = "uploadDate";
	private static final String DEFAULT_PAGE_SIZE = "10";
	private static final String DEFAULT_PAGE_NUMBER = "0";
	private static final String ASC_SORTING = "asc";
	@Autowired
	private GpsTrackService gpsTrackService;
	@Autowired
	private GpsService gpsService;

	@GetMapping("/tracks")
	public Page<GpsTrack> getLatestTrack(
			@RequestParam(value = "page", required = false, defaultValue = DEFAULT_PAGE_NUMBER) int page,
			@RequestParam(value = "size", required = false, defaultValue = DEFAULT_PAGE_SIZE) int size,
			@RequestParam(value = "oderBy", required = false, defaultValue = DEFAULT_ORDER_BY_COLUMN) String oderBy,
			@RequestParam(value = "direction", required = false, defaultValue = DESC_SORTING) String direction) {
		Order order = Order.desc(oderBy);
		if (ASC_SORTING.equalsIgnoreCase(direction)) {
			order = Order.asc(oderBy);
		}
		Pageable pageable = PageRequest.of(page, size, Sort.by(order));
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
