/**
 * 
 */
package com.shareablee.socialprofile;

/**
 * This class is used to store selected information provided for a particular
 * social profile
 * 
 * @author ravidugar
 */
public class Social {

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
