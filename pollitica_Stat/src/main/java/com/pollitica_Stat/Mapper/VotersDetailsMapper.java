package com.pollitica_Stat.Mapper;

import com.pollitica_Stat.Dto.VotersDetailsDto;
import com.pollitica_Stat.Model.VotersDetails;

public interface VotersDetailsMapper {
	public VotersDetailsDto VotersDetailstoVoterDetailsDto(VotersDetails votersDetails);
	public VotersDetails voterDetailsDtoToVotersDetails(VotersDetailsDto dto);

}
