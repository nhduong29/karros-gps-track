package com.example.demo.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.File;
import com.example.demo.rest.response.GpsTrack;

@Repository
public interface GpsTrackRepository extends PagingAndSortingRepository<File, Long> {
	@Query(value = "SELECT new com.example.demo.rest.response.GpsTrack(g.name,g.description, f.uploadBy, f.uploadDate) FROM File f, GPS g WHERE f.gps=g ORDER BY f.uploadDate DESC")
	Page<GpsTrack> getLatestTrack(Pageable pageable);
}
