package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.WayPoint;

@Repository
public interface WayPointRepository extends JpaRepository<WayPoint, Long> {

}
