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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (headIdentificationDocumentID ^ (headIdentificationDocumentID >>> 32));
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + (int) (identificationDocumentID ^ (identificationDocumentID >>> 32));
		result = prime * result + ((issuer == null) ? 0 : issuer.hashCode());
		result = prime * result
				+ (int) (nextLinkedIdentificationDocumentID ^ (nextLinkedIdentificationDocumentID >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IdentificationDocument other = (IdentificationDocument) obj;
		if (headIdentificationDocumentID != other.headIdentificationDocumentID)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (identificationDocumentID != other.identificationDocumentID)
			return false;
		if (issuer == null) {
			if (other.issuer != null)
				return false;
		} else if (!issuer.equals(other.issuer))
			return false;
		if (nextLinkedIdentificationDocumentID != other.nextLinkedIdentificationDocumentID)
			return false;
		return true;
	}
}
