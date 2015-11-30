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
	 * Method to return the list of users in a given cluster.
	 * @return
	 */
	public List<UserProfile> getUsers() {
		return users;
	}

	/**
	 * Method to add the user to the cluster.
	 * @param user
	 */
	public void addUsers(UserProfile user){
		this.users.add(user);
	}
	
	private List<UserProfile> users = new ArrayList<>();
	
}
