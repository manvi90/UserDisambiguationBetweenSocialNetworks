/**
 * 
 */
package com.shareablee.cluster;

import java.util.ArrayList;
import java.util.List;

import com.shareablee.common.Profile;

/**
 * @author mm
 *
 */
public class Cluster {
	/**
	 * Method to return the list of profiles in a given cluster.
	 * 
	 * @return
	 */
	public List<Profile> getProfiles() {
		return profiles;
	}

	/**
	 * Method to add the profile to the cluster.
	 * 
	 * @param profile
	 */
	public void addProfile(Profile profile) {
		this.profiles.add(profile);
	}

	private List<Profile> profiles = new ArrayList<>();

}
