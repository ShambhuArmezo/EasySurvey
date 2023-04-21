package com.armezo.easysurvey.sc.service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.armezo.easysurvey.sc.model.DimensionMaster;
import com.armezo.easysurvey.sc.model.SurveyMaster;
import com.armezo.easysurvey.sc.repository.DimensionRepository;

@Service
public class DimensionService {
	
	@Autowired
	private DimensionRepository dimensionRepository;
	
	 public DimensionMaster dimensionAdd(DimensionMaster entity) throws Exception
	    {
		 	dimensionRepository.save(entity);
	        return entity;
	    }
	 
	 public Optional<DimensionMaster> getDimensionById(Long dimansionId) {
			return dimensionRepository.findById(dimansionId);
		}
	 
	 public List<DimensionMaster> getDimension(String dimention) {
			return dimensionRepository.getDimension(dimention);
		}
	 
	 public List<DimensionMaster> getDimensionName(Long dimansionId) {
			return dimensionRepository.getDimensionName(dimansionId);
		}
	 
	 public List<DimensionMaster> getAllDimension(Long surveyId) {
			return dimensionRepository.getAllDimension(surveyId);
		}

		public void deleteDimensionById(Long dimensionid) {
			dimensionRepository.deleteById(dimensionid);
		}

}
