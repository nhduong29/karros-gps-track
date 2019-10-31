package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.GPS;
import com.example.demo.rest.response.GpsTrack;

@Repository
public interface GpsRepository extends JpaRepository<GPS, Long> {

	@Query(value = "SELECT new com.example.demo.rest.response.GpsTrack(g.id, f.id, g.name, g.description, f.uploadBy, f.uploadDate) FROM File f, GPS g WHERE f.gps=g AND g.id=:gpsId")
	GpsTrack getGpsInformation(@Param("gpsId") Long gpsId);
}
