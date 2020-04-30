package com.hk.prj.filehandler.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileController {

	
	@GetMapping(value = "/modelo")
	@ResponseBody
	public ResponseEntity<ByteArrayResource> getModelo() throws IOException {
	    String path = "file_example_ODS_10.ods";
	    File file = new File(path);
	    Path pathObj = Paths.get(file.getAbsolutePath());
	    ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(pathObj));
	    HttpHeaders headers = new HttpHeaders();
	    headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getName());
	    headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
	    headers.add(HttpHeaders.CONTENT_LENGTH, String.valueOf(Files.readAllBytes(pathObj).length));
	    headers.add(HttpHeaders.TRANSFER_ENCODING, "binary");

	    return new ResponseEntity<>(resource, headers, HttpStatus.OK);
	}
	
}
