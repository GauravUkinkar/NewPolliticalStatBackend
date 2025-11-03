package com.pollitica_Stat.Dto;

import java.util.List;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@ToString
@Accessors(chain = true)
public class PrabhagRequestDto {
	private int id;
	 private int prabhagId;
	    private String name;
	    private List<String> villageNames;
	    private String jilhaName;
}
