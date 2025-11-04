package com.pollitica_Stat.Model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@Entity
@Data
@ToString
@Accessors(chain = true)
public class Prabhag {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false, unique = true)
	private int prabhagId;
	private String name;
	private String villageName;
	private String jilhaName;
	
	@OneToMany(mappedBy = "prabhag", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<VotersDetails> voters = new ArrayList<>();



	 

}
