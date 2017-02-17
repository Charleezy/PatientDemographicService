package hello;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class DemographicInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long demographicID;

	public DemographicInfo(){
	}
	
//	public DemographicInfo(String issuer, String id){
//	}
}
