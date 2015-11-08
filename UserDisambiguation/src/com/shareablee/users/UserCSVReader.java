/**
 * 
 */
package com.shareablee.users;

import com.shareablee.common.CSVReader;
import com.shareablee.common.Location;

/**
 *
 */
public class UserCSVReader extends CSVReader<User> {
	
	@Override
	public User parseLine(String inputLine) {
		Demographic demoInfo = new Demographic();
		Location locationInfo = new Location();
		
		//TODO : Uncomment
		//Range<Integer> rangeOfAge = new Range<Integer>();
		
		User retVal = null;
		
		String[] inputLineArray = inputLine.split(",");
		retVal = new User(inputLineArray[0]); // id
		retVal.setLikelihood(Double.parseDouble(inputLineArray[1])); //likelihood
		retVal.setRequestId(inputLineArray[2]); // requestId
		retVal.setStatus(Integer.parseInt(inputLineArray[3])); // status
		retVal.setContactInfo_familyName(inputLineArray[4]); //contactInfo_familyName
		retVal.setContactInfo_fullName(inputLineArray[5]);  //contactInfo_fullName
		retVal.setContactInfo_givenName(inputLineArray[6]); //contactInfo_givenName
		
		demoInfo.setDemographics_age(Integer.parseInt(inputLineArray[7])); //demographic_age
		
		//TODO : Need to see range split
		//demoInfo.setDemographics_ageRange(inputLineArray[8]); //demographic_ageRange
		
		//TODO : fix enum
		//demoInfo.setDemographics_gender(enum.parseLine(inputLineArray[9])); //demographics_gender
 		
		locationInfo.setLocationGeneral(inputLineArray[10]); //locationGeneral
		locationInfo.setLocationDeduced_deducedLocation(inputLineArray[11]); //deducedLocation
		locationInfo.setLocationDeduced_likelihood(Double.parseDouble(inputLineArray[12])); // deduced_likelihood
		locationInfo.setLocationDeduced_normalizedLocation(inputLineArray[13]); //normalizedLocation
		locationInfo.setLocationDeduced_city_deduced(inputLineArray[14]); //cityDeduced
		locationInfo.setLocationDeduced_city_name(inputLineArray[15]); //cityName
		locationInfo.setLocationDeduced_continent_deduced(inputLineArray[16]); //continentDeduced
		locationInfo.setLocationDeduced_continent_name(inputLineArray[17]); //continenetName
		locationInfo.setLocationDeduced_country_code(inputLineArray[18]); //countryCode
		locationInfo.setLocationDeduced_country_deduced(inputLineArray[19]); //country_deduced
		locationInfo.setLocationDeduced_country_name(inputLineArray[20]); //countryName
		locationInfo.setLocationDeduced_county_code(inputLineArray[21]); //countyCode
		locationInfo.setLocationDeduced_county_deduced(inputLineArray[22]); //countyDeduced
		locationInfo.setLocationDeduced_county_name(inputLineArray[23]); //countyName
		locationInfo.setLocationDeduced_state_code(inputLineArray[24]); //stateCode
		locationInfo.setLocationDeduced_state_deduced(inputLineArray[25]); //stateDeduced
		locationInfo.setLocationDeduced_state_name(inputLineArray[26]); //stateName
	
		demoInfo.setLocation(locationInfo);
		retVal.setDemographic(demoInfo);
		
		return retVal;
		
	}
}