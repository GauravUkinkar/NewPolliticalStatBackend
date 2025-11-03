package com.pollitica_Stat.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pollitica_Stat.Dto.Message;
import com.pollitica_Stat.Dto.PrabhagRequestDto;
import com.pollitica_Stat.Service.PrabhagService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/prabhag")
@CrossOrigin(origins = { "*" }, allowedHeaders = { "*" })
@Log4j2
@RequiredArgsConstructor
public class PrabhagController {
	private final PrabhagService prabhagService;
	
	
	@PostMapping("/addPrabhag")
	public Message<PrabhagRequestDto> addPrabhag(@RequestBody PrabhagRequestDto request) {
		return prabhagService.addPrabhag(request);
	}
	@PostMapping("/updatePrabhag")
	public Message<PrabhagRequestDto> updatePrabhag(@RequestBody PrabhagRequestDto request) {
		return prabhagService.updatePrabhag(request);
	}
	@DeleteMapping("/deletePrabhag")
	public Message<PrabhagRequestDto> deletePrabhag(@RequestParam("id") int id) {
		return prabhagService.deletePrabhag(id);
	}
	@GetMapping("/getPrabhagbyId")
	public Message<PrabhagRequestDto> getPrabhagbyId(@RequestParam("id") int id) {
		return prabhagService.getPrabhagbyId(id);
	}
	@GetMapping("/getPrabhagByPrabhagId")
	public Message<PrabhagRequestDto> getPrabhagByPrabhagId(@RequestParam("prabhagId") int prabhagId) {
		return prabhagService.getPrabhagByPrabhagId(prabhagId);
	}

}
