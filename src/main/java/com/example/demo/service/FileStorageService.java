package com.example.demo.service;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.File;

public interface FileStorageService {
	File storeFile(MultipartFile file);

	File getFile(String fileId);
}
