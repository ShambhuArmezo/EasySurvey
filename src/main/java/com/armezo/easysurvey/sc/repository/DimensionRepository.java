package com.armezo.easysurvey.sc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.armezo.easysurvey.sc.model.DimensionMaster;

@Repository
public interface DimensionRepository extends JpaRepository<DimensionMaster, Long> {
	
	@Query("SELECT s FROM DimensionMaster s WHERE s.dimension=:dimention")
	List<DimensionMaster> getDimension(String dimention);
	
	@Query("SELECT s FROM DimensionMaster s WHERE s.dimensionid=:dimensionid")
	List<DimensionMaster> getDimensionName(Long dimensionid);
	
	@Query("SELECT s FROM DimensionMaster s WHERE s.surveyId=:surveyId")
	List<DimensionMaster> getAllDimension(Long surveyId);

}
