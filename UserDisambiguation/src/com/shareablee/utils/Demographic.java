/**
 * 
 */
package com.shareablee.utils;

/**
 * This class stores demographic information
 * 
 * @author ravidugar
 */
public class Demographic {

	/**
	 * 
	 * @return Age
	 */
	public int getDemographics_age() {
		return demographics_age;
	}

	/**
	 * 
	 * @param demographics_age
	 */
	public void setDemographics_age(int demographics_age) {
		this.demographics_age = demographics_age;
	}

	/**
	 * 
	 * @return Age Range
	 */
	public Range<Integer> getDemographics_ageRange() {
		return demographics_ageRange;
	}

	/**
	 * 
	 * @param demographics_ageRange
	 */
	public void setDemographics_ageRange(Range<Integer> demographics_ageRange) {
		this.demographics_ageRange = demographics_ageRange;
	}

	/**
	 * 
	 * @return Gender
	 */
	public Gender getDemographics_gender() {
		return demographics_gender;
	}

	/**
	 * 
	 * @param gender
	 */
	public void setDemographics_gender(String gender) {
		if (gender == null || gender.isEmpty()) {
			this.demographics_gender = Gender.UNKNOWN;
		} else {
			switch (gender.toUpperCase()) {
			case "FEMALE":
				this.demographics_gender = Gender.FEMALE;
				break;
			case "MALE":
				this.demographics_gender = Gender.MALE;
				break;
			default:
				this.demographics_gender = Gender.UNKNOWN;
			}
		}
	}

	/**
	 * 
	 * @return Location object
	 */
	public Location getLocation() {
		return location;
	}

	/**
	 * 
	 * @param location
	 */
	public void setLocation(Location location) {
		this.location = location;
	}

	private int demographics_age;
	private Range<Integer> demographics_ageRange;
	private Gender demographics_gender = Gender.UNKNOWN;
	private Location location;
}
