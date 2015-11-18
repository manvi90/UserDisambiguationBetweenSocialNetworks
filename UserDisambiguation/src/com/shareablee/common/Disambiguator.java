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

	final double EMAIL_THRESHOLD = 0.7; 
	final double FIRST_NAME_THRESHOLD = 0.95;
	final double LAST_NAME_THRESHOLD = 0.98;

	public void userDisambiguator(UserProfile newUser){

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


		for (String existingFirstName : Program.getLastNameList()){
			double firstnameSimScore = Utilities.getSimilarity(newUser.getContactInfo_givenName(), existingFirstName);

			if (firstnameSimScore >= FIRST_NAME_THRESHOLD){
				matchingEmailList.addAll(Program.getFirstNameMap().get(existingFirstName));
			}

		}

		for (String existingLastName : Program.getLastNameList()){
			double lastnameSimScore = Utilities.getSimilarity(newUser.getContactInfo_givenName(), existingLastName);

			if (lastnameSimScore >= FIRST_NAME_THRESHOLD){
				matchingEmailList.addAll(Program.getLastNameMap().get(existingLastName));
			}

		}

		for(String email : matchingEmailList){
			UserProfile userProfile = Program.userlist.get(email);
			double emailSim = Utilities.getSimilarity(userProfile.getEmailId(), newUser.getEmailId());
			double emailWithoutDomainSim = 0;
			
			if (emailSim < EMAIL_THRESHOLD){
				emailWithoutDomainSim = Utilities.getSimilarity(userProfile.getEmailId().split("@")[0], 
						newUser.getEmailId().split("@")[0]);
			}
			
			double FnameSim = Utilities.getSimilarity(userProfile.getContactInfo_givenName(),
														newUser.getContactInfo_givenName());
			double LnameSim = Utilities.getSimilarity(userProfile.getContactInfo_familyName(),
														newUser.getContactInfo_familyName());
													
			
			double genderSim = userProfile.getDemographics_gender() == newUser.getDemographics_gender() ? 1.0 : 0.0;
			
			//need to check for location
			
			
			
			
			
			
			
		}
		
	}

}
