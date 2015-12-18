/**
 * 
 */
package com.shareablee.testing;

import com.shareablee.common.Profile;

/**
 * @author Madhuri
 *
 */
public class TestProfile {

	/**
	 * 
	 * @return Profile
	 */
	public Profile getProfile() {
		return profile;
	}

	/**
	 * 
	 * @param profile
	 */
	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	/**
	 * 
	 * @return True if test passed
	 */
	public boolean isSuccess() {
		return success;
	}

	/**
	 * 
	 * @param success
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}

	/**
	 * 
	 * @return true if desired output is true
	 */
	public boolean isMatch() {
		return match;
	}

	/**
	 * 
	 * @param match
	 */
	public void setMatch(boolean match) {
		this.match = match;
	}

	private Profile profile;
	private boolean success = false;
	private boolean match = false;
}
