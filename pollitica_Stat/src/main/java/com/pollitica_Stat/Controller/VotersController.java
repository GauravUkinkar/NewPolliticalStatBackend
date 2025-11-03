package com.pollitica_Stat.Controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pollitica_Stat.Service.VoterService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/voters")
@CrossOrigin(origins = { "*" }, allowedHeaders = { "*" })
@Log4j2
@RequiredArgsConstructor
public class VotersController {
	
	private final VoterService voterService;
	@PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> uploadVoters(
	        @RequestParam("file") MultipartFile file,
	        @RequestParam("prabhagId") Integer prabhagId) {

	    return ResponseEntity.ok(voterService.saveVotersFromFile(file, prabhagId));
	}

}
