package com.pollitica_Stat.Service;

import com.pollitica_Stat.Dto.Message;
import com.pollitica_Stat.Dto.VillageRequestDto;

public interface VillageService {
	public Message<VillageRequestDto>addVillage(VillageRequestDto request);

}
