/**
 * 
 */
package com.shareablee.common;

import java.util.HashSet;
import java.util.Set;

import com.shareablee.utils.Utilities;

/**
 * @author Madhuri
 *
 */
public class Disambiguator {

	static final double EMAIL_THRESHOLD = 0.7; 
	static final double FIRST_NAME_THRESHOLD = 0.95;
	static final double LAST_NAME_THRESHOLD = 0.98;
	
	static final double EMAIL_WEIGHT = 0.25;
	static final double FIRST_NAME_WEIGHT = 0.25;
	static final double LAST_NAME_WEIGHT = 0.3;
	static final double GENDER_WEIGHT = 0.05;
	static final double LOCATION_WEIGHT = 0.15;
	
	public static UserProfile userDisambiguator(UserProfile newUser){

		UserProfile retVal = null;
		Set<String> matchingEmailList = new HashSet<>();
		

		for (String existingEmail : Program.getEmailList()){
			double simScore = Utilities.getSimilarity(newUser.getEmailId(), existingEmail);
			if (simScore < EMAIL_THRESHOLD){
				simScore = Utilities.getSimilarity(newUser.getEmailId().split("@")[0], 
						existingEmail.split("@")[0]);

				if (simScore >= EMAIL_THRESHOLD){
					matchingEmailList.add(existingEmail);
				}

			} else{
				matchingEmailList.add(existingEmail);
			}



		}

		int count = 0;
		for (String existingFirstName : Program.getFirstNameList()){
			try {
				double firstnameSimScore = Utilities.getSimilarity(newUser.getContactInfo_givenName(), existingFirstName);
				
				if (firstnameSimScore >= FIRST_NAME_THRESHOLD){
					matchingEmailList.addAll(Program.getFirstNameMap().get(existingFirstName));
				}
			}catch(Exception ex) {
				System.err.println("First name : " + count + ": "+ ex.getMessage());
			}
			count++;
		}
		count = 0;
		for (String existingLastName : Program.getLastNameList()){
			try {
				double lastnameSimScore = Utilities.getSimilarity(newUser.getContactInfo_familyName(), existingLastName);
				if (lastnameSimScore >= LAST_NAME_THRESHOLD){
					matchingEmailList.addAll(Program.getLastNameMap().get(existingLastName));
				}
			} catch (Exception ex) {
				System.err.println("Second name " + count + ": " + ex.getMessage());
			}
			count++;
		}
		
		double maxSimScore = 0.0;
		for(String email : matchingEmailList){
			System.out.println(email);
			
			UserProfile userProfile = Program.userlist.get(email);
			double emailSim = Utilities.getSimilarity(userProfile.getEmailId(), newUser.getEmailId());
			double emailWithoutDomainSim = 0;
			double fnameSim = 0;
			double lnameSim = 0;
			double genderSim = 0;
			double locationSim = 0;
			
			if (emailSim < EMAIL_THRESHOLD){
				emailWithoutDomainSim = Utilities.getSimilarity(userProfile.getEmailId().split("@")[0], 
						newUser.getEmailId().split("@")[0]);
			}
			
			fnameSim = Utilities.getSimilarity(userProfile.getContactInfo_givenName(),
														newUser.getContactInfo_givenName());
			lnameSim = Utilities.getSimilarity(userProfile.getContactInfo_familyName(),
														newUser.getContactInfo_familyName());
													
			
			genderSim = userProfile.getDemographics_gender() == newUser.getDemographics_gender() ? 1.0 : 0.0;
			
			//need to check for location
			count = 0;
			for(String string : userProfile.getLocation()) {
				if(newUser.getLocation().contains(string)) {
					count++;
				}
			}
			
			if(count > 0) {
				locationSim = count * 1.0 / Math.min(userProfile.getLocation().size(), newUser.getLocation().size());
			}
			
			double simScore = EMAIL_WEIGHT * Math.max(emailSim, emailWithoutDomainSim) + 
								FIRST_NAME_WEIGHT * fnameSim + 
								LAST_NAME_WEIGHT * lnameSim +
								GENDER_WEIGHT * genderSim + 
								LOCATION_WEIGHT * locationSim;
			if(maxSimScore < simScore) {
				maxSimScore = simScore;
				retVal = userProfile; 
			}
			
		}
		System.out.println(maxSimScore);
		return retVal;
	}

}
