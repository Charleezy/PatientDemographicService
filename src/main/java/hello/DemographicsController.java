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
	DemographicService idService;
	
	@RequestMapping("/addDemographic")
    public ResponseEntity addDemographic(@RequestParam(value="issuer", required=true) String issuer, @RequestParam(value="id", required=true) String id) {
        long demographicID = idService.addDemographic(new DemographicInfo());
		ResponseEntity response = ResponseEntity.status(HttpStatus.CREATED).header("Location", "/getDemographic/" + demographicID).build();
		return response;
    }
}
