/**
 * 
 */
package com.shareablee.socialprofile;

/**
 * This class is used to store entire information provided for a particular
 * social user profile
 * 
 * @author ravidugar
 */
public class SocialMaster {
	/**
	 * Method to set the email id from user's social profile
	 * 
	 * @param id
	 */
	public SocialMaster(String id) {
		this.emailId = id;
	}

	/**
	 * Method to set the email id and social media id
	 * 
	 * @param id
	 * @param socialMediaId
	 */
	public SocialMaster(String id, String socialMediaId) {
		this.emailId = id;
		this.socialMediaId = socialMediaId;
	}

	/**
	 * Method to return the email id
	 * 
	 * @return
	 */
	public String getEmailId() {
		return emailId;
	}

	/**
	 * Method to set the email id of the user
	 * 
	 * @param id
	 */
	public void setId(String id) {
		this.emailId = id;
	}

	/**
	 * Method that returns the user biographical information
	 * 
	 * @return
	 */
	public String getBio() {
		return bio;
	}

	/**
	 * Method to set the biograhical information for the user
	 * 
	 * @param bio
	 */
	public void setBio(String bio) {
		this.bio = bio;
	}

	/**
	 * Method that returns the social media id of the user
	 * 
	 * @return
	 */
	public String getSocialMediaId() {
		return socialMediaId;
	}

	/**
	 * Method that sets the social media id of the user
	 * 
	 * @param socialMediaId
	 */
	public void setSocialMediaId(String socialMediaId) {
		this.socialMediaId = socialMediaId;
	}

	/**
	 * Method that return the RSS of the user
	 * 
	 * @return
	 */
	public String getRss() {
		return rss;
	}

	/**
	 * Method to set the RSS of the user
	 * 
	 * @param rss
	 */
	public void setRss(String rss) {
		this.rss = rss;
	}

	/**
	 * Method that returns the type ID of social media
	 * 
	 * @return
	 */
	public String getTypeId() {
		return typeId;
	}

	/**
	 * Method to set the type ID for a particular social profile
	 * 
	 * @param typeId
	 */
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	/**
	 * Method to return the type name of the social profile
	 * 
	 * @return
	 */
	public String getTypeName() {
		return typeName;
	}

	/**
	 * Method to set the type name of social profile
	 * 
	 * @param typeName
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	/**
	 * Method to return the user social url
	 * 
	 * @return
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Method to set the user social url
	 * 
	 * @param url
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * Method to return the user name of the user
	 * 
	 * @return
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Method to set the user name of the user
	 * 
	 * @param userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * Method to return the number of followers
	 * 
	 * @return
	 */
	public int getFollowers() {
		return followers;
	}

	/**
	 * Method that set the number of followers for the user
	 * 
	 * @param followers
	 */
	public void setFollowers(int followers) {
		this.followers = followers;
	}

	/**
	 * Method to return the number of users followed by the user.
	 * 
	 * @return
	 */
	public int getFollowing() {
		return following;
	}

	/**
	 * Method to set the number of people followed by the user
	 * 
	 * @param following
	 */
	void setFollowing(int following) {
		this.following = following;
	}

	private String emailId;
	private String bio;
	private int followers;
	private int following;
	private String socialMediaId;
	private String rss;
	private String typeId;
	private String typeName;
	private String url;
	private String userName;
}
