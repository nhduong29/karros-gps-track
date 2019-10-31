package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.File;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {

	@Query(value = "SELECT * FROM File f, User u WHERE f.user_id=:userId AND f.id=:fileId", nativeQuery = true)
	File getFileMe(@Param("fileId") Long fileId, @Param("userId") Long userId);

}
