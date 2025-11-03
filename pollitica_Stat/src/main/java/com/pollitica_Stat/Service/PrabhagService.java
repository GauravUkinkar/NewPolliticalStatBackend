package com.pollitica_Stat.Service;

import com.pollitica_Stat.Dto.Message;
import com.pollitica_Stat.Dto.PrabhagRequestDto;

public interface PrabhagService {
	
	public Message<PrabhagRequestDto>addPrabhag(PrabhagRequestDto request);
	public Message<PrabhagRequestDto>updatePrabhag(PrabhagRequestDto request);
	public Message<PrabhagRequestDto>deletePrabhag(int id);
	public Message<PrabhagRequestDto>getPrabhagByPrabhagId(int prabhagId);
	public Message<PrabhagRequestDto>getPrabhagbyId(int id);

}
