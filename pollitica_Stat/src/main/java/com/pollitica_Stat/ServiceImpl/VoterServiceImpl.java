package com.pollitica_Stat.ServiceImpl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pollitica_Stat.Dto.Message;
import com.pollitica_Stat.Dto.VotersDetailsDto;
import com.pollitica_Stat.Mapper.VotersDetailsMapper;
import com.pollitica_Stat.Model.Prabhag;

import com.pollitica_Stat.Model.VotersDetails;
import com.pollitica_Stat.Repository.PrabhagRepository;

import com.pollitica_Stat.Repository.VoterRepository;
import com.pollitica_Stat.Service.VoterService;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2

public class VoterServiceImpl implements VoterService {
	private final PrabhagRepository prabhagService;
	private final VoterRepository voterRepository;

	private final VotersDetailsMapper votersDetailsMapperImpl;

	private VotersDetailsDto mapToDto(VotersDetails v) {
		VotersDetailsDto dto = new VotersDetailsDto();
		BeanUtils.copyProperties(v, dto);
		dto.setPrabhagId(v.getPrabhag() != null ? v.getPrabhag().getPrabhagId() : null);
		return dto;
	}

	@Override
	public String saveVotersFromFile(MultipartFile file, Integer prabhagId) {
		try {
			Prabhag prabhag = prabhagService.findByPrabhagId(prabhagId);
			if (prabhag == null) {
				return "❌ Invalid Prabhag ID: " + prabhagId;
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()));
			String line;
			int saved = 0, skipped = 0;

			br.readLine(); // skip header

			while ((line = br.readLine()) != null) {

				String[] data = line.split(",", -1);
				if (data.length < 10)
					continue;

				String voterId = data[1].trim();

				// Skip if voter already exists
				if (voterRepository.existsByVoterId(voterId)) {
					skipped++;
					continue;
				}

				VotersDetails voter = new VotersDetails();
				voter.setVoterId(voterId);
				voter.setVoterEnglishName(data[2]);
				voter.setVoterMarathiName(data[3]);
				voter.setVotersFatherEnglishName(data[4]);
				voter.setVotersFatherMarathiName(data[5]);
				voter.setAge(data[6]);
				voter.setHouseNo(data[7]);
				voter.setGender(data[8]);
				voter.setVillageName(data[9]);
				voter.setPrabhag(prabhag);
				

				voterRepository.save(voter);
				saved++;
			}

			return "✅ Upload Completed\n" + "Saved: " + saved + "\n" + "Skipped (existing): " + skipped;

		} catch (Exception e) {
			log.error("Error uploading voters CSV", e);
			return "❌ Error: " + e.getMessage();
		}
	}

	@Override
	public Message<Page<VotersDetailsDto>> searchBySurname(String surname, int page, int size) {

	    Message<Page<VotersDetailsDto>> response = new Message<>();

	    try {
	        // ✅ Adjust for 1-based page index
	        int pageIndex = (page > 0) ? page - 1 : 0;

	        Pageable pageable = PageRequest.of(pageIndex, size);

	        Page<VotersDetails> votersPage = voterRepository
	                .findByVoterEnglishNameStartingWithIgnoreCaseOrVoterMarathiNameStartingWith(
	                        surname, surname, pageable);

	        if (votersPage.isEmpty()) {
	            response.setStatus(HttpStatus.NOT_FOUND);
	            response.setResponseMessage("No voters found with entered name");
	            response.setData(null);
	            return response;
	        }

	        // Convert Entity -> DTO list
	        List<VotersDetailsDto> dtoList = votersPage.getContent().stream().map(voter -> {
	            VotersDetailsDto dto = mapToDto(voter);

	            // Fix: Set PrabhagId in DTO if exists
	            if (voter.getPrabhag() != null) {
	                dto.setPrabhagId(voter.getPrabhag().getPrabhagId());
	            }

	            return dto;
	        }).toList();

	        // Create Page of DTO
	        Page<VotersDetailsDto> dtoPage = new PageImpl<>(dtoList, pageable, votersPage.getTotalElements());

	        response.setStatus(HttpStatus.OK);
	        response.setResponseMessage("Voter records fetched successfully");
	        response.setData(dtoPage);

	    } catch (Exception e) {
	        log.error("Error searching voters by surname: {}", e.getMessage(), e);
	        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
	        response.setResponseMessage("Something went wrong. Please try again later.");
	        response.setData(null);
	    }

	    return response;
	}

	@Override
	public Message<Page<VotersDetailsDto>> searchByName(String name, int page, int size) {
	    Message<Page<VotersDetailsDto>> response = new Message<>();

	    try {
	        // ✅ Adjust for 1-based page index
	        int pageIndex = (page > 0) ? page - 1 : 0;
	        Pageable pageable = PageRequest.of(pageIndex, size);

	        Page<VotersDetails> votersPage = voterRepository
	                .findByVoterEnglishNameContainingIgnoreCaseOrVoterMarathiNameContainingIgnoreCase(
	                        name, name, pageable);

	        if (votersPage.isEmpty()) {
	            response.setStatus(HttpStatus.NOT_FOUND);
	            response.setResponseMessage("No voters found with entered name");
	            response.setData(null);
	            return response;
	        }

	        // Convert Entity -> DTO list
	        List<VotersDetailsDto> dtoList = votersPage.getContent().stream().map(voter -> {
	            VotersDetailsDto dto = mapToDto(voter);

	            // Fix: Set PrabhagId in DTO if exists
	            if (voter.getPrabhag() != null) {
	                dto.setPrabhagId(voter.getPrabhag().getPrabhagId());
	            }

	            return dto;
	        }).toList();

	        // Create Page of DTO
	        Page<VotersDetailsDto> dtoPage = new PageImpl<>(dtoList, pageable, votersPage.getTotalElements());

	        response.setStatus(HttpStatus.OK);
	        response.setResponseMessage("Voter records fetched successfully");
	        response.setData(dtoPage);

	    } catch (Exception e) {
	        log.error("Error searching voters by name: {}", e.getMessage(), e);
	        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
	        response.setResponseMessage("Something went wrong. Please try again later.");
	        response.setData(null);
	    }

	    return response;
	}


	@Override
	public Message<Page<VotersDetailsDto>> searchByAge(String age, int page, int size) {
	    Message<Page<VotersDetailsDto>> response = new Message<>();

	    try {
	        // ✅ Adjust for 1-based page index
	        int pageIndex = (page > 0) ? page - 1 : 0;
	        Pageable pageable = PageRequest.of(pageIndex, size);

	        Page<VotersDetails> votersPage = voterRepository.findByAge(age, pageable);

	        if (votersPage.isEmpty()) {
	            response.setStatus(HttpStatus.NOT_FOUND);
	            response.setResponseMessage("No voters found with entered age");
	            response.setData(null);
	            return response;
	        }

	        // Convert Entity -> DTO list
	        List<VotersDetailsDto> dtoList = votersPage.getContent().stream().map(voter -> {
	            VotersDetailsDto dto = mapToDto(voter);

	            // ✅ Set PrabhagId if exists
	            if (voter.getPrabhag() != null) {
	                dto.setPrabhagId(voter.getPrabhag().getPrabhagId());
	            }

	            return dto;
	        }).toList();

	        // Create Page of DTOs
	        Page<VotersDetailsDto> dtoPage = new PageImpl<>(dtoList, pageable, votersPage.getTotalElements());

	        response.setStatus(HttpStatus.OK);
	        response.setResponseMessage("Voter records fetched successfully");
	        response.setData(dtoPage);

	    } catch (Exception e) {
	        log.error("Error searching voters by age: {}", e.getMessage(), e);
	        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
	        response.setResponseMessage("Something went wrong. Please try again later.");
	        response.setData(null);
	    }

	    return response;
	}

	

	@Override
	public Message<Page<VotersDetailsDto>> searchByGender(String gender, int page, int size) {
	    Message<Page<VotersDetailsDto>> response = new Message<>();

	    try {
	        // ✅ Fix pagination to start from 1
	        int pageIndex = (page > 0) ? page - 1 : 0;
	        Pageable pageable = PageRequest.of(pageIndex, size);

	        Page<VotersDetails> votersPage = voterRepository.findByGender(gender, pageable);

	        if (votersPage.isEmpty()) {
	            response.setStatus(HttpStatus.NOT_FOUND);
	            response.setResponseMessage("No voters found with entered gender");
	            response.setData(null);
	            return response;
	        }

	        // Convert Entity → DTO list
	        List<VotersDetailsDto> dtoList = votersPage.getContent().stream().map(voter -> {
	            VotersDetailsDto dto = mapToDto(voter);

	            // ✅ Set PrabhagId if exists
	            if (voter.getPrabhag() != null) {
	                dto.setPrabhagId(voter.getPrabhag().getPrabhagId());
	            }

	            return dto;
	        }).toList();

	        // Create Page of DTOs
	        Page<VotersDetailsDto> dtoPage = new PageImpl<>(dtoList, pageable, votersPage.getTotalElements());

	        response.setStatus(HttpStatus.OK);
	        response.setResponseMessage("Voter records fetched successfully");
	        response.setData(dtoPage);

	    } catch (Exception e) {
	        log.error("Error searching voters by gender: {}", e.getMessage(), e);
	        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
	        response.setResponseMessage("Something went wrong. Please try again later.");
	        response.setData(null);
	    }

	    return response;
	}


	@Override

	public Message<Page<VotersDetailsDto>> getAllVoterDetails(int page, int size) {
	    Message<Page<VotersDetailsDto>> response = new Message<>();

	    try {
	        // Adjust for 1-based page index
	        int pageIndex = (page > 0) ? page - 1 : 0;

	        Pageable pageable = PageRequest.of(pageIndex, size);
	        Page<VotersDetails> votersPage = voterRepository.findAll(pageable);

	        if (votersPage.isEmpty()) {
	            response.setStatus(HttpStatus.NOT_FOUND);
	            response.setResponseMessage("No voters found");
	            response.setData(null);
	            return response;
	        }

	        // Convert Entity -> DTO list
	        List<VotersDetailsDto> dtoList = votersPage.getContent().stream().map(voter -> {
	            VotersDetailsDto dto = mapToDto(voter);

	            if (voter.getPrabhag() != null) {
	                dto.setPrabhagId(voter.getPrabhag().getPrabhagId());
	            }

	            return dto;
	        }).toList();

	        // Create Page of DTOs
	        Page<VotersDetailsDto> dtoPage = new PageImpl<>(dtoList, pageable, votersPage.getTotalElements());

	        response.setStatus(HttpStatus.OK);
	        response.setResponseMessage("Voter records fetched successfully");
	        response.setData(dtoPage);

	    } catch (Exception e) {
	        log.error("Error fetching all voter records: {}", e.getMessage(), e);
	        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
	        response.setResponseMessage("Something went wrong. Please try again later.");
	        response.setData(null);
	    }

	    return response;
	}
	
	@Override
	public Message<Page<VotersDetailsDto>> searchByVillageName(String villageName, int page, int size) {
	    Message<Page<VotersDetailsDto>> response = new Message<>();

	    try {
	        // ✅ Ensure pagination starts from 1
	        int pageIndex = (page > 0) ? page - 1 : 0;
	        Pageable pageable = PageRequest.of(pageIndex, size);

	        Page<VotersDetails> votersPage = voterRepository.findByvillageName(villageName, pageable);

	        if (votersPage.isEmpty()) {
	            response.setStatus(HttpStatus.NOT_FOUND);
	            response.setResponseMessage("No voters found with entered village name");
	            response.setData(null);
	            return response;
	        }

	        // Convert Entity → DTO list
	        List<VotersDetailsDto> dtoList = votersPage.getContent().stream().map(voter -> {
	            VotersDetailsDto dto = mapToDto(voter);

	            // ✅ Include Prabhag ID if available
	            if (voter.getPrabhag() != null) {
	                dto.setPrabhagId(voter.getPrabhag().getPrabhagId());
	            }

	            return dto;
	        }).toList();

	        // Create Page of DTOs
	        Page<VotersDetailsDto> dtoPage = new PageImpl<>(dtoList, pageable, votersPage.getTotalElements());

	        response.setStatus(HttpStatus.OK);
	        response.setResponseMessage("Voter records fetched successfully");
	        response.setData(dtoPage);

	    } catch (Exception e) {
	        log.error("Error searching voters by village name: {}", e.getMessage(), e);
	        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
	        response.setResponseMessage("Something went wrong. Please try again later.");
	        response.setData(null);
	    }

	    return response;
	}

	@Override
	public Message<Page<VotersDetailsDto>> searchByPrabhag(Integer prabhagId, int page, int size) {
	    Message<Page<VotersDetailsDto>> response = new Message<>();

	    try {
	        int pageIndex = (page > 0) ? page - 1 : 0;
	        Pageable pageable = PageRequest.of(pageIndex, size);

	        Page<VotersDetails> votersPage = voterRepository.findByPrabhagId(prabhagId, pageable);

	        if (votersPage.isEmpty()) {
	            response.setStatus(HttpStatus.NOT_FOUND);
	            response.setResponseMessage("No voters found for the given Prabhag ID");
	            response.setData(null);
	            return response;
	        }

	        List<VotersDetailsDto> dtoList = votersPage.getContent().stream().map(voter -> {
	            VotersDetailsDto dto = mapToDto(voter);
	            if (voter.getPrabhag() != null) {
	                dto.setPrabhagId(voter.getPrabhag().getPrabhagId());
	            }
	            return dto;
	        }).toList();

	        Page<VotersDetailsDto> dtoPage = new PageImpl<>(dtoList, pageable, votersPage.getTotalElements());

	        response.setStatus(HttpStatus.OK);
	        response.setResponseMessage("Voter records fetched successfully");
	        response.setData(dtoPage);

	    } catch (Exception e) {
	        log.error("Error searching voters by Prabhag: {}", e.getMessage(), e);
	        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
	        response.setResponseMessage("Something went wrong. Please try again later.");
	        response.setData(null);
	    }

	    return response;
	}




		
	}


