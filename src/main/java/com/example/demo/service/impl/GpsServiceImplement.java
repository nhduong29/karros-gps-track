package com.example.demo.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.GPS;
import com.example.demo.repo.GpsRepository;
import com.example.demo.service.GpsService;

@Service
public class GpsServiceImplement implements GpsService {
	@Autowired
	GpsRepository gpsRepository;

	@Override
	public Optional<GPS> getGps(Long id) {
		return gpsRepository.findById(id);
	}

}
