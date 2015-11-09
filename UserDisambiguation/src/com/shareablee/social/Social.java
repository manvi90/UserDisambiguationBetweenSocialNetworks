/**
 * 
 */
package com.shareablee.social;

import java.util.List;

/**
 * 
 *
 */
public class Social {
	
	public Social(String id) { 
		this.id = id;
	}
	
	public Social (String id, String socialMediaId) {
		this.id = id;
		this.socialMediaId = socialMediaId;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
	
	public List<String> getFollowers() {
		return followers;
	}
	
	public List<String> getFollowing() {
		return following;
	}
	
	public void addFollowers(String follower) {
		this.followers.add(follower);
	}
	
	public void addFollowing(String following) {
		this.following.add(following);
	}
	
	private String id;
	private String bio;
	// TODO: check
	private List<String> followers; 
	private List<String> following;
	private String socialMediaId;
	private String rss;
	private String typeId;
	private String typeName;
	private String url;
	private String userName;
}
