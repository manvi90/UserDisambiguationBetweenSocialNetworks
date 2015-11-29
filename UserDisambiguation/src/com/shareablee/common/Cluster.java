/**
 * 
 */
package com.shareablee.common;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mm
 *
 */
public class Cluster {
	/**
	 * 
	 * @return
	 */
	public List<UserProfile> getUsers() {
		return users;
	}

	/**
	 * 
	 * @param user
	 */
	public void addUsers(UserProfile user){
		this.users.add(user);
	}
	
	private List<UserProfile> users = new ArrayList<>();
	
}
