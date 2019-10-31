package com.example.demo.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.entity.File;
import com.example.demo.rest.response.UploadFileResponse;
import com.example.demo.service.FileStorageService;

@RestController
@RequestMapping("/api/file")
public class FileController {

	@Autowired
	private FileStorageService fileStorageService;

	@PostMapping("/uploadFile")
	public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
		File dbFile = fileStorageService.storeFile(file);

		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/file/downloadFile/")
				.path(dbFile.getId().toString()).toUriString();

		return new UploadFileResponse(dbFile.getName(), fileDownloadUri, file.getContentType(), file.getSize());
	}

	@GetMapping("/downloadFile/{fileId}")
	public ResponseEntity<?> downloadFile(@PathVariable Long fileId) {
		File dbFile = fileStorageService.getFile(fileId);
		if (Objects.isNull(dbFile)) {
			return new ResponseEntity<>("File is not exist", HttpStatus.NOT_FOUND);
		}

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(dbFile.getType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getName() + "\"")
				.body(new ByteArrayResource(dbFile.getData()));
	}
}
