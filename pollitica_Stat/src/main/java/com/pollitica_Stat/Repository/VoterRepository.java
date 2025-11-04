package com.pollitica_Stat.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pollitica_Stat.Dto.VotersDetailsDto;
import com.pollitica_Stat.Model.Prabhag;
import com.pollitica_Stat.Model.Village;
import com.pollitica_Stat.Model.VotersDetails;

@Repository
public interface VoterRepository extends JpaRepository<VotersDetails, Integer> {

	boolean existsByVoterId(String voterId);
	Page<VotersDetails> findByVoterEnglishNameStartingWithIgnoreCaseOrVoterMarathiNameStartingWith(
	        String englishSurname,
	        String marathiSurname,
	        Pageable pageable
	);

	    // Search by full name / contains
	    Page<VotersDetails> findByVoterEnglishNameContainingIgnoreCaseOrVoterMarathiNameContainingIgnoreCase(
	            String voterEnglishName, String VoterMarathiName, Pageable pageable);

    Page<VotersDetails> findByAge(String age, Pageable pageable);

    Page<VotersDetails> findByGender(String gender, Pageable pageable);

    @Query("SELECT v FROM VotersDetails v WHERE v.prabhag.prabhagId = :prabhagId")
    Page<VotersDetails> findByPrabhagId(@Param("prabhagId") Integer prabhagId, Pageable pageable);

	 List<VotersDetails> findByPrabhag(Prabhag prabhag);

}
