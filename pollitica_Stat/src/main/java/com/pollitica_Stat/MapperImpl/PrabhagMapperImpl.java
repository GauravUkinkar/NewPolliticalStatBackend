package com.pollitica_Stat.MapperImpl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.pollitica_Stat.Dto.PrabhagRequestDto;
import com.pollitica_Stat.Mapper.PrabhagMapper;
import com.pollitica_Stat.Model.Prabhag;
import com.pollitica_Stat.Model.Village;

@Component
public class PrabhagMapperImpl implements PrabhagMapper {

	 public Prabhag toPrabhag(PrabhagRequestDto dto) {
	        Prabhag prabhag = new Prabhag();
	        prabhag.setId(dto.getId());
	        prabhag.setPrabhagId(dto.getPrabhagId());
	        prabhag.setName(dto.getName());
	        prabhag.setJilhaName(dto.getJilhaName());

	        if (dto.getVillageNames() != null && !dto.getVillageNames().isEmpty()) {
	            List<Village> villages = dto.getVillageNames().stream().map(name -> {
	                Village v = new Village();
	                v.setVillageName(name);
	                return v;
	            }).toList();
	            prabhag.setVillages(villages);
	        }
	        return prabhag;
	    }

	    public PrabhagRequestDto toPrabhagResponseDto(Prabhag prabhag) {
	        PrabhagRequestDto dto = new PrabhagRequestDto();
	        dto.setId(prabhag.getId());
	        dto.setPrabhagId(prabhag.getPrabhagId());
	        dto.setName(prabhag.getName());
	        dto.setJilhaName(prabhag.getJilhaName());

	        dto.setVillageNames(
	           prabhag.getVillages().stream()
	                   .map(Village::getVillageName)
	                   .toList()
	        );

	        return dto;
	    }
	}

