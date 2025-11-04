package com.pollitica_Stat.MapperImpl;

import org.springframework.stereotype.Component;

import com.pollitica_Stat.Dto.VotersDetailsDto;
import com.pollitica_Stat.Mapper.VotersDetailsMapper;
import com.pollitica_Stat.Model.VotersDetails;

@Component

public class VotersDetailsMapperImpl implements VotersDetailsMapper {

	@Override
	public VotersDetailsDto VotersDetailstoVoterDetailsDto(VotersDetails votersDetails) {
		return new VotersDetailsDto()
				.setAge(votersDetails.getAge())
				.setGender(votersDetails.getGender())
				.setHouseNo(votersDetails.getHouseNo())
				.setId(votersDetails.getId())
				.setPrabhagId(votersDetails.getId())
				.setVoterEnglishName(votersDetails.getVoterEnglishName())
				.setVoterId(votersDetails.getVoterId())
				.setVoterMarathiName(votersDetails.getVoterMarathiName())
				.setVotersFatherEnglishName(votersDetails.getVotersFatherEnglishName())
				.setVotersFatherMarathiName(votersDetails.getVotersFatherMarathiName());
	}

	@Override
	public VotersDetails voterDetailsDtoToVotersDetails(VotersDetailsDto dto) {
		return new VotersDetails()
				.setAge(dto.getAge())
				.setGender(dto.getGender())
				.setHouseNo(dto.getHouseNo())
				.setId(dto.getId())
				.setVoterEnglishName(dto.getVoterEnglishName())
				.setVoterId(dto.getVoterId())
				.setVoterMarathiName(dto.getVoterMarathiName())
				.setVotersFatherEnglishName(dto.getVotersFatherEnglishName())
				.setVotersFatherMarathiName(dto.getVotersFatherMarathiName());
	}

}
