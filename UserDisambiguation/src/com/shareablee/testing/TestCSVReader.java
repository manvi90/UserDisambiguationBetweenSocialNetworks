package com.shareablee.testing;

import com.shareablee.common.Profile;
import com.shareablee.userprofile.User;
import com.shareablee.userprofile.UserMaster;
import com.shareablee.utils.CSVReader;
import com.shareablee.utils.Demographic;
import com.shareablee.utils.Location;
import com.shareablee.utils.Range;

/**
 * Class for testing
 * 
 * @author Madhuri
 *
 */
public class TestCSVReader extends CSVReader<TestProfile> {

	@Override
	public TestProfile parseLine(String inputLine) {
		TestProfile retVal = new TestProfile();
		Demographic demoInfo = new Demographic();
		Location locationInfo = new Location();

		Range<Integer> ageRange = new Range<Integer>(0, 100);

		UserMaster userMaster = null;
		try {
			String[] inputLineArray = inputLine.split(",");
			userMaster = new UserMaster(inputLineArray[0]); // id
			if (inputLineArray.length > 1)
				userMaster.setLikelihood(Double.parseDouble(inputLineArray[1])); // likelihood
			if (inputLineArray.length > 2)
				userMaster.setRequestId(inputLineArray[2]); // requestId
			if (inputLineArray.length > 3)
				userMaster.setStatus(Integer.parseInt(inputLineArray[3])); // status
			if (inputLineArray.length > 4)
				userMaster.setContactInfo_familyName(inputLineArray[4]); // contactInfo_familyName
			if (inputLineArray.length > 5)
				userMaster.setContactInfo_fullName(inputLineArray[5]); // contactInfo_fullName
			if (inputLineArray.length > 6)
				userMaster.setContactInfo_givenName(inputLineArray[6]); // contactInfo_givenName

			if (inputLineArray.length > 7) {
				try {
					demoInfo.setDemographics_age(Integer
							.parseInt(inputLineArray[7])); // demographic_age
				} catch (Exception ex) {
				}
			}

			// TODO : Need to see range split
			// if(inputLineArray.length > 8)
			// demoInfo.setDemographics_ageRange(inputLineArray[8]);
			// //demographic_ageRange

			demoInfo.setDemographics_ageRange(ageRange);

			if (inputLineArray.length > 9)
				demoInfo.setDemographics_gender(inputLineArray[9]); // demographics_gender

			if (inputLineArray.length > 10)
				locationInfo.setLocationGeneral(inputLineArray[10]); // locationGeneral
			if (inputLineArray.length > 11)
				locationInfo
						.setLocationDeduced_deducedLocation(inputLineArray[11]); // deducedLocation
			if (inputLineArray.length > 12)
				locationInfo.setLocationDeduced_likelihood(inputLineArray[12]); // deduced_likelihood
			if (inputLineArray.length > 13)
				locationInfo
						.setLocationDeduced_normalizedLocation(inputLineArray[13]); // normalizedLocation
			if (inputLineArray.length > 14)
				locationInfo
						.setLocationDeduced_city_deduced(inputLineArray[14]); // cityDeduced
			if (inputLineArray.length > 15)
				locationInfo.setLocationDeduced_city_name(inputLineArray[15]); // cityName
			if (inputLineArray.length > 16)
				locationInfo
						.setLocationDeduced_continent_deduced(inputLineArray[16]); // continentDeduced
			if (inputLineArray.length > 17)
				locationInfo
						.setLocationDeduced_continent_name(inputLineArray[17]); // continenetName
			if (inputLineArray.length > 18)
				locationInfo
						.setLocationDeduced_country_code(inputLineArray[18]); // countryCode
			if (inputLineArray.length > 19)
				locationInfo
						.setLocationDeduced_country_deduced(inputLineArray[19]); // country_deduced
			if (inputLineArray.length > 20)
				locationInfo
						.setLocationDeduced_country_name(inputLineArray[20]); // countryName
			if (inputLineArray.length > 21)
				locationInfo.setLocationDeduced_county_code(inputLineArray[21]); // countyCode
			if (inputLineArray.length > 22)
				locationInfo
						.setLocationDeduced_county_deduced(inputLineArray[22]); // countyDeduced
			if (inputLineArray.length > 23)
				locationInfo.setLocationDeduced_county_name(inputLineArray[23]); // countyName
			if (inputLineArray.length > 24)
				locationInfo.setLocationDeduced_state_code(inputLineArray[24]); // stateCode
			if (inputLineArray.length > 25)
				locationInfo
						.setLocationDeduced_state_deduced(inputLineArray[25]); // stateDeduced
			if (inputLineArray.length > 26)
				locationInfo.setLocationDeduced_state_name(inputLineArray[26]); // stateName

			// Yes/No in the end of csv
			// Indicates Desired outputs
			if (inputLineArray[inputLineArray.length - 1]
					.equalsIgnoreCase("YES"))
				retVal.setMatch(true);

			demoInfo.setLocation(locationInfo);
			userMaster.setDemographic(demoInfo);
		} catch (Exception e) {

		}

		User user = new User();
		user.setContactInfo_familyName(userMaster.getContactInfo_familyName());
		user.setContactInfo_fullName(userMaster.getContactInfo_fullName());
		user.setContactInfo_givenName(userMaster.getContactInfo_givenName());
		user.setDemographics_gender(userMaster.getDemographic()
				.getDemographics_gender());
		user.setEmailId(userMaster.getEmailId());
		user.setLocation(userMaster.getDemographic().getLocation()
				.getLocationMap());

		Profile profile = new Profile();
		profile.setUser(user);

		retVal.setProfile(profile);
		return retVal;
	}

}
