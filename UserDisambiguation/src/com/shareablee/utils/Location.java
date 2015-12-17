/**
 * 
 */
package com.shareablee.utils;

import java.util.Iterator;
import java.util.TreeSet;
import java.util.Set;

/**
 *
 */
public class Location {
	/**
	 * Method that returns the general location
	 * 
	 * @return locationGeneral
	 */
	public String getLocationGeneral() {
		return locationGeneral;
	}

	/**
	 * Method to set the Location general value.
	 * 
	 * @param locationGeneral
	 */
	public void setLocationGeneral(String locationGeneral) {
		this.locationGeneral = locationGeneral;
	}

	/**
	 * Method to return the deduced location
	 * 
	 * @return locationDeduced_deducedLocation
	 */
	public String getLocationDeduced_deducedLocation() {
		return locationDeduced_deducedLocation;
	}

	/**
	 * Method to set the deduced location
	 * 
	 * @param locationDeduced_deducedLocation
	 */
	public void setLocationDeduced_deducedLocation(
			String locationDeduced_deducedLocation) {
		this.locationDeduced_deducedLocation = locationDeduced_deducedLocation;
	}

	/**
	 * Method to return the likelihood for deduced location
	 * 
	 * @return locationDeduced_likelihood
	 */
	public String getLocationDeduced_likelihood() {
		return locationDeduced_likelihood;
	}

	/**
	 * Method to set the likelihood for deduced location
	 * 
	 * @param locationDeduced_likelihood
	 */
	public void setLocationDeduced_likelihood(String locationDeduced_likelihood) {
		this.locationDeduced_likelihood = locationDeduced_likelihood;
	}

	/**
	 * Method to return the normalized location
	 * 
	 * @return locationDeduced_normalizedLocation
	 */
	public String getLocationDeduced_normalizedLocation() {
		return locationDeduced_normalizedLocation;
	}

	/**
	 * Method to set the normalized location
	 * 
	 * @param locationDeduced_normalizedLocation
	 */
	public void setLocationDeduced_normalizedLocation(
			String locationDeduced_normalizedLocation) {
		this.locationDeduced_normalizedLocation = locationDeduced_normalizedLocation;
	}

	/**
	 * Method to return the deduced city
	 * 
	 * @return locationDeduced_city_deduced
	 */
	public String getLocationDeduced_city_deduced() {
		return locationDeduced_city_deduced;
	}

	/**
	 * Method to set the deduced city
	 * 
	 * @param locationDeduced_city_deduced
	 */
	public void setLocationDeduced_city_deduced(
			String locationDeduced_city_deduced) {
		this.locationDeduced_city_deduced = locationDeduced_city_deduced;
	}

	/**
	 * Method to return the deduced city name
	 * 
	 * @return locationDeduced_city_name
	 */
	public String getLocationDeduced_city_name() {
		return locationDeduced_city_name;
	}

	/**
	 * Method to set the deduced city name
	 * 
	 * @param locationDeduced_city_name
	 */
	public void setLocationDeduced_city_name(String locationDeduced_city_name) {
		this.locationDeduced_city_name = locationDeduced_city_name;
	}

	/**
	 * Method to return the deduced continent.
	 * 
	 * @return locationDeduced_continent_deduced
	 */
	public String getLocationDeduced_continent_deduced() {
		return locationDeduced_continent_deduced;
	}

	/**
	 * 
	 * @param locationDeduced_continent_deduced
	 */
	public void setLocationDeduced_continent_deduced(
			String locationDeduced_continent_deduced) {
		this.locationDeduced_continent_deduced = locationDeduced_continent_deduced;
	}

	/**
	 * Method to return the deduced continent name
	 * 
	 * @return locationDeduced_continent_name
	 */
	public String getLocationDeduced_continent_name() {
		return locationDeduced_continent_name;
	}

	/**
	 * Method to set the deduced continent name
	 * 
	 * @param locationDeduced_continent_name
	 */
	public void setLocationDeduced_continent_name(
			String locationDeduced_continent_name) {
		this.locationDeduced_continent_name = locationDeduced_continent_name;
	}

	/**
	 * Method to return the deduced country code
	 * 
	 * @return locationDeduced_country_code
	 */
	public String getLocationDeduced_country_code() {
		return locationDeduced_country_code;
	}

	/**
	 * Method to set the deduced country code
	 * 
	 * @param locationDeduced_country_code
	 */
	public void setLocationDeduced_country_code(
			String locationDeduced_country_code) {
		this.locationDeduced_country_code = locationDeduced_country_code;
	}

	/**
	 * Method to return the deduced country
	 * 
	 * @return locationDeduced_country_deduced
	 */
	public String getLocationDeduced_country_deduced() {
		return locationDeduced_country_deduced;
	}

	/**
	 * Method to set the deduced country
	 * 
	 * @param locationDeduced_country_deduced
	 */
	public void setLocationDeduced_country_deduced(
			String locationDeduced_country_deduced) {
		this.locationDeduced_country_deduced = locationDeduced_country_deduced;
	}

	/**
	 * Method to return the deduced country name
	 * 
	 * @return locationDeduced_country_name
	 */
	public String getLocationDeduced_country_name() {
		return locationDeduced_country_name;
	}

	/**
	 * Method to set the deduced country name
	 * 
	 * @param locationDeduced_country_name
	 */
	public void setLocationDeduced_country_name(
			String locationDeduced_country_name) {
		this.locationDeduced_country_name = locationDeduced_country_name;
	}

	/**
	 * Method to return the deduced county code
	 * 
	 * @return locationDeduced_county_code
	 */
	public String getLocationDeduced_county_code() {
		return locationDeduced_county_code;
	}

	/**
	 * Method to set the deduced county code
	 * 
	 * @param locationDeduced_county_code
	 */
	public void setLocationDeduced_county_code(
			String locationDeduced_county_code) {
		this.locationDeduced_county_code = locationDeduced_county_code;
	}

	/**
	 * Method to return the county deduced.
	 * 
	 * @return locationDeduced_county_deduced
	 */
	public String getLocationDeduced_county_deduced() {
		return locationDeduced_county_deduced;
	}

	/**
	 * Method to set the county deduced
	 * 
	 * @param locationDeduced_county_deduced
	 */
	public void setLocationDeduced_county_deduced(
			String locationDeduced_county_deduced) {
		this.locationDeduced_county_deduced = locationDeduced_county_deduced;
	}

	/**
	 * Method to return the county name
	 * 
	 * @return locationDeduced_county_name
	 */
	public String getLocationDeduced_county_name() {
		return locationDeduced_county_name;
	}

	/**
	 * Method to set the county name
	 * 
	 * @param locationDeduced_county_name
	 */
	public void setLocationDeduced_county_name(
			String locationDeduced_county_name) {
		this.locationDeduced_county_name = locationDeduced_county_name;
	}

	/**
	 * Method to return the state code
	 * 
	 * @return locationDeduced_state_code
	 */
	public String getLocationDeduced_state_code() {
		return locationDeduced_state_code;
	}

	/**
	 * Method to set the state code
	 * 
	 * @param locationDeduced_state_code
	 */
	public void setLocationDeduced_state_code(String locationDeduced_state_code) {
		this.locationDeduced_state_code = locationDeduced_state_code;
	}

	/**
	 * Method to return the deduced state
	 * 
	 * @return locationDeduced_state_deduced
	 */
	public String getLocationDeduced_state_deduced() {
		return locationDeduced_state_deduced;
	}

	/**
	 * Method to set the deduced state
	 * 
	 * @param locationDeduced_state_deduced
	 */
	public void setLocationDeduced_state_deduced(
			String locationDeduced_state_deduced) {
		this.locationDeduced_state_deduced = locationDeduced_state_deduced;
	}

	/**
	 * Method to set the state name
	 * 
	 * @return locationDeduced_state_name
	 */
	public String getLocationDeduced_state_name() {
		return locationDeduced_state_name;
	}

	/**
	 * Method to set the state name
	 * 
	 * @param locationDeduced_state_name
	 */
	public void setLocationDeduced_state_name(String locationDeduced_state_name) {
		this.locationDeduced_state_name = locationDeduced_state_name;
	}

	/**
	 * Method to build the location map, this combines all the existing location
	 * fields into one.
	 * 
	 * @return String representation of location
	 */
	public Set<String> getLocationMap() {

		Set<String> location = new TreeSet<String>();

		if (locationGeneral != null)
			location.add(locationGeneral);
		if (locationDeduced_deducedLocation != null)
			location.add(locationDeduced_deducedLocation);
		if (locationDeduced_normalizedLocation != null)
			location.add(locationDeduced_normalizedLocation);
		if (locationDeduced_city_deduced != null)
			location.add(locationDeduced_city_deduced);
		if (locationDeduced_city_name != null)
			location.add(locationDeduced_city_name);
		if (locationDeduced_continent_deduced != null)
			location.add(locationDeduced_continent_deduced);
		if (locationDeduced_continent_name != null)
			location.add(locationDeduced_continent_name);
		if (locationDeduced_country_code != null)
			location.add(locationDeduced_country_code);
		if (locationDeduced_country_deduced != null)
			location.add(locationDeduced_country_deduced);
		if (locationDeduced_country_name != null)
			location.add(locationDeduced_country_name);
		if (locationDeduced_county_deduced != null)
			location.add(locationDeduced_county_deduced);
		if (locationDeduced_county_code != null)
			location.add(locationDeduced_county_code);
		if (locationDeduced_county_name != null)
			location.add(locationDeduced_county_name);
		if (locationDeduced_state_deduced != null)
			location.add(locationDeduced_state_deduced);
		if (locationDeduced_state_code != null)
			location.add(locationDeduced_state_code);
		if (locationDeduced_state_name != null)
			location.add(locationDeduced_state_name);

		return location;

	}

	/**
	 * 
	 */
	@Override
	public String toString() {
		StringBuilder retVal = new StringBuilder();
		Set<String> location = new TreeSet<String>();

		if (locationGeneral != null)
			location.add(locationGeneral);
		if (locationDeduced_deducedLocation != null)
			location.add(locationDeduced_deducedLocation);
		if (locationDeduced_normalizedLocation != null)
			location.add(locationDeduced_normalizedLocation);
		if (locationDeduced_city_deduced != null)
			location.add(locationDeduced_city_deduced);
		if (locationDeduced_city_name != null)
			location.add(locationDeduced_city_name);
		if (locationDeduced_continent_deduced != null)
			location.add(locationDeduced_continent_deduced);
		if (locationDeduced_continent_name != null)
			location.add(locationDeduced_continent_name);
		if (locationDeduced_country_code != null)
			location.add(locationDeduced_country_code);
		if (locationDeduced_country_deduced != null)
			location.add(locationDeduced_country_deduced);
		if (locationDeduced_country_name != null)
			location.add(locationDeduced_country_name);
		if (locationDeduced_county_deduced != null)
			location.add(locationDeduced_county_deduced);
		if (locationDeduced_county_code != null)
			location.add(locationDeduced_county_code);
		if (locationDeduced_county_name != null)
			location.add(locationDeduced_county_name);
		if (locationDeduced_state_deduced != null)
			location.add(locationDeduced_state_deduced);
		if (locationDeduced_state_code != null)
			location.add(locationDeduced_state_code);
		if (locationDeduced_state_name != null)
			location.add(locationDeduced_state_name);

		Iterator<String> it = location.iterator();

		while (it.hasNext()) {
			String temp = it.next();
			if (temp != null && !temp.isEmpty()) {
				retVal.append("|" + temp);
			}
		}
		if (retVal.toString() == null || retVal.toString().isEmpty())
			return "";
		return retVal.substring(1);
	}

	private String locationGeneral;
	private String locationDeduced_deducedLocation;
	private String locationDeduced_likelihood;
	private String locationDeduced_normalizedLocation;
	private String locationDeduced_city_deduced;
	private String locationDeduced_city_name;
	private String locationDeduced_continent_deduced;
	private String locationDeduced_continent_name;
	private String locationDeduced_country_code;
	private String locationDeduced_country_deduced;
	private String locationDeduced_country_name;
	private String locationDeduced_county_code;
	private String locationDeduced_county_deduced;
	private String locationDeduced_county_name;
	private String locationDeduced_state_code;
	private String locationDeduced_state_deduced;
	private String locationDeduced_state_name;
}
