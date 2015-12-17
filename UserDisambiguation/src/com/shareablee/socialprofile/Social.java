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

	/**
	 * 
	 * @return socialMediaId
	 */
	public String getSocialMediaId() {
		return socialMediaId;
	}

	/**
	 * 
	 * @param socialMediaId
	 */
	public void setSocialMediaId(String socialMediaId) {
		this.socialMediaId = socialMediaId;
	}

	/**
	 * 
	 * @return typeId
	 */
	public String getTypeId() {
		return typeId;
	}

	/**
	 * 
	 * @param typeId
	 */
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	/**
	 * 
	 * @return typeName
	 */
	public String getTypeName() {
		return typeName;
	}

	/**
	 * 
	 * @param typeName
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	/**
	 * 
	 * @return userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * 
	 * @param userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	private String socialMediaId;
	private String typeId;
	private String typeName;
	private String userName;
}
