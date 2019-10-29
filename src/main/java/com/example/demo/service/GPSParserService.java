package com.example.demo.service;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.GPS;

public interface GPSParserService {
	GPS getTracksFromFile(MultipartFile file);
}
