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

	public UserMaster(String emailid) {
		this.emailId = emailid;
	}

	public String getEmailId() {
		return emailId;
	}

	public double getLikelihood() {
		return likelihood;
	}

	public void setLikelihood(double likelihood) {
		this.likelihood = likelihood;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getContactInfo_familyName() {
		return contactInfo_familyName;
	}

	public void setContactInfo_familyName(String contactInfo_familyName) {
		this.contactInfo_familyName = contactInfo_familyName;
	}

	public String getContactInfo_fullName() {
		return contactInfo_fullName;
	}

	public void setContactInfo_fullName(String contactInfo_fullName) {
		this.contactInfo_fullName = contactInfo_fullName;
	}

	public String getContactInfo_givenName() {
		return contactInfo_givenName;
	}

	public void setContactInfo_givenName(String contactInfo_givenName) {
		this.contactInfo_givenName = contactInfo_givenName;
	}

	public Demographic getDemographic() {
		return demographic;
	}

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
	private Demographic demographic;
}
