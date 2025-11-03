package com.pollitica_Stat.Service;

import org.springframework.web.multipart.MultipartFile;

public interface VoterService {
	 public String saveVotersFromFile(MultipartFile file, Integer prabhagId);
}
