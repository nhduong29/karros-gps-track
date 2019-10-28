package com.example.demo.service.impl;

import java.io.IOException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.config.security.CustomUserDetail;
import com.example.demo.entity.File;
import com.example.demo.entity.GPS;
import com.example.demo.entity.User;
import com.example.demo.repo.FileRepository;
import com.example.demo.repo.UserRepository;
import com.example.demo.rest.exception.FileNotFoundException;
import com.example.demo.rest.exception.FileStorageException;
import com.example.demo.service.FileStorageService;
import com.example.demo.service.GPSParserService;

@Service
public class FileStorageServiceImplement implements FileStorageService {
	private static final Logger logger = LoggerFactory.getLogger(FileStorageServiceImplement.class);

	@Autowired
	private FileRepository fileRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private GPSParserService gpsParserService;

	@Override
	public File storeFile(MultipartFile file) {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		try {
			if (fileName.contains("..")) {
				throw new FileStorageException("Sorry! The path of file is wrong " + fileName);
			}
			gpsParserService.getTracksFromFile(file);

			File storedFile = new File();
			User user = new User();
			GPS gps = new GPS("twest", "test des", "suong");
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (principal instanceof UserDetails) {
				String username = ((CustomUserDetail) principal).getUsername();
				user = userRepository.findByUsername(username).orElse(new User());
				storedFile = new File(fileName, file.getContentType(), file.getBytes(), username, new Date(), gps,
						user);
			}
			return fileRepository.save(storedFile);
		} catch (IOException e) {
			throw new FileStorageException("Could not store file:  " + fileName + ". Please check and try again!", e);
		}
	}

	@Override
	public File getFile(String fileId) {
		return fileRepository.findById(fileId)
				.orElseThrow(() -> new FileNotFoundException("File not found with id " + fileId));
	}

}
