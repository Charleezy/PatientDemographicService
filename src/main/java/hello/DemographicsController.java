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
        long demographicID = demoService.addDemographic(new DemographicInfo());
		ResponseEntity response = ResponseEntity.status(HttpStatus.CREATED).header("Location", "/getDemographic/" + demographicID).build();
		return response;
    }
}
