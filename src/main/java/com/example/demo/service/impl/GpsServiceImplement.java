package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repo.GpsRepository;
import com.example.demo.rest.response.GpsTrack;
import com.example.demo.service.GpsService;

@Service
public class GpsServiceImplement implements GpsService {
	@Autowired
	GpsRepository gpsRepository;

	@Override
	public GpsTrack getGpsInformation(Long gpsId) {
		return gpsRepository.getGpsInformation(gpsId);
	}

}
