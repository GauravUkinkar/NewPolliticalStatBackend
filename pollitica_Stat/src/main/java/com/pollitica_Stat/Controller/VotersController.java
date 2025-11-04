package com.pollitica_Stat.Controller;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pollitica_Stat.Dto.Message;
import com.pollitica_Stat.Dto.VotersDetailsDto;
import com.pollitica_Stat.Model.VotersDetails;
import com.pollitica_Stat.Repository.VoterRepository;
import com.pollitica_Stat.Service.VoterService;
import com.pollitica_Stat.ServiceImpl.ExcelExportServiceImpl;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/voters")
@CrossOrigin(origins = { "*" }, allowedHeaders = { "*" })
@Log4j2
@RequiredArgsConstructor
public class VotersController {
	
	private final VoterService voterService;
	private final ExcelExportServiceImpl excelExportService;
	private final VoterRepository voterRepository;
	@PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> uploadVoters(
	        @RequestParam("file") MultipartFile file,
	        @RequestParam("prabhagId") Integer prabhagId) {

	    return ResponseEntity.ok(voterService.saveVotersFromFile(file, prabhagId));
	}
	@GetMapping("/search/surname")
	public void searchBySurname(
	        @RequestParam String surname,
	        @RequestParam(defaultValue = "1") int page,
	        @RequestParam(defaultValue = "10") int size,
	        @RequestParam(defaultValue = "false") boolean export,
	        HttpServletResponse response
	) throws IOException {

	    if (export) {
	        List<VotersDetails> voters = voterRepository
	            .findByVoterEnglishNameStartingWithIgnoreCaseOrVoterMarathiNameStartingWith(
	                    surname, surname, Pageable.unpaged()
	            ).getContent();

	        excelExportService.exportVoters(voters, "voters_by_surname", response);
	        return;
	    }

	    Message<Page<VotersDetailsDto>> result = voterService.searchBySurname(surname, page, size);
	    response.setContentType("application/json");
	    new com.fasterxml.jackson.databind.ObjectMapper().writeValue(response.getOutputStream(), result);
	}

	@GetMapping("/search/name")
    public void searchByName(
            @RequestParam String name,
            @RequestParam(defaultValue = "1") int page,
	        @RequestParam(defaultValue = "10") int size,
	        @RequestParam(defaultValue = "false") boolean export,
	        HttpServletResponse response
	) throws IOException {

	    if (export) {
	        List<VotersDetails> voters = voterRepository
	            .findByVoterEnglishNameContainingIgnoreCaseOrVoterMarathiNameContainingIgnoreCase(
	                    name, name, Pageable.unpaged()
	            ).getContent();

	        excelExportService.exportVoters(voters, "voters_by_surname", response);
	        return;
	    }

	    Message<Page<VotersDetailsDto>> result = voterService.searchByName(name, page, size);
	    response.setContentType("application/json");
	    new com.fasterxml.jackson.databind.ObjectMapper().writeValue(response.getOutputStream(), result);
	}
      
	@GetMapping("/search/age")
    public void searchByAge(
            @RequestParam String age,
            @RequestParam(defaultValue = "1") int page,
	        @RequestParam(defaultValue = "10") int size,
	        @RequestParam(defaultValue = "false") boolean export,
	        HttpServletResponse response
	) throws IOException {

	    if (export) {
	        List<VotersDetails> voters = voterRepository
	            .findByAge(
	                    age, Pageable.unpaged()
	            ).getContent();

	        excelExportService.exportVoters(voters, "voters_by_surname", response);
	        return;
	    }

	    Message<Page<VotersDetailsDto>> result = voterService.searchByAge(age, page, size);
	    response.setContentType("application/json");
	    new com.fasterxml.jackson.databind.ObjectMapper().writeValue(response.getOutputStream(), result);
	}
      
	@GetMapping("/search/gender")
    public void searchByGender(
            @RequestParam String gender,
            @RequestParam(defaultValue = "1") int page,
	        @RequestParam(defaultValue = "10") int size,
	        @RequestParam(defaultValue = "false") boolean export,
	        HttpServletResponse response
	) throws IOException {

	    if (export) {
	        List<VotersDetails> voters = voterRepository
	            .findByGender(
	                    gender, Pageable.unpaged()
	            ).getContent();

	        excelExportService.exportVoters(voters, "voters_by_surname", response);
	        return;
	    }

	    Message<Page<VotersDetailsDto>> result = voterService.searchByGender(gender, page, size);
	    response.setContentType("application/json");
	    new com.fasterxml.jackson.databind.ObjectMapper().writeValue(response.getOutputStream(), result);
	}
	
	@GetMapping("/search/village")
	public void searchByVillage(
	        @RequestParam String villageName,
	        @RequestParam(defaultValue = "1") int page,
	        @RequestParam(defaultValue = "10") int size,
	        @RequestParam(defaultValue = "false") boolean export,
	        HttpServletResponse response
	) throws IOException {

	    if (export) {
	        List<VotersDetails> voters = voterRepository
	            .findByvillageName(
	            		villageName, Pageable.unpaged()
	            ).getContent();

	        excelExportService.exportVoters(voters, "voters_by_village", response);
	        return;
	    }

	    Message<Page<VotersDetailsDto>> result = voterService.searchByVillageName(villageName, page, size);
	    response.setContentType("application/json");
	    new com.fasterxml.jackson.databind.ObjectMapper().writeValue(response.getOutputStream(), result);
	}

	@GetMapping("/voters/all")
	public void getAllVoterDetails(
	        @RequestParam(defaultValue = "1") int page,
	        @RequestParam(defaultValue = "10") int size,
	        @RequestParam(defaultValue = "false") boolean export,
	        HttpServletResponse response
	) throws IOException {

	    if (export) {
	        List<VotersDetails> voters = voterRepository.findAll();
	        excelExportService.exportVoters(voters, "all_voters", response);
	        return;
	    }

	    Message<Page<VotersDetailsDto>> result = voterService.getAllVoterDetails(page, size);
	    response.setContentType("application/json");
	    new com.fasterxml.jackson.databind.ObjectMapper().writeValue(response.getOutputStream(), result);
	}


}
