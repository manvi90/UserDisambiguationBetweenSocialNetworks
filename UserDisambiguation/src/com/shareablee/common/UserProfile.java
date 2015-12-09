/**
 * 
 */
package com.shareablee.common;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * @author ravidugar
 *
 */
public class UserProfile {
	
	/**
	 * Method that returns the email id of the user
	 * @return
	 */
	public String getEmailId() {
		return emailId;
	}
	
	/**
	 * Method to set the email id
	 * @param emailId
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	/**
	 * Method to return the family name
	 * @return
	 */
	public String getContactInfo_familyName() {
		return contactInfo_familyName;
	}
	
	/**
	 * Method to set the family name
	 * @param contactInfo_familyName
	 */
	public void setContactInfo_familyName(String contactInfo_familyName) {
		this.contactInfo_familyName = contactInfo_familyName;
	}
	
	/**
	 * Method to return the full name
	 * @return
	 */
	public String getContactInfo_fullName() {
		return contactInfo_fullName;
	}
	
	/**
	 * Method to set the full name
	 * @param contactInfo_fullName
	 */
	public void setContactInfo_fullName(String contactInfo_fullName) {
		this.contactInfo_fullName = contactInfo_fullName;
	}
	
	/**
	 * Method to return the given name of user
	 * @return
	 */
	public String getContactInfo_givenName() {
		return contactInfo_givenName;
	}
	
	/**
	 * Method to set the given name
	 * @param contactInfo_givenName
	 */
	public void setContactInfo_givenName(String contactInfo_givenName) {
		this.contactInfo_givenName = contactInfo_givenName;
	}
	
	/**
	 * Method that returns the gender
	 * @return
	 */
	public Gender getDemographics_gender() {
		return demographics_gender;
	}
	
	/**
	 * Method to set the gender
	 * @param demographics_gender
	 */
	public void setDemographics_gender(Gender demographics_gender) {
		this.demographics_gender = demographics_gender;
	}
	
	/**
	 * Method that return the location of the user
	 * @return
	 */
	public Set<String> getLocation() {
		return location;
	}
	
	/**
	 * Method to set the location of the user
	 * @param location
	 */
	public void setLocation(Set<String> location) {
		this.location = location;
	}
	
	/**
	 * Method that returns the list of social profiles, key = email id
	 * value = other information about the user.
	 * @return
	 */
	public Map<String, List<SocialProfile>> getMapSocial() {
		return mapSocial;
	}
	
	/**
	 * Method that set the social profile of a user in social map
	 * @param mapSocial
	 */
	public void setMapSocial(Map<String, List<SocialProfile>> mapSocial) {
		this.mapSocial = mapSocial;
	}
	
	public double getSimScore() {
		return simScore;
	}

	public void setSimScore(double simScore) {
		this.simScore = simScore;
	}
	
	private String emailId;
	private String contactInfo_familyName;
	private String contactInfo_fullName;
	private String contactInfo_givenName;
	private Gender demographics_gender;
	private Set<String> location = new HashSet<>();
	
	//Map of TypeId , Social Profiles
	private Map<String, List<SocialProfile>> mapSocial = new HashMap<>();
	
	private double simScore;
}

class SocialProfile {
	
	

	public String getSocialMediaId() {
		return socialMediaId;
	}
	
	public void setSocialMediaId(String socialMediaId) {
		this.socialMediaId = socialMediaId;
	}
	
	public String getTypeId() {
		return typeId;
	}
	
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	
	public String getTypeName() {
		return typeName;
	}
	
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	private String socialMediaId;
	private String typeId;
	private String typeName;	
	private String userName;
	
	
	
}
