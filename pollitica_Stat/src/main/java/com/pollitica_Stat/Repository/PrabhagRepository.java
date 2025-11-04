package com.pollitica_Stat.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pollitica_Stat.Model.Prabhag;


@Repository
public interface PrabhagRepository extends JpaRepository<Prabhag, Integer> {

	Prabhag findByPrabhagId(int prabhagId);



}
