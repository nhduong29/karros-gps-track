package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.entity.File;
import com.example.demo.repo.GpsTrackRepository;
import com.example.demo.service.GpsTrackService;

@Service
public class GpsTrackServiceImplement implements GpsTrackService {
	@Autowired
	GpsTrackRepository gpsTrackRepository;

	@Override
	public Page<File> getLatestTrack(Pageable pageable) {
		return gpsTrackRepository.getLatestTrack(pageable);
	}

}
