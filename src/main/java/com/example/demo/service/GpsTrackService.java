package com.example.demo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.entity.File;

public interface GpsTrackService {
	Page<File> getLatestTrack(Pageable pageable);
}
