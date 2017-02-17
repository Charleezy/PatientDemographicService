package hello;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
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
	
	private String firstname;
	private String lastname;
	private String dob;
	private String address;

	public DemographicInfo(){
	}
	
//	public DemographicInfo(String issuer, String id){
//	}
	
	public long getDemographicID() {
		return demographicID;
	}

	public void setDemographicID(long demographicID) {
		this.demographicID = demographicID;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
