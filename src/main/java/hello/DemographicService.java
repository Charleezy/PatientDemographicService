package hello;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DemographicService {

	@Autowired
	DemographicRepository demoRepository;
	
	public long addDemographic(DemographicInfo demographicInfo){
		DemographicInfo demographicInfoSaved = demoRepository.save(demographicInfo);
		return demographicInfoSaved.getDemographicID();
	}
}
