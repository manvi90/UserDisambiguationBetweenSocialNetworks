/**
 * 
 */
package com.shareablee.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.shareablee.socialprofile.SocialMaster;
import com.shareablee.userprofile.UserMaster;

/**
 * This class is used to store entire information provided for a particular
 * userMaster and all his related profiles
 * 
 * @author ravidugar
 */
public class ProfileMaster {

	/**
	 * Method that returns the userMaster
	 * 
	 * @return
	 */
	public UserMaster getUser() {
		return userMaster;
	}

	/**
	 * Method that set the userMaster
	 * 
	 * @param userMaster
	 */
	public void setUser(UserMaster userMaster) {
		this.userMaster = userMaster;
	}

	/**
	 * 
	 * @return
	 */
	public Map<String, List<SocialMaster>> getMapSocial() {
		return mapSocial;
	}

	public void setMapSocial(Map<String, List<SocialMaster>> mapSocial) {
		this.mapSocial = mapSocial;
	}

	private UserMaster userMaster;
	private Map<String, List<SocialMaster>> mapSocial = new HashMap<String, List<SocialMaster>>();
}
