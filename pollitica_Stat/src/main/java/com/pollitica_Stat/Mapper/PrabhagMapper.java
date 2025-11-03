package com.pollitica_Stat.Mapper;

import com.pollitica_Stat.Dto.PrabhagRequestDto;
import com.pollitica_Stat.Model.Prabhag;

public interface PrabhagMapper {
	public Prabhag toPrabhag(PrabhagRequestDto prabhagRequestDto);
	public PrabhagRequestDto toPrabhagResponseDto(Prabhag prabhag);

}
