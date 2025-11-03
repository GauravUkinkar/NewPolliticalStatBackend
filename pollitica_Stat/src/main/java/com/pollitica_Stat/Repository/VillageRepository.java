package com.pollitica_Stat.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pollitica_Stat.Model.Village;

@Repository
public interface VillageRepository extends JpaRepository<Village, Integer> {

	Village findByVillageName(String villageName);

}
