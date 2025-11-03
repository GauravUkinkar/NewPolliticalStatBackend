package com.pollitica_Stat.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pollitica_Stat.Model.VotersDetails;

@Repository
public interface VoterRepository extends JpaRepository<VotersDetails, Integer> {

	boolean existsByVoterId(String voterId);

}
