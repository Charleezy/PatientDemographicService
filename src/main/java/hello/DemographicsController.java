package hello;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.HeadersBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DemographicsController {
	
	@Autowired
	DemographicService demoService;
	
	@RequestMapping("/addDemographic")
    public ResponseEntity addDemographic(@RequestParam(value="documentID", required=true) long documentID, @RequestParam(value="firstName", required=true) String firstName, @RequestParam(value="lastName", required=true) String lastName, 
    		@RequestParam(value="dob", required=true) String dob, @RequestParam(value="address", required=true) String address) {
        long demographicID = demoService.addDemographic(documentID, new DemographicInfo(firstName, lastName, dob, address));
        return ResponseEntity.status(HttpStatus.CREATED).header("Location", "/getDemographic/" + demographicID).build();
    }
	
	@RequestMapping("/updateDocument")
    public ResponseEntity updateDocument(@RequestParam(value="documentID", required=true) long documentID, @RequestParam(value="firstName", required=true) String firstName, @RequestParam(value="lastName", required=true) String lastName, 
    		@RequestParam(value="dob", required=true) String dob, @RequestParam(value="address", required=true) String address) throws Exception {
		long demographicID = demoService.updateDemographicInfo(documentID, new DemographicInfo(firstName, lastName, dob, address));
		return ResponseEntity.status(HttpStatus.OK).header("Location", "/getDemographic/" + demographicID).build();
    }
	
	@RequestMapping("/getDocumentsByDemographic")
    public ResponseEntity getDocumentsByDemographic(@RequestParam(value="demographicID", required=true) long demographicID) {
		List<IdentificationDocument> identificationDocumentsList = demoService.getDocumentsByDemographic(demographicID);
		String identificationDocumentsString = identificationDocumentsList.stream().map(ld -> "issuer: " + ld.getIssuer() + " ID: " + ld.getID()).collect(Collectors.joining(", "));
		return ResponseEntity.status(HttpStatus.OK).body("patient documents: " + identificationDocumentsString);
    }
	
	@RequestMapping("/queryPatients")
    public ResponseEntity queryPatients(@RequestParam(value="parameter", required=true) String parameter, @RequestParam(value="value", required=true) String value) throws Exception {
		Map<IdentificationDocument, DemographicInfo> identificationDocumentsList = demoService.getDocumentsByParameter(parameter, value);
		Set<IdentificationDocument> identificationDocuments = identificationDocumentsList.keySet();
		JSONArray responseJSON = new JSONArray();
		for(IdentificationDocument id : identificationDocuments){
			JSONObject row = new JSONObject();
			row.put("issuer", id.getIssuer());
			row.put("id", id.getID());
			DemographicInfo demoInfo = identificationDocumentsList.get(id);
			row.put("firstName", demoInfo.getFirstName());
			row.put("lastName", demoInfo.getLastName());
			row.put("dob", demoInfo.getDob());
			row.put("address", demoInfo.getAddress());
			responseJSON.put(row);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(responseJSON.toString());
    }
	
}
