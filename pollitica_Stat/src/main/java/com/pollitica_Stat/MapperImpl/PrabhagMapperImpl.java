package com.pollitica_Stat.MapperImpl;



import org.springframework.stereotype.Component;

import com.pollitica_Stat.Dto.PrabhagRequestDto;
import com.pollitica_Stat.Mapper.PrabhagMapper;
import com.pollitica_Stat.Model.Prabhag;


@Component
public class PrabhagMapperImpl implements PrabhagMapper {

	 public Prabhag toPrabhag(PrabhagRequestDto dto) {
	        Prabhag prabhag = new Prabhag();
	        prabhag.setId(dto.getId());
	        prabhag.setPrabhagId(dto.getPrabhagId());
	        prabhag.setName(dto.getName());
	        prabhag.setJilhaName(dto.getJilhaName());
	        return prabhag;
	    }

	    public PrabhagRequestDto toPrabhagResponseDto(Prabhag prabhag) {
	        PrabhagRequestDto dto = new PrabhagRequestDto();
	        dto.setId(prabhag.getId());
	        dto.setPrabhagId(prabhag.getPrabhagId());
	        dto.setName(prabhag.getName());
	        dto.setJilhaName(prabhag.getJilhaName());

	        return dto;
	    }
	}

