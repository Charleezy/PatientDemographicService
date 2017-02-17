package hello;

import java.util.List;
import java.util.stream.Collectors;

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
    public ResponseEntity addDemographic(@RequestParam(value="firstName", required=true) String firstName, @RequestParam(value="lastName", required=true) String lastName, 
    		@RequestParam(value="dob", required=true) String dob, @RequestParam(value="address", required=true) String address) {
        long demographicID = demoService.addDemographic(new DemographicInfo(firstName, lastName, dob, address));
		ResponseEntity response = ResponseEntity.status(HttpStatus.CREATED).header("Location", "/getDemographic/" + demographicID).build();
		return response;
    }
	
	@RequestMapping("/linkDocument")
    public ResponseEntity addDemographic(@RequestParam(value="demographicID", required=true) long demographicID, @RequestParam(value="documentID", required=true) long documentID) {
		demoService.linkDemographicInfo(demographicID, documentID);
		ResponseEntity response = ResponseEntity.status(HttpStatus.OK).body("demographic " + demographicID + " linked to document " + documentID);
		return response;
    }
}
