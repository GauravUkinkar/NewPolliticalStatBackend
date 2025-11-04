package com.pollitica_Stat.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.pollitica_Stat.Dto.Message;
import com.pollitica_Stat.Dto.VotersDetailsDto;
import com.pollitica_Stat.Model.VotersDetails;

public interface VoterService {
	 public String saveVotersFromFile(MultipartFile file, Integer prabhagId);
	 
	 public Message<Page<VotersDetailsDto>> searchBySurname(String surname, int page, int size);

	    public Message<Page<VotersDetailsDto>> searchByName(String name, int page, int size);

	    public Message<Page<VotersDetailsDto>> searchByAge(String age, int page, int size);

	    public Message<Page<VotersDetailsDto>> searchByGender(String gender, int page, int size);

	    public Message<Page<VotersDetailsDto>> searchByPrabhag(Integer prabhagId, int page, int size);
	    
		public List<VotersDetailsDto> getVotersByVillageName(String villageName) ;
		
		public Message<Page<VotersDetailsDto>> getAllVoterDetails(int page, int size)  ;
}
