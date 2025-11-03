package com.pollitica_Stat.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pollitica_Stat.Dto.Message;
import com.pollitica_Stat.Dto.VillageRequestDto;
import com.pollitica_Stat.Service.VillageService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/village")
@CrossOrigin(origins = { "*" }, allowedHeaders = { "*" })
@Log4j2
@RequiredArgsConstructor
public class VillageController {
	private final VillageService villageService;
	
	@PostMapping("/addPrabhag")
	public Message<VillageRequestDto> addPrabhag(@RequestBody VillageRequestDto request) {
		return villageService.addVillage(request);
	}
}
