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
	static final double USER_NAME_THRESHOLD = 0.7;

	static final double EMAIL_WEIGHT = 0.25;
	static final double FIRST_NAME_WEIGHT = 0.20;
	static final double LAST_NAME_WEIGHT = 0.25;
	static final double USERID_WEIGHT = 0.15;
	static final double GENDER_WEIGHT = 0.05;
	static final double LOCATION_WEIGHT = 0.01;

	public static UserProfile userDisambiguator(UserProfile newUser) {

		UserProfile retVal = null;
		Set<String> matchingEmailList = new HashSet<>();

		matchingEmailList.addAll(getEmailSimilarity(newUser));
		matchingEmailList.addAll(getFirstNameSimilarity(newUser));
		matchingEmailList.addAll(getLastNameSimilarity(newUser));
		matchingEmailList.addAll(getUserIDSimilarity(newUser));

		double maxSimScore = 0.0;
		for (String email : matchingEmailList) {
			double count = 0;
			System.out.println(email);

			UserProfile userProfile = Program.userlist.get(email);
			double emailSim = Utilities.getSimilarity(userProfile.getEmailId(),
					newUser.getEmailId());
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
				count += EMAIL_WEIGHT;
			}

			if (emailSim < EMAIL_THRESHOLD) {
				emailWithoutDomainSim = Utilities.getSimilarity(userProfile
						.getEmailId().split("@")[0], newUser.getEmailId()
						.split("@")[0]);
			}

			if (userProfile.getContactInfo_givenName() != null
					&& !userProfile.getContactInfo_givenName().isEmpty()
					&& newUser.getContactInfo_givenName() != null
					&& !newUser.getContactInfo_givenName().isEmpty()) {
				count += FIRST_NAME_WEIGHT;
				fnameSim = Utilities.getSimilarity(
						userProfile.getContactInfo_givenName(),
						newUser.getContactInfo_givenName());
			}

			if (userProfile.getContactInfo_familyName() != null
					&& !userProfile.getContactInfo_familyName().isEmpty()
					&& newUser.getContactInfo_familyName() != null
					&& !newUser.getContactInfo_familyName().isEmpty()) {
				count += LAST_NAME_WEIGHT;
				lnameSim = Utilities.getSimilarity(
						userProfile.getContactInfo_familyName(),
						newUser.getContactInfo_familyName());
			}

			unameSim = calculateUserIDSimilarityScore(newUser, userProfile);
			if (unameSim > 0)
				count += USERID_WEIGHT;

			if (userProfile.getDemographics_gender() != Gender.UNKNOWN
					&& newUser.getDemographics_gender() != Gender.UNKNOWN) {
				count += GENDER_WEIGHT;
				genderSim = userProfile.getDemographics_gender() == newUser
						.getDemographics_gender() ? 1.0 : 0.0;
			}

			if (userProfile.getLocation().size() != 0
					&& newUser.getLocation().size() != 0) {
				count += LOCATION_WEIGHT;
				locationSim = getLocationSimilarity(newUser, userProfile);
			}

			double simScore = EMAIL_WEIGHT * Math.max(emailSim, emailWithoutDomainSim) / count
					+ FIRST_NAME_WEIGHT * fnameSim / count 
					+ LAST_NAME_WEIGHT * lnameSim / count 
					+ USERID_WEIGHT * unameSim / count
					+ GENDER_WEIGHT * genderSim / count 
					+ LOCATION_WEIGHT * locationSim / count;
			
			if (maxSimScore < simScore) {
				maxSimScore = simScore;
				retVal = userProfile;
			}

		}
		System.out.println(maxSimScore);
		return retVal;
	}

	private static Set<String> getEmailSimilarity(UserProfile newUser) {
		Set<String> matchingEmailList = new HashSet<>();
		for (String existingEmail : Program.getEmailList()) {
			double simScore = Utilities.getSimilarity(newUser.getEmailId(),
					existingEmail);
			if (simScore < EMAIL_THRESHOLD) {
				simScore = Utilities.getSimilarity(
						newUser.getEmailId().split("@")[0],
						existingEmail.split("@")[0]);

				if (simScore >= EMAIL_THRESHOLD) {
					matchingEmailList.add(existingEmail);
				}

			} else {
				matchingEmailList.add(existingEmail);
			}

		}

		return matchingEmailList;
	}

	private static Set<String> getFirstNameSimilarity(UserProfile newUser) {
		Set<String> matchingEmailList = new HashSet<>();
		int count = 0;
		for (String existingFirstName : Program.getFirstNameList()) {
			try {
				double firstnameSimScore = Utilities.getSimilarity(
						newUser.getContactInfo_givenName(), existingFirstName);

				if (firstnameSimScore >= FIRST_NAME_THRESHOLD) {
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

	private static Set<String> getLastNameSimilarity(UserProfile newUser) {
		Set<String> matchingEmailList = new HashSet<>();
		int count = 0;
		for (String existingLastName : Program.getLastNameList()) {
			try {
				double lastnameSimScore = Utilities.getSimilarity(
						newUser.getContactInfo_familyName(), existingLastName);
				if (lastnameSimScore >= LAST_NAME_THRESHOLD) {
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

	private static double getLocationSimilarity(UserProfile newUser,
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

	private static Set<String> getUserIDSimilarity(UserProfile newUser) {
		Set<String> matchingEmailList = new HashSet<>();
		char[] pattern = { '\0', '.', '_', '-' };
		for (UserProfile existingUser : Program.getUserlist().values()) {
			boolean flag = false;
			for (String socialMediaType : existingUser.getMapSocial().keySet()) {
				for (SocialProfile socialProfile : existingUser.getMapSocial()
						.get(socialMediaType)) {
					for (char c : pattern) {
						String nameToCompare1 = newUser
								.getContactInfo_givenName()
								+ c
								+ newUser.getContactInfo_familyName();
						String nameToCompare2 = newUser
								.getContactInfo_familyName()
								+ c
								+ newUser.getContactInfo_givenName();
						double userNameScore = Utilities.getSimilarity(
								socialProfile.getUserName(), nameToCompare1);
						if (userNameScore >= USER_NAME_THRESHOLD) {
							matchingEmailList.add(existingUser.getEmailId());
							flag = true;
							break;
						}
						userNameScore = Utilities.getSimilarity(
								socialProfile.getUserName(), nameToCompare2);
						if (userNameScore >= USER_NAME_THRESHOLD) {
							matchingEmailList.add(existingUser.getEmailId());
							flag = true;
							break;
						}
					}
					if (flag)
						break;
				}
				if (flag)
					break;
			}
		}

		return matchingEmailList;
	}

	private static double calculateUserIDSimilarityScore(UserProfile newUser,
			UserProfile existingUser) {
		double retVal = 0;
		char[] pattern = { '\0', '.', '_', '-' };
		for (String socialMediaType : existingUser.getMapSocial().keySet()) {
			for (SocialProfile socialProfile : existingUser.getMapSocial().get(
					socialMediaType)) {
				for (char c : pattern) {
					String nameToCompare1 = newUser.getContactInfo_givenName()
							+ c + newUser.getContactInfo_familyName();
					String nameToCompare2 = newUser.getContactInfo_familyName()
							+ c + newUser.getContactInfo_givenName();
					double userNameScore = Utilities.getSimilarity(
							socialProfile.getUserName(), nameToCompare1);
					retVal = Math.max(retVal, userNameScore);
					userNameScore = Utilities.getSimilarity(
							socialProfile.getUserName(), nameToCompare2);
					retVal = Math.max(retVal, userNameScore);
				}
			}
		}
		return retVal;
	}

	private static double calculateTotalSimilarityScore() {
		return 0;
	}

}
