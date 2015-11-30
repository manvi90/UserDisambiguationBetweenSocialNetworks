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
	
	/**
	 * Method that returns the user
	 * @return
	 */
	public User getUser() {
		return user;
	}
	
	/**
	 * Method that set the user
	 * @param user
	 */
	public void setUser(User user) {
		this.user = user;
	}
	
	/**
	 * 
	 * @return
	 */
	public Map<String, List<Social>> getMapSocial() {
		return mapSocial;
	}
	
	public void setMapSocial(Map<String, List<Social>> mapSocial) {
		this.mapSocial = mapSocial;
	}
	
	private User user;
	private Map<String, List<Social>> mapSocial= new HashMap<String, List<Social>>();
}
