package com.example.demo.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.File;

@Repository
public interface GpsTrackRepository extends PagingAndSortingRepository<File, Long> {
	@Query(value = "SELECT name FROM File", nativeQuery = true)
	Page<File> getLatestTrack(Pageable pageable);
}
