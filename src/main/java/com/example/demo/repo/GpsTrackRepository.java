package com.example.demo.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.GPS;
import com.example.demo.rest.response.GpsTrack;

@Repository
public interface GpsTrackRepository extends PagingAndSortingRepository<GPS, Long> {
	@Query(value = "SELECT new com.example.demo.rest.response.GpsTrack(g.id, f.id, g.name,g.description, f.uploadBy, f.uploadDate) FROM File f, GPS g WHERE f.gps=g")
	Page<GpsTrack> getLatestTrack(Pageable pageable);
}
