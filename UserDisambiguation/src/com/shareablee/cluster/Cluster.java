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
	 * Returns the list of profiles in a cluster.
	 * 
	 * @return Collection of profiles present in the cluster
	 */
	public List<Profile> getProfiles() {
		return profiles;
	}

	/**
	 * Adds the profile to the cluster.
	 * 
	 * @param profile
	 */
	public void addProfile(Profile profile) {
		this.profiles.add(profile);
	}

	private List<Profile> profiles = new ArrayList<>();

}
