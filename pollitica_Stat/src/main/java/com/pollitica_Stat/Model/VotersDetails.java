package com.pollitica_Stat.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@Entity
@Data
@ToString
@Accessors(chain = true)
public class VotersDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String voterId;
	private String voterEnglishName;
	private String votersFatherEnglishName;
	private String voterMarathiName;
	private String votersFatherMarathiName;
	private String age;
	private String houseNo;
	private String gender;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prabhag_id", referencedColumnName = "prabhagId")
    private Prabhag prabhag;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "village_id")
	private Village village;

}
