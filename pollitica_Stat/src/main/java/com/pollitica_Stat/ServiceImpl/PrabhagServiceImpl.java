package com.pollitica_Stat.ServiceImpl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.pollitica_Stat.Dto.Message;
import com.pollitica_Stat.Dto.PrabhagRequestDto;
import com.pollitica_Stat.Mapper.PrabhagMapper;
import com.pollitica_Stat.Model.Prabhag;
import com.pollitica_Stat.Model.Village;
import com.pollitica_Stat.Repository.PrabhagRepository;
import com.pollitica_Stat.Repository.VillageRepository;
import com.pollitica_Stat.Service.PrabhagService;
import com.pollitica_Stat.Util.Constants;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class PrabhagServiceImpl implements PrabhagService {
	private final PrabhagMapper prabhagMapper;
	private final PrabhagRepository prabhagRepository;
	private final VillageRepository villageRepository;

	@Override
	public Message<PrabhagRequestDto> addPrabhag(PrabhagRequestDto request) {
	Message<PrabhagRequestDto> message = new Message<>();
	try {
		Prabhag prabhag=prabhagRepository.findByPrabhagId(request.getPrabhagId());
		if(prabhag==null) {
			prabhag=prabhagMapper.toPrabhag(request);
			prabhagRepository.save(prabhag);
			
			Village village = new Village();
			village.setPrabhag(prabhag);
			villageRepository.save(village);
			message.setStatus(HttpStatus.OK);
			message.setResponseMessage(Constants.PRABHAG_ADDED_SUCCESSFULLY);
			message.setData(prabhagMapper.toPrabhagResponseDto(prabhag));
			return message;
		}else {
			message.setStatus(HttpStatus.OK);
			message.setResponseMessage(Constants.PRABHAG_ALREADY_EXISTS);
			message.setData(prabhagMapper.toPrabhagResponseDto(prabhag));
			return message;
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		log.error(e.getMessage());
		String error = e.getMessage();
		message.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		message.setResponseMessage(error);
		return message;
	}
	}

	@Override
	public Message<PrabhagRequestDto> updatePrabhag(PrabhagRequestDto request) {
		Message<PrabhagRequestDto> message = new Message<>();
		Prabhag prabhag=prabhagRepository.findByPrabhagId(request.getPrabhagId());
		try {
		if(prabhag!=null) {
			prabhag=prabhagMapper.toPrabhag(request);
			prabhagRepository.save(prabhag);
			message.setStatus(HttpStatus.OK);
			message.setResponseMessage(Constants.PRABHAG_UPDATED_SUCCESSFULLY);
			message.setData(prabhagMapper.toPrabhagResponseDto(prabhag));
			return message;
		}else {
			message.setStatus(HttpStatus.OK);
			message.setResponseMessage(Constants.PRABHAG_NOT_FOUND);
			return message;
		}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
			String error = e.getMessage();
			message.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			message.setResponseMessage(error);
			return message;
		}
	}

	@Override
	public Message<PrabhagRequestDto> deletePrabhag(int id) {
		Message<PrabhagRequestDto> message = new Message<>();
		Prabhag prabhag=prabhagRepository.findById(id).orElse(null);
		try {
		if(prabhag!=null) {
			prabhagRepository.delete(prabhag);
			message.setStatus(HttpStatus.OK);
			message.setResponseMessage(Constants.PRABHAG_DELETED_SUCCESSFULLY);
			return message;
		}else {
			message.setStatus(HttpStatus.OK);
			message.setResponseMessage(Constants.PRABHAG_NOT_FOUND);
			return message;
		}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
			String error = e.getMessage();
			message.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			message.setResponseMessage(error);
			return message;
		}
	}

	@Override
	public Message<PrabhagRequestDto> getPrabhagByPrabhagId(int prabhagId) {
		Message<PrabhagRequestDto> message = new Message<>();
		Prabhag prabhag=prabhagRepository.findByPrabhagId(prabhagId);
		try {
			if(prabhag!=null) {
				message.setStatus(HttpStatus.OK);
				message.setResponseMessage(Constants.PRABHAG_FOUND_SUCCESSFULLY);
				message.setData(prabhagMapper.toPrabhagResponseDto(prabhag));
				return message;
			}else {
				message.setStatus(HttpStatus.OK);
				message.setResponseMessage(Constants.PRABHAG_NOT_FOUND);
				return message;
			}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
			String error = e.getMessage();
			message.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			message.setResponseMessage(error);
			return message;
		}
	}

	@Override
	public Message<PrabhagRequestDto> getPrabhagbyId(int id) {
		Message<PrabhagRequestDto> message = new Message<>();
		Prabhag prabhag=prabhagRepository.findById(id).orElse(null);
		try {
			if(prabhag!=null) {
				message.setStatus(HttpStatus.OK);
				message.setResponseMessage(Constants.PRABHAG_FOUND_SUCCESSFULLY);
				message.setData(prabhagMapper.toPrabhagResponseDto(prabhag));
				return message;
			}else {
				message.setStatus(HttpStatus.OK);
				message.setResponseMessage(Constants.PRABHAG_NOT_FOUND);
				return message;
			}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
			String error = e.getMessage();
			message.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			message.setResponseMessage(error);
			return message;
		}
	}

}
