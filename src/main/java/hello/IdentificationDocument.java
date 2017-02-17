package hello;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class IdentificationDocument {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long identificationDocumentID;

	private String issuer;
	private String id;
	private long nextLinkedIdentificationDocumentID;
	private long headIdentificationDocumentID;

	public IdentificationDocument(){
	}
	
	public IdentificationDocument(String issuer, String id){
		this.issuer = issuer;
		this.id = id;
	}

	/**
	 * This ID is the unique identifier in the database
	 */
	public long getIdentificationDocumentID(){
		return identificationDocumentID;
	}
	
	/**
	 * Strictly for tests
	 */
	public void setIdentificationDocumentID(long identificationDocumentID) {
		this.identificationDocumentID = identificationDocumentID;
	}
	
	public String getIssuer() {
		return issuer;
	}

	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}

	/**
	 * This is something like a passport number
	 */
	public String getID() {
		return id;
	}

	public void setID(String id) {
		this.id = id;
	}

	public long getNextLinkedIdentificationDocumentID() {
		return nextLinkedIdentificationDocumentID;
	}

	public void setNextLinkedIdentificationDocumentID(long nextLinkedIdentificationDocumentID) {
		this.nextLinkedIdentificationDocumentID = nextLinkedIdentificationDocumentID;
	}
	
	public long getHeadIdentificationDocumentID() {
		return headIdentificationDocumentID;
	}

	public void setHeadIdentificationDocumentID(long headIdentificationDocumentID) {
		this.headIdentificationDocumentID = headIdentificationDocumentID;
	}
}
