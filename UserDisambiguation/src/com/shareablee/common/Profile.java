/**
 * 
 */
package com.shareablee.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.shareablee.socialprofile.Social;
import com.shareablee.userprofile.User;

/**
 * This class is used to store selected information provided for a particular
 * user and all his related profiles
 * 
 * @author ravidugar
 */
public class Profile {

	/**
	 * Method that returns the user profile
	 * 
	 * @return user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Method that sets the user profile
	 * 
	 * @param user
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * Method that returns the list of social profiles, key = email id value =
	 * other information about the user.
	 * 
	 * @return Collection of Social profiles for the user
	 */
	public Map<String, List<Social>> getMapSocial() {
		return mapSocial;
	}

	/**
	 * Method that set the social profile of a user in social map
	 * 
	 * @param mapSocial
	 */
	public void setMapSocial(Map<String, List<Social>> mapSocial) {
		this.mapSocial = mapSocial;
	}

	private User user;

	// Map of TypeId , Social Profiles
	private Map<String, List<Social>> mapSocial = new HashMap<>();
}
