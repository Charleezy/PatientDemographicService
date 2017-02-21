package hello;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DemographicService {

	@Autowired
	DemographicRepository demoRepository;
	
	@Autowired
	IdentificationDocumentRepository idRepository;
	
	public long addDemographic(long documentID, DemographicInfo demographicInfo){
		IdentificationDocument identificationDocument = idRepository.findOne(documentID);
		demographicInfo.setIdentificationDocument(identificationDocument.getHeadIdentificationDocumentID());
		DemographicInfo demographicInfoSaved = demoRepository.save(demographicInfo);
		return demographicInfoSaved.getDemographicID();
	}

	public long updateDemographicInfo(long documentID, DemographicInfo newDemographicInfo) throws Exception {
		IdentificationDocument id = idRepository.findOne(documentID);
		DemographicInfo demoInfo = demoRepository.findByIdentificationDocument(id.getHeadIdentificationDocumentID());
		if(demoInfo == null){
			throw new Exception("no demographic info matching that identificationDocument");
		}
		demoInfo.setFirstName(newDemographicInfo.getFirstName());
		demoInfo.setLastname(newDemographicInfo.getLastName());
		demoInfo.setDob(newDemographicInfo.getDob());
		demoInfo.setAddress(newDemographicInfo.getAddress());
		demoRepository.save(demoInfo);
		return demoInfo.getIdentificationDocument();
	}

	public List<IdentificationDocument> getDocumentsByDemographic(long demoID) {
		DemographicInfo demoInfo = demoRepository.findOne(demoID);
		
		List<IdentificationDocument> identificationDocuments = new ArrayList<IdentificationDocument>();
		
		if(demoInfo.getIdentificationDocument() != 0){
			IdentificationDocument curID = idRepository.findOne(demoInfo.getIdentificationDocument());
			while(curID.getNextLinkedIdentificationDocumentID() != 0){
				identificationDocuments.add(curID);
				curID = idRepository.findOne(curID.getNextLinkedIdentificationDocumentID());
			}
			identificationDocuments.add(curID);
		}
		
		return identificationDocuments;
	}

	public Map<IdentificationDocument, DemographicInfo> getDocumentsByParameter(String parameter, String value) throws Exception {
		List<DemographicInfo> demographicInfoList = new ArrayList<DemographicInfo>();
		Map<IdentificationDocument, DemographicInfo> result = new HashMap<IdentificationDocument, DemographicInfo>();
		
		switch(parameter){
			case "firstName":
				demographicInfoList = demoRepository.findByFirstName(value);
				break;
			case "lastName":
			case "dob":
			case "address":
			default :
				throw new Exception("search parameter must be one of firstName, lastName, dob, address");
		}
		
//		List<IdentificationDocument> identificationDocumentList = demographicInfoList.stream().map(patient -> idRepository.findOne(patient.getIdentificationDocument())).collect(Collectors.toList());
		
		for(DemographicInfo di : demographicInfoList){
			result.put(idRepository.findOne(di.getIdentificationDocument()), di);
		}
		return result;
	}
}
