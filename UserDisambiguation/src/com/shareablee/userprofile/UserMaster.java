/**
 * 
 */
package com.shareablee.userprofile;

import com.shareablee.utils.Demographic;

/**
 * This class is used to store entire information provided for a particular user
 * 
 * @author ravidugar
 */
public class UserMaster {

	/**
	 * 
	 * @param emailid
	 */
	public UserMaster(String emailid) {
		this.emailId = emailid;
	}

	/**
	 * 
	 * @return EmaiId
	 */
	public String getEmailId() {
		return emailId;
	}

	/**
	 * 
	 * @return Likelihood
	 */
	public double getLikelihood() {
		return likelihood;
	}

	/**
	 * 
	 * @param likelihood
	 */
	public void setLikelihood(double likelihood) {
		this.likelihood = likelihood;
	}

	/**
	 * 
	 * @return RequestId
	 */
	public String getRequestId() {
		return requestId;
	}

	/**
	 * 
	 * @param requestId
	 */
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	/**
	 * 
	 * @return Status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * 
	 * @param status
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * 
	 * @return Family name
	 */
	public String getContactInfo_familyName() {
		return contactInfo_familyName;
	}

	/**
	 * 
	 * @param contactInfo_familyName
	 */
	public void setContactInfo_familyName(String contactInfo_familyName) {
		this.contactInfo_familyName = contactInfo_familyName;
	}

	/**
	 * 
	 * @return Full name
	 */
	public String getContactInfo_fullName() {
		return contactInfo_fullName;
	}

	/**
	 * 
	 * @param contactInfo_fullName
	 */
	public void setContactInfo_fullName(String contactInfo_fullName) {
		this.contactInfo_fullName = contactInfo_fullName;
	}

	/**
	 * 
	 * @return Given name
	 */
	public String getContactInfo_givenName() {
		return contactInfo_givenName;
	}

	/**
	 * 
	 * @param contactInfo_givenName
	 */
	public void setContactInfo_givenName(String contactInfo_givenName) {
		this.contactInfo_givenName = contactInfo_givenName;
	}

	/**
	 * 
	 * @return Demographic
	 */
	public Demographic getDemographic() {
		return demographic;
	}

	/**
	 * 
	 * @param demographic
	 */
	public void setDemographic(Demographic demographic) {
		this.demographic = demographic;
	}

	private String emailId;
	private double likelihood;
	private String requestId;
	private int status;
	private String contactInfo_familyName = "";
	private String contactInfo_fullName = "";
	private String contactInfo_givenName = "";
	private Demographic demographic = new Demographic();
}
