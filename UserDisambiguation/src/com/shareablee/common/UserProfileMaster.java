/**
 * 
 */
package com.shareablee.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.shareablee.users.User;
import com.shareablee.social.Social;

/**
 * @author ravidugar
 *
 */
public class UserProfileMaster {
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public Map<String, List<Social>> getMapSocial() {
		return mapSocial;
	}
	
	public void setMapSocial(Map<String, List<Social>> mapSocial) {
		this.mapSocial = mapSocial;
	}
	
	private User user;
	private Map<String, List<Social>> mapSocial= new HashMap<String, List<Social>>();
}
