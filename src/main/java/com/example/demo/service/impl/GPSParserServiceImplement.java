package com.example.demo.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.GPS;
import com.example.demo.entity.Track;
import com.example.demo.entity.TrackPoint;
import com.example.demo.entity.TrackSegment;
import com.example.demo.entity.WayPoint;
import com.example.demo.repo.GpsRepository;
import com.example.demo.service.GPSParserService;

import io.jenetics.jpx.GPX;
import io.jenetics.jpx.Length;
import io.jenetics.jpx.Metadata;
import io.jenetics.jpx.Person;

@Service
public class GPSParserServiceImplement implements GPSParserService {
	private static final Logger logger = LoggerFactory.getLogger(GPSParserServiceImplement.class);

	@Autowired
	GpsRepository gpsRepository;

	@Override
	public GPS convertGpsFromFile(MultipartFile file) {
		GPS gps = new GPS();
		try {
			GPX gpxData = GPX.read(file.getInputStream());
			Optional<Metadata> metadataOptional = gpxData.getMetadata();
			if (metadataOptional.isPresent()) {
				Metadata metadata = metadataOptional.get();
				Optional<Person> author = metadata.getAuthor();
				if (author.isPresent()) {
					gps.setAuthor(author.get().getName().orElse(""));
				}
				gps.setDescription(metadata.getDescription().orElse(""));
				gps.setName(metadata.getName().orElse(""));
			}
			Set<WayPoint> wayPoints = gpxData.getWayPoints().stream()
					.map(jpxWaypoint -> convertWaypoint(jpxWaypoint, gps)).collect(Collectors.toSet());
			gps.setWaypoints(wayPoints);
			Set<Track> tracks = gpxData.getTracks().stream().map(jpxTrack -> convertTrack(jpxTrack, gps))
					.collect(Collectors.toSet());
			gps.setTracks(tracks);
			gpsRepository.save(gps);
			return gps;
		} catch (IOException e) {
			logger.error("Can't convert the file, Please check again", e);
		}
		return null;

	}

	private WayPoint convertWaypoint(io.jenetics.jpx.WayPoint wayPoint, GPS gps) {
		WayPoint point = new WayPoint();
		point.setGps(gps);
		point.setDescription(wayPoint.getDescription().orElse(""));
		point.setLatitude(BigDecimal.valueOf(wayPoint.getLatitude().doubleValue()));
		point.setLongitude(BigDecimal.valueOf(wayPoint.getLongitude().doubleValue()));
		point.setName(wayPoint.getName().orElse(""));
		Optional<Length> elevation = wayPoint.getElevation();
		if (elevation.isPresent()) {
			point.setElevation(elevation.get().doubleValue());
		}
		return point;
	}

	private TrackPoint convertTrackPoint(io.jenetics.jpx.WayPoint jpxTrackPoint, TrackSegment segment) {
		TrackPoint trackPoint = new TrackPoint();
		trackPoint.setName(jpxTrackPoint.getName().orElse(""));
		trackPoint.setDescription(jpxTrackPoint.getDescription().orElse(""));
		trackPoint.setLatitude(BigDecimal.valueOf(jpxTrackPoint.getLatitude().doubleValue()));
		trackPoint.setLongitude(BigDecimal.valueOf(jpxTrackPoint.getLongitude().doubleValue()));
		Optional<Length> elevation = jpxTrackPoint.getElevation();
		if (elevation.isPresent()) {
			trackPoint.setElevation(elevation.get().doubleValue());
		}
		trackPoint.setTrackSegment(segment);
		return trackPoint;
	}

	private TrackSegment convertTrackSegment(io.jenetics.jpx.TrackSegment jpxSegment, Track track) {
		TrackSegment segment = new TrackSegment();
		Set<TrackPoint> trackPoints = jpxSegment.getPoints().stream()
				.map(jpxTrackPoint -> convertTrackPoint(jpxTrackPoint, segment)).collect(Collectors.toSet());
		segment.setTrackPoints(trackPoints);
		segment.setTrack(track);
		return segment;
	}

	private Track convertTrack(io.jenetics.jpx.Track jpxTrack, GPS gps) {
		Track track = new Track();
		track.setGps(gps);
		track.setDescription(jpxTrack.getDescription().orElse(""));
		track.setName(jpxTrack.getDescription().orElse(""));
		Set<TrackSegment> segments = jpxTrack.getSegments().stream()
				.map(jpxSegment -> convertTrackSegment(jpxSegment, track)).collect(Collectors.toSet());
		track.setTrackSegments(segments);
		return track;
	}

}
