package com.example.demo.service;

import com.example.demo.rest.response.GpsTrack;

public interface GpsService {
	GpsTrack getGpsInformation(Long gpsId);
}
