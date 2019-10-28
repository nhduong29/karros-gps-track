package com.example.demo.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Track;

public interface GPSParserService {
	List<Track> getTracksFromFile(MultipartFile file);
}
