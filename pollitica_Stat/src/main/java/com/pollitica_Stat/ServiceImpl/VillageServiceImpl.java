package com.pollitica_Stat.ServiceImpl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.pollitica_Stat.Dto.Message;
import com.pollitica_Stat.Dto.VillageRequestDto;
import com.pollitica_Stat.Model.Village;
import com.pollitica_Stat.Repository.VillageRepository;
import com.pollitica_Stat.Service.VillageService;
import com.pollitica_Stat.Util.Constants;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class VillageServiceImpl implements VillageService {
private final VillageRepository prabhagRepository;
	@Override
	public Message<VillageRequestDto> addVillage(VillageRequestDto request) {
		Message<VillageRequestDto> message = new Message<>();
		try {
			Village village=prabhagRepository.findByVillageName(request.getVillageName());
			if(village==null) {
				village=new Village();
				village.setVillageName(request.getVillageName());
				prabhagRepository.save(village);
				message.setStatus(HttpStatus.OK);
				message.setResponseMessage(Constants.VILLAGE_ADDED_SUCCESSFULLY);
				VillageRequestDto response=new VillageRequestDto();
				response.setVillageName(village.getVillageName());
				message.setData(response);
				return message;
				
			}else {
				message.setStatus(HttpStatus.OK);
				message.setResponseMessage(Constants.VILLAGE_ALREADY_EXISTS);
				return message;
			}
		}
			catch (Exception e) {
				// TODO Auto-generated catch block
				log.error(e.getMessage());
				String error = e.getMessage();
				message.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
				message.setResponseMessage(error);
				return message;
			}
		}
}


