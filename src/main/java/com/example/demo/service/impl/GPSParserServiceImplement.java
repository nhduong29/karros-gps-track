package com.example.demo.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Track;
import com.example.demo.service.GPSParserService;

import io.jenetics.jpx.GPX;
import io.jenetics.jpx.TrackSegment;

@Service
public class GPSParserServiceImplement implements GPSParserService {
	private static final Logger logger = LoggerFactory.getLogger(GPSParserServiceImplement.class);

	@Override
	public List<Track> getTracksFromFile(MultipartFile file) {
		try {
			File convFile = new File(file.getOriginalFilename());
			convFile.createNewFile();
			FileOutputStream fos = new FileOutputStream(convFile);
			fos.write(file.getBytes());
			fos.close();
			GPX.read(convFile.getAbsolutePath()).tracks().flatMap(io.jenetics.jpx.Track::segments)
					.flatMap(TrackSegment::points).forEach(System.out::println);
			return new ArrayList<Track>();
		} catch (IOException e) {
			logger.error("Can't convert the file, Please check again", e);
		}
		return new ArrayList<Track>();

	}

}
