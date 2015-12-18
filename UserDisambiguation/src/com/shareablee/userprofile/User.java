/**
 * 
 */
package com.shareablee.userprofile;

import java.util.HashSet;
import java.util.Set;

import com.shareablee.utils.Gender;

/**
 * This class is used to store selected information about the user.
 * 
 * @author ravidugar
 */
public class User {

	/**
	 * Method that returns the email id of the user
	 * 
	 * @return emailId
	 */
	public String getEmailId() {
		return emailId;
	}

	/**
	 * Method to set the email id
	 * 
	 * @param emailId
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	/**
	 * Method to return the family name
	 * 
	 * @return Family name
	 */
	public String getContactInfo_familyName() {
		return contactInfo_familyName;
	}

	/**
	 * Method to set the family name
	 * 
	 * @param contactInfo_familyName
	 */
	public void setContactInfo_familyName(String contactInfo_familyName) {
		this.contactInfo_familyName = contactInfo_familyName;
	}

	/**
	 * Method to return the full name
	 * 
	 * @return Full name
	 */
	public String getContactInfo_fullName() {
		return contactInfo_fullName;
	}

	/**
	 * Method to set the full name
	 * 
	 * @param contactInfo_fullName
	 */
	public void setContactInfo_fullName(String contactInfo_fullName) {
		this.contactInfo_fullName = contactInfo_fullName;
	}

	/**
	 * Method to return the given name of user
	 * 
	 * @return Given name
	 */
	public String getContactInfo_givenName() {
		return contactInfo_givenName;
	}

	/**
	 * Method to set the given name
	 * 
	 * @param contactInfo_givenName
	 */
	public void setContactInfo_givenName(String contactInfo_givenName) {
		this.contactInfo_givenName = contactInfo_givenName;
	}

	/**
	 * Method that returns the gender
	 * 
	 * @return gender
	 */
	public Gender getDemographics_gender() {
		return demographics_gender;
	}

	/**
	 * Method to set the gender
	 * 
	 * @param demographics_gender
	 */
	public void setDemographics_gender(Gender demographics_gender) {
		this.demographics_gender = demographics_gender;
	}

	/**
	 * Method that return the location of the user
	 * 
	 * @return Collection of location strings
	 */
	public Set<String> getLocation() {
		return location;
	}

	/**
	 * Method to set the location of the user
	 * 
	 * @param location
	 */
	public void setLocation(Set<String> location) {
		this.location = location;
	}

	/**
	 * 
	 * @return similarity score
	 */
	public double getSimilarityScore() {
		return similarityScore;
	}

	/**
	 * 
	 * @param similarityScore
	 */
	public void setSimilarityScore(double similarityScore) {
		this.similarityScore = similarityScore;
	}

	@Override
	public String toString() {
		String retVal = "";
		retVal += this.emailId + ", " + this.getContactInfo_fullName() + ", "
				+ this.getContactInfo_givenName() + ", "
				+ this.getContactInfo_familyName() + ", "
				+ this.demographics_gender;
		return retVal;
	}

	private String emailId;
	private String contactInfo_familyName;
	private String contactInfo_fullName;
	private String contactInfo_givenName;
	private Gender demographics_gender;
	private Set<String> location = new HashSet<>();

	private double similarityScore;
}
