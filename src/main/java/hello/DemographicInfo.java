package hello;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class DemographicInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "MEDIUMINT NOT NULL AUTO_INCREMENT")
	private long demographicID;
	
	private String firstname;
	private String lastname;
	private String dob;
	private String address;
	
	private long identificationDocument;

	public DemographicInfo(){
	}
	
	public DemographicInfo(String firstName, String lastName, String dob, String address) {
		this.firstname = firstName;
		this.lastname = lastName;
		this.dob = dob;
		this.address = address;
	}

	public long getDemographicID() {
		return demographicID;
	}

	/**
	 * For tests
	 */
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

	public long getIdentificationDocument() {
		return identificationDocument;
	}
	
	public void setIdentificationDocument(long identificationDocument) {
		this.identificationDocument = identificationDocument;
	}
}
