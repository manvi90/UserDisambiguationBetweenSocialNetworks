package com.shareablee.testing;

import java.util.ArrayList;
import java.util.List;

import com.shareablee.common.Profile;
import com.shareablee.userprofile.User;
import com.shareablee.userprofile.UserCSVReader;
import com.shareablee.userprofile.UserMaster;
import com.shareablee.utils.CSVReader;

/**
 * Class for testing
 * @author Madhuri
 *
 */
public class Test {
	
	public List<Profile> getTestData(String inputPath) {
		CSVReader<UserMaster> ucsv = new UserCSVReader();
		List<UserMaster> users = ucsv.getData(inputPath);
		List<Profile> retVal = new ArrayList<>();
		
		for(UserMaster userMaster : users) {
			User user = new User();
			user.setContactInfo_familyName(userMaster
					.getContactInfo_familyName());
			user.setContactInfo_fullName(userMaster.getContactInfo_fullName());
			user.setContactInfo_givenName(userMaster.getContactInfo_givenName());
			user.setEmailId(userMaster.getEmailId());
			user.setDemographics_gender(userMaster.getDemographic()
					.getDemographics_gender());
			user.setLocation(userMaster.getDemographic().getLocation()
					.getLocationMap());

			Profile profile = new Profile();
			profile.setUser(user);
			retVal.add(profile);
		}
		
		return retVal;
	}
	
}
