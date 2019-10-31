package com.example.demo.service;

import java.util.Optional;

import com.example.demo.entity.GPS;

public interface GpsService {
	Optional<GPS> getGps(Long id);
}
