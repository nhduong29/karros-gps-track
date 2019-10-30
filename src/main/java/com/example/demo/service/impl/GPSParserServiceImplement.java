package com.example.demo.service.impl;

import java.io.IOException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.GPS;
import com.example.demo.entity.Track;
import com.example.demo.entity.TrackPoint;
import com.example.demo.entity.TrackSegment;
import com.example.demo.entity.WayPoint;
import com.example.demo.service.GPSParserService;

import io.jenetics.jpx.GPX;
import io.jenetics.jpx.Metadata;
import io.jenetics.jpx.Person;

@Service
public class GPSParserServiceImplement implements GPSParserService {
	private static final Logger logger = LoggerFactory.getLogger(GPSParserServiceImplement.class);

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
					gps.setAuthor(author.get().getName().orElse(null));
				}
				gps.setDescription(metadata.getDescription().orElse(null));
				gps.setName(metadata.getName().orElse(null));
			}
			Set<WayPoint> wayPoints = gpxData.getWayPoints().stream().map(jpxWaypoint -> convertWaypoint(jpxWaypoint))
					.collect(Collectors.toSet());
			gps.setWaypoints(wayPoints);
			Set<Track> tracks = gpxData.getTracks().stream().map(jpxTrack -> convertTrack(jpxTrack))
					.collect(Collectors.toSet());
			gps.setTracks(tracks);
			return gps;
		} catch (IOException e) {
			logger.error("Can't convert the file, Please check again", e);
		}
		return gps;

	}

	private WayPoint convertWaypoint(io.jenetics.jpx.WayPoint wayPoint) {
		WayPoint point = new WayPoint();
		point.setDescription(wayPoint.getDescription().orElse(null));
		point.setLatitude(wayPoint.getLatitude().floatValue());
		point.setLongitude(wayPoint.getLongitude().floatValue());
		point.setName(wayPoint.getName().orElse(null));
		point.setElevation(wayPoint.getElevation().orElse(null));
		return point;
	}

	private TrackPoint convertTrackPoint(io.jenetics.jpx.WayPoint jpxTrackPoint) {
		TrackPoint trackPoint = new TrackPoint();
		trackPoint.setDescription(jpxTrackPoint.getDescription().orElse(null));
		trackPoint.setElevation(jpxTrackPoint.getElevation().orElse(null));
		trackPoint.setLatitude(jpxTrackPoint.getLatitude());
		trackPoint.setLongitude(jpxTrackPoint.getLongitude());
		return trackPoint;
	}

	private TrackSegment convertTrackSegment(io.jenetics.jpx.TrackSegment jpxSegment) {
		Set<TrackPoint> trackPoints = jpxSegment.getPoints().stream()
				.map(jpxTrackPoint -> convertTrackPoint(jpxTrackPoint)).collect(Collectors.toSet());
		TrackSegment segment = new TrackSegment();
		segment.setTrackPoints(trackPoints);
		return segment;
	}

	private Track convertTrack(io.jenetics.jpx.Track jpxTrack) {
		Track track = new Track();
		track.setDescription(jpxTrack.getDescription().orElse(null));
		track.setName(jpxTrack.getDescription().orElse(null));
		Set<TrackSegment> segments = jpxTrack.getSegments().stream().map(jpxSegment -> convertTrackSegment(jpxSegment))
				.collect(Collectors.toSet());
		track.setTrackSegments(segments);
		return track;
	}

}
