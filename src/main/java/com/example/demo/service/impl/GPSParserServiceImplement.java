package com.example.demo.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.GPS;
import com.example.demo.entity.Track;
import com.example.demo.entity.WayPoint;
import com.example.demo.service.GPSParserService;

import io.jenetics.jpx.GPX;

@Service
public class GPSParserServiceImplement implements GPSParserService {
	private static final Logger logger = LoggerFactory.getLogger(GPSParserServiceImplement.class);

	@Override
	public GPS getTracksFromFile(MultipartFile file) {
		GPS gps = new GPS();
		try {
			List<Track> tracks = new ArrayList<Track>();
			File convFile = new File(file.getOriginalFilename());
			convFile.createNewFile();
			FileOutputStream fos = new FileOutputStream(convFile);
			fos.write(file.getBytes());
			fos.close();
			GPX gpxData = GPX.read(convFile.getAbsolutePath());
			Set<WayPoint> wayPointsSet = new HashSet<>();
			gpxData.getWayPoints().forEach(wayPoint -> {
				WayPoint point = new WayPoint();
				point.setDescription(wayPoint.getDescription().orElse(""));
				point.setLatitude(wayPoint.getLatitude().floatValue());
				point.setLongitude(wayPoint.getLongitude().floatValue());
				point.setName(wayPoint.getName().orElse(""));
				wayPointsSet.add(point);
			});
			gps.setWaypoints(wayPointsSet);
			return gps;
		} catch (IOException e) {
			logger.error("Can't convert the file, Please check again", e);
		}
		return gps;

	}

}
