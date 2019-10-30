package com.example.demo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.rest.response.GpsTrack;

public interface GpsTrackService {
	Page<GpsTrack> getLatestTrack(Pageable pageable);
}
