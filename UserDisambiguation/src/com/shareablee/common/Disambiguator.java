/**
 * 
 */
package com.shareablee.common;

import java.util.HashSet;
import java.util.Set;

import com.shareablee.utils.ProgramConstants;
import com.shareablee.utils.Utilities;

/**
 *  
 * @author Madhuri
 *
 */
public class Disambiguator {

	/**
	 * Method to calculate total similarity score.
	 * @param newUser
	 * @return
	 */
	public static Set<UserProfile> userDisambiguator(UserProfile newUser) {

		Set<UserProfile> retVal = new HashSet<>();
		Set<String> matchingEmailList = new HashSet<>();

		matchingEmailList.addAll(getSimilarEmails(newUser));
		matchingEmailList.addAll(getSimilarFirstNames(newUser));
		matchingEmailList.addAll(getSimilarLastNames(newUser));
		matchingEmailList.addAll(getSimilarUserIds(newUser));

		for(String e : matchingEmailList){
			//System.out.println(e);
		}


		for (String email : matchingEmailList) {
			double count = 0;
			//System.out.println(email);

			UserProfile userProfile = Program.userlist.get(email);
			double emailSim = Utilities.getSimilarity(userProfile.getEmailId(),
					newUser.getEmailId(),true);


			double emailWithoutDomainSim = 0;
			double fnameSim = 0;
			double lnameSim = 0;
			double unameSim = 0;
			double genderSim = 0;
			double locationSim = 0;

			if (userProfile.getEmailId() != null
					&& !userProfile.getEmailId().isEmpty()
					&& newUser.getEmailId() != null
					&& !newUser.getEmailId().isEmpty()) {
				count += ProgramConstants.EMAIL_WEIGHT;
			}

			if (emailSim < ProgramConstants.EMAIL_THRESHOLD) {
				emailWithoutDomainSim = Utilities.getSimilarity(userProfile
						.getEmailId().split("@")[0], newUser.getEmailId()
						.split("@")[0], true);
			}

			if (userProfile.getContactInfo_givenName() != null
					&& !userProfile.getContactInfo_givenName().isEmpty()
					&& newUser.getContactInfo_givenName() != null
					&& !newUser.getContactInfo_givenName().isEmpty()) {
				count += ProgramConstants.FIRST_NAME_WEIGHT;
				fnameSim = Utilities.getSimilarity(
						userProfile.getContactInfo_givenName(),
						newUser.getContactInfo_givenName(),false);
			}

			if (userProfile.getContactInfo_familyName() != null
					&& !userProfile.getContactInfo_familyName().isEmpty()
					&& newUser.getContactInfo_familyName() != null
					&& !newUser.getContactInfo_familyName().isEmpty()) {
				count += ProgramConstants.LAST_NAME_WEIGHT;
				lnameSim = Utilities.getSimilarity(
						userProfile.getContactInfo_familyName(),
						newUser.getContactInfo_familyName(),false);
			}

			unameSim = calculateUserIdSimilarityScore(newUser, userProfile);
			if (unameSim > 0)
				count += ProgramConstants.USERID_WEIGHT;

			if (userProfile.getDemographics_gender() != Gender.UNKNOWN
					&& newUser.getDemographics_gender() != Gender.UNKNOWN) {
				count += ProgramConstants.GENDER_WEIGHT;
				genderSim = userProfile.getDemographics_gender() == newUser
						.getDemographics_gender() ? 1.0 : 0.0;
			}

			if (userProfile.getLocation().size() != 0
					&& newUser.getLocation().size() != 0) {
				count += ProgramConstants.LOCATION_WEIGHT;
				locationSim = calculateLocationSimilarityScore(newUser, userProfile);
			}

			double simScore = ProgramConstants.EMAIL_WEIGHT * Math.max(emailSim, emailWithoutDomainSim) / count
					+ ProgramConstants.FIRST_NAME_WEIGHT * fnameSim / count 
					+ ProgramConstants.LAST_NAME_WEIGHT * lnameSim / count 
					+ ProgramConstants.USERID_WEIGHT * unameSim / count
					+ ProgramConstants.GENDER_WEIGHT * genderSim / count 
					+ ProgramConstants.LOCATION_WEIGHT * locationSim / count;

			if (ProgramConstants.USER_SIMILARITY_THRESHOLD <= simScore) {
				retVal.add(userProfile);
				userProfile.setSimScore(simScore);

			}

		}
		//System.out.println(maxSimScore);
		return retVal;
	}

	/**
	 * Method that calculates the similarity between the two emails and if it lies in the
	 * threshold, add it to the matching email list
	 * @param newUser
	 * @return
	 */
	private static Set<String> getSimilarEmails(UserProfile newUser) {
		Set<String> matchingEmailList = new HashSet<>();
		for (String existingEmail : Program.getEmailList()) {
			/*double simScore = Utilities.getSimilarity(newUser.getEmailId(),
					existingEmail,true);*/
			double simScore = 0.0;
			if (simScore < ProgramConstants.EMAIL_THRESHOLD) {
				simScore = Utilities.getSimilarity(
						newUser.getEmailId().split("@")[0],
						existingEmail.split("@")[0],true);

				if (simScore >= ProgramConstants.EMAIL_THRESHOLD) {
					matchingEmailList.add(existingEmail);
				}

			} else {
				matchingEmailList.add(existingEmail);
			}

		}

		return matchingEmailList;
	}

	/**
	 * Method that calculates the similarity between the first name of two users and if the similarity score is larger
	 * than threshold, add the email id's to the matching email list. 
	 *
	 * @param newUser
	 * @return
	 */
	private static Set<String> getSimilarFirstNames(UserProfile newUser) {
		Set<String> matchingEmailList = new HashSet<>();
		int count = 0;
		for (String existingFirstName : Program.getFirstNameList()) {
			try {
				double firstnameSimScore = Utilities.getSimilarity(
						newUser.getContactInfo_givenName(), existingFirstName,false);

				if (firstnameSimScore >= ProgramConstants.FIRST_NAME_THRESHOLD) {
					matchingEmailList.addAll(Program.getFirstNameMap().get(
							existingFirstName));
				}
			} catch (Exception ex) {
				System.err.println("First name : " + count + ": "
						+ ex.getMessage());
			}
			count++;
		}
		return matchingEmailList;
	}

	/**
	 * Method that calculates the similarity between the last name of two users and if the similarity score is larger
	 * than threshold, add the email id's to the matching email list. 
	 * 
	 * @param newUser
	 * @return
	 */
	private static Set<String> getSimilarLastNames(UserProfile newUser) {
		Set<String> matchingEmailList = new HashSet<>();
		int count = 0;
		for (String existingLastName : Program.getLastNameList()) {
			try {
				double lastnameSimScore = Utilities.getSimilarity(
						newUser.getContactInfo_familyName(), existingLastName,false);
				if (lastnameSimScore >= ProgramConstants.LAST_NAME_THRESHOLD) {
					matchingEmailList.addAll(Program.getLastNameMap().get(
							existingLastName));
				}
			} catch (Exception ex) {
				System.err.println("Second name " + count + ": "
						+ ex.getMessage());
			}
			count++;
		}
		return matchingEmailList;
	}

	/**
	 * Method that returns the similarity score value between the location of two users.
	 * @param newUser
	 * @param userProfile
	 * @return
	 */
	private static double calculateLocationSimilarityScore(UserProfile newUser,
			UserProfile userProfile) {
		// need to check for location
		double locationSim = 0.0;
		int count = 0;
		for (String string : userProfile.getLocation()) {
			if (newUser.getLocation().contains(string)) {
				count++;
			}
		}

		if (count > 0) {
			locationSim = count
					* 1.0
					/ Math.min(userProfile.getLocation().size(), newUser
							.getLocation().size());
		}
		return locationSim;
	}

	/**
	 * Method that calculates the similarity between the user id's of two users and if the similarity score is larger
	 * than threshold, add the email id's to the matching email list. 
	 * @param newUser
	 * @return
	 */
	private static Set<String> getSimilarUserIds(UserProfile newUser) {
		Set<String> matchingEmailList = new HashSet<>();
		for (UserProfile existingUser : Program.getUserlist().values()) {
			boolean flag = false;
			for (String socialMediaType : existingUser.getMapSocial().keySet()) {
				for (SocialProfile socialProfile : existingUser.getMapSocial()
						.get(socialMediaType)) {

					//Check for patterns like mgambhir & madhurig
					String FirstNameToCheck = newUser.getContactInfo_givenName();
					String LastNameToCheck = newUser.getContactInfo_familyName();
					String firstCharOfFNLN = "";
					String LNfirstCharOfFN = "";

					if (! (FirstNameToCheck.isEmpty())){
						firstCharOfFNLN = FirstNameToCheck.charAt(0) + LastNameToCheck;
					}

					double userNameScore = Utilities.getSimilarity(
							socialProfile.getUserName(), firstCharOfFNLN, false);

					if (userNameScore >= ProgramConstants.USER_NAME_THRESHOLD) {
						matchingEmailList.add(existingUser.getEmailId());
						flag = true;
						break;
					}

					if (! (LastNameToCheck.isEmpty())){
						LNfirstCharOfFN = FirstNameToCheck + LastNameToCheck.charAt(0) ;
					}

					userNameScore = Utilities.getSimilarity(
							socialProfile.getUserName(), LNfirstCharOfFN, false);

					if (userNameScore >= ProgramConstants.USER_NAME_THRESHOLD) {
						matchingEmailList.add(existingUser.getEmailId());
						flag = true;
						break;
					}

					//Full Name Pattern check
					userNameScore = Utilities.getSimilarity(
							socialProfile.getUserName(), newUser.getContactInfo_fullName().replace("\\s+", ""), false);

					if (userNameScore >= ProgramConstants.USER_NAME_THRESHOLD) {
						matchingEmailList.add(existingUser.getEmailId());
						flag = true;
						break;
					}


					for (char c : ProgramConstants.PATTERN) {
						String nameToCompare1 = newUser
								.getContactInfo_givenName()
								+ c
								+ newUser.getContactInfo_familyName();
						String nameToCompare2 = newUser
								.getContactInfo_familyName()
								+ c
								+ newUser.getContactInfo_givenName();
						userNameScore = Utilities.getSimilarity(
								socialProfile.getUserName(), nameToCompare1, false);
						if (userNameScore >= ProgramConstants.USER_NAME_THRESHOLD) {
							matchingEmailList.add(existingUser.getEmailId());
							flag = true;
							break;
						}
						userNameScore = Utilities.getSimilarity(
								socialProfile.getUserName(), nameToCompare2,false);
						if (userNameScore >= ProgramConstants.USER_NAME_THRESHOLD) {
							matchingEmailList.add(existingUser.getEmailId());
							flag = true;
							break;
						}
					}
				}
				if (flag)
					break;
			}
			if (flag)
				break;
		}
	

	return matchingEmailList;
}

/**
 * Method that returns the similarity score value between two user id's.
 * @param newUser
 * @param existingUser
 * @return
 */
private static double calculateUserIdSimilarityScore(UserProfile newUser,
		UserProfile existingUser) {
	double retVal = 0;
	for (String socialMediaType : existingUser.getMapSocial().keySet()) {
		for (SocialProfile socialProfile : existingUser.getMapSocial().get(
				socialMediaType)) {
			for (char c : ProgramConstants.PATTERN) {
				String nameToCompare1 = newUser.getContactInfo_givenName()
						+ c + newUser.getContactInfo_familyName();
				String nameToCompare2 = newUser.getContactInfo_familyName()
						+ c + newUser.getContactInfo_givenName();
				double userNameScore = Utilities.getSimilarity(
						socialProfile.getUserName(), nameToCompare1, false);
				retVal = Math.max(retVal, userNameScore);
				userNameScore = Utilities.getSimilarity(
						socialProfile.getUserName(), nameToCompare2, false);
				retVal = Math.max(retVal, userNameScore);
			}
		}
	}
	return retVal;
}

/**
 * Method that calculate the total similarity score values between two users,
 * based on their email id, user id, first name, last name, gender and location.
 * All these parameters are assigned different weights.
 * 
 * @param user1
 * @param user2
 * @return
 */
public static double caluclateSimilarities(UserProfile user1, UserProfile user2){
	double count = 0;
	double maxSimScore = 0.0;
	double retVal =0;

	/*double emailSim = Utilities.getSimilarity(user1.getEmailId(),
				user2.getEmailId(), true);*/

	double emailSim = 0.0;

	double emailWithoutDomainSim = 0;
	double fnameSim = 0;
	double lnameSim = 0;
	double unameSim = 0;
	double genderSim = 0;
	double locationSim = 0;

	if (user1.getEmailId() != null
			&& !user1.getEmailId().isEmpty()
			&& user2.getEmailId() != null
			&& !user2.getEmailId().isEmpty()) {
		count += ProgramConstants.EMAIL_WEIGHT;
	}

	if (emailSim < ProgramConstants.EMAIL_THRESHOLD) {
		emailWithoutDomainSim = Utilities.getSimilarity(user1
				.getEmailId().split("@")[0], user2.getEmailId()
				.split("@")[0], true);
	}

	if (user1.getContactInfo_givenName() != null
			&& !user1.getContactInfo_givenName().isEmpty()
			&& user2.getContactInfo_givenName() != null
			&& !user2.getContactInfo_givenName().isEmpty()) {
		count += ProgramConstants.FIRST_NAME_WEIGHT;
		fnameSim = Utilities.getSimilarity(
				user1.getContactInfo_givenName(),
				user2.getContactInfo_givenName(), false);
	}

	if (user1.getContactInfo_familyName() != null
			&& !user1.getContactInfo_familyName().isEmpty()
			&& user2.getContactInfo_familyName() != null
			&& !user2.getContactInfo_familyName().isEmpty()) {
		count += ProgramConstants.LAST_NAME_WEIGHT;
		lnameSim = Utilities.getSimilarity(
				user1.getContactInfo_familyName(),
				user2.getContactInfo_familyName(),false);
	}

	unameSim = calculateUserIdSimilarityScore(user2, user1);
	if (unameSim > 0)
		count += ProgramConstants.USERID_WEIGHT;

	if (user1.getDemographics_gender() != Gender.UNKNOWN
			&& user2.getDemographics_gender() != Gender.UNKNOWN) {
		count += ProgramConstants.GENDER_WEIGHT;
		genderSim = user1.getDemographics_gender() == user2
				.getDemographics_gender() ? 1.0 : 0.0;
	}

	if (user1.getLocation().size() != 0
			&& user2.getLocation().size() != 0) {
		count += ProgramConstants.LOCATION_WEIGHT;
		locationSim = calculateLocationSimilarityScore(user1, user2);
	}

	double simScore = ProgramConstants.EMAIL_WEIGHT * Math.max(emailSim, emailWithoutDomainSim) / count
			+ ProgramConstants.FIRST_NAME_WEIGHT * fnameSim / count 
			+ ProgramConstants.LAST_NAME_WEIGHT * lnameSim / count 
			+ ProgramConstants.USERID_WEIGHT * unameSim / count
			+ ProgramConstants.GENDER_WEIGHT * genderSim / count 
			+ ProgramConstants.LOCATION_WEIGHT * locationSim / count;

	if (maxSimScore < simScore) {
		maxSimScore = simScore;
	}
	return maxSimScore;
}	

}
