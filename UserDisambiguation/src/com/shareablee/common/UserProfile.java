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
	
	public String getEmailId() {
		return emailId;
	}
	
	public void setEmailId(String emailId) {
		this.emailId = emailId;
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
	
	public Gender getDemographics_gender() {
		return demographics_gender;
	}
	
	public void setDemographics_gender(Gender demographics_gender) {
		this.demographics_gender = demographics_gender;
	}
	
	public Set<String> getLocation() {
		return location;
	}
	
	public void setLocation(Set<String> location) {
		this.location = location;
	}
	
	public Map<String, List<SocialProfile>> getMapSocial() {
		return mapSocial;
	}
	
	public void setMapSocial(Map<String, List<SocialProfile>> mapSocial) {
		this.mapSocial = mapSocial;
	}
	
	private String emailId;
	private String contactInfo_familyName;
	private String contactInfo_fullName;
	private String contactInfo_givenName;
	private Gender demographics_gender;
	private Set<String> location = new HashSet<>();
	
	//Map of TypeId , Social Profiles
	private Map<String, List<SocialProfile>> mapSocial = new HashMap<>();
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
