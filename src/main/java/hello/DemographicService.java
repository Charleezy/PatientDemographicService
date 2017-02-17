package hello;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DemographicService {

	@Autowired
	DemographicRepository demoRepository;
	
	@Autowired
	IdentificationDocumentRepository idRepository;
	
	public long addDemographic(DemographicInfo demographicInfo){
		DemographicInfo demographicInfoSaved = demoRepository.save(demographicInfo);
		return demographicInfoSaved.getDemographicID();
	}

	public void linkDemographicInfo(long demographicID, long identificationDocumentID) {
		DemographicInfo demoInfo = demoRepository.findOne(demographicID);
		IdentificationDocument id = idRepository.findOne(identificationDocumentID);
		demoInfo.setIdentificationDocument(id.getHeadIdentificationDocumentID());
		demoRepository.save(demoInfo);
	}
}
