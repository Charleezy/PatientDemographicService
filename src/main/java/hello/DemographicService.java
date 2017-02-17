package hello;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DemographicService {

	@Autowired
	DemographicRepository idRepository;
	
	public long addDemographic(DemographicInfo demographicInfo){
		DemographicInfo demographicInfoSaved = idRepository.save(demographicInfo);
		return 0;
	}
}
