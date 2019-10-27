package com.example.demo.service.impl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.File;
import com.example.demo.repo.FileRepository;
import com.example.demo.rest.exception.FileNotFoundException;
import com.example.demo.rest.exception.FileStorageException;
import com.example.demo.service.FileStorageService;

@Service
public class FileStorageServiceImplement implements FileStorageService {
	@Autowired
	private FileRepository fileRepository;

	@Override
	public File storeFile(MultipartFile file) {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		try {
			if (fileName.contains("..")) {
				throw new FileStorageException("Sorry! The path of file is wrong " + fileName);
			}
			File storedFile = new File(fileName, file.getContentType(), file.getBytes());
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
