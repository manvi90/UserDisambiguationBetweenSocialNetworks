/**
 * 
 */
package com.shareablee.social;


/**
 * 
 *
 */
public class Social {
	
	public Social(String id) { 
		this.emailId = id;
	}
	
	public Social (String id, String socialMediaId) {
		this.emailId = id;
		this.socialMediaId = socialMediaId;
	}
	
	public String getEmailId() {
		return emailId;
	}

	public void setId(String id) {
		this.emailId = id;
	}
	
	public String getBio() {
		return bio;
	}
	
	public void setBio(String bio) {
		this.bio = bio;
	}
	
	public String getSocialMediaId() {
		return socialMediaId;
	}
	
	public void setSocialMediaId(String socialMediaId) {
		this.socialMediaId = socialMediaId;
	}
	
	public String getRss() {
		return rss;
	}
	
	public void setRss(String rss) {
		this.rss = rss;
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
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public int getFollowers() {
		return followers;
	}
	
	public void setFollowers(int followers) {
		this.followers = followers;
	}
	
	public int getFollowing() {
		return following;
	}
	
	public void setFollowing(int following) {
		this.following = following;
	}
	
	private String emailId;
	private String bio;
	// TODO: check
	private int followers; 
	private int following;
	private String socialMediaId;
	private String rss;
	private String typeId;
	private String typeName;
	private String url;
	private String userName;
}
