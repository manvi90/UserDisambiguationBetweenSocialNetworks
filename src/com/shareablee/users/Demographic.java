/**
 * 
 */
package com.shareablee.users;

import com.shareablee.common.*;


/**
 * 
 *
 */
public class Demographic {
	
	public int getDemographics_age() {
		return demographics_age;
	}
	
	public void setDemographics_age(int demographics_age) {
		this.demographics_age = demographics_age;
	}
	
	public Range<Integer> getDemographics_ageRange() {
		return demographics_ageRange;
	}
	
	public void setDemographics_ageRange(Range<Integer> demographics_ageRange) {
		this.demographics_ageRange = demographics_ageRange;
	}
	
	public Gender getDemographics_gender() {
		return demographics_gender;
	}
	
	public void setDemographics_gender(Gender demographics_gender) {
		this.demographics_gender = demographics_gender;
	}
	
	public Location getLocation() {
		return location;
	}
	
	public void setLocation(Location location) {
		this.location = location;
	}
	
	private int demographics_age;
	private Range<Integer> demographics_ageRange;
	private Gender demographics_gender;
	private Location location;
}
