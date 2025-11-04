package com.pollitica_Stat.Dto;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@ToString
@Accessors(chain = true)
public class VotersDetailsDto {
	private int id;
	private String voterId;
	private String voterEnglishName;
	private String votersFatherEnglishName;
	private String voterMarathiName;
	private String votersFatherMarathiName;
	private String age;
	private String houseNo;
	private String gender;
	private Integer prabhagId; 
	private String villageName;

}
