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

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public boolean isMatch() {
		return match;
	}

	public void setMatch(boolean match) {
		this.match = match;
	}

	private Profile profile;
	private boolean success = false;
	private boolean match = false;
}
