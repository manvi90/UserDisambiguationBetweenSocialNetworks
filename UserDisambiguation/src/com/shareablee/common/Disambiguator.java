/**
 * 
 */
package com.shareablee.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.shareablee.socialprofile.Social;
import com.shareablee.userprofile.User;
import com.shareablee.utils.Gender;
import com.shareablee.utils.Util;

/**
 * @author Madhuri
 *
 */
public class Disambiguator {

	/**
	 * Method adds the profile details to the various internal maps against
	 * which the similarity scores have to be performed
	 * 
	 * @param profile
	 */
	public void addProfile(Profile profile) {
		User user = profile.getUser();
		this.emailMap.put(user.getEmailId(), profile);

		if (this.lastNameMap.get(user.getContactInfo_familyName()) == null) {
			this.lastNameMap.put(user.getContactInfo_familyName(),
					new ArrayList<>());
		}
		this.lastNameMap.get(user.getContactInfo_familyName()).add(
				user.getEmailId());

		if (this.firstNameMap.get(user.getContactInfo_givenName()) == null) {
			this.firstNameMap.put(user.getContactInfo_givenName(),
					new ArrayList<>());
		}
		this.firstNameMap.get(user.getContactInfo_givenName()).add(
				user.getEmailId());
	}

	/**
	 * Finds the profiles similar to give new profile
	 * 
	 * @param newProfile
	 * @return Collection of profiles similar to the newProfile
	 */
	public Set<Profile> userDisambiguator(Profile newProfile) {

		Set<Profile> retVal = new HashSet<>();
		Set<String> matchingEmails = new HashSet<>();

		// get all the potential emails that could have similar profiles
		matchingEmails.addAll(getSimilarEmails(newProfile));
		matchingEmails.addAll(getSimilarFirstNames(newProfile));
		matchingEmails.addAll(getSimilarLastNames(newProfile));
		matchingEmails.addAll(getSimilarUserIds(newProfile));

		for (String email : matchingEmails) {
			double count = 0;

			Profile profile = this.emailMap.get(email);
			/*
			 * double emailSim = Util.getDiceSimilarity(profile.getUser()
			 * .getEmailId(), newProfile.getUser().getEmailId());
			 */
			double emailSim = 0;
			double emailWithoutDomainSim = 0;
			double fnameSim = 0;
			double lnameSim = 0;
			double unameSim = 0;
			double genderSim = 0;
			double locationSim = 0;

			if (profile.getUser().getEmailId() != null
					&& !profile.getUser().getEmailId().isEmpty()
					&& newProfile.getUser().getEmailId() != null
					&& !newProfile.getUser().getEmailId().isEmpty()) {
				count += Constants.EMAIL_WEIGHT;
			}
			
			//Calculate the similarity without the domain of email id
			if (emailSim < Constants.EMAIL_THRESHOLD) {
				emailWithoutDomainSim = Util.getDiceSimilarity(profile
						.getUser().getEmailId().split("@")[0], newProfile
						.getUser().getEmailId().split("@")[0]);
			}
			
			//Calculate the similarity for Last Name
			if (profile.getUser().getContactInfo_givenName() != null
					&& !profile.getUser().getContactInfo_givenName().isEmpty()
					&& newProfile.getUser().getContactInfo_givenName() != null
					&& !newProfile.getUser().getContactInfo_givenName()
							.isEmpty()) {
				count += Constants.FIRST_NAME_WEIGHT;
				fnameSim = Util.getJaroWinklerDistanceSimilarity(profile
						.getUser().getContactInfo_givenName(), newProfile
						.getUser().getContactInfo_givenName());
			}

			//Calculate the similarity for First Name
			if (profile.getUser().getContactInfo_familyName() != null
					&& !profile.getUser().getContactInfo_familyName().isEmpty()
					&& newProfile.getUser().getContactInfo_familyName() != null
					&& !newProfile.getUser().getContactInfo_familyName()
							.isEmpty()) {
				count += Constants.LAST_NAME_WEIGHT;
				lnameSim = Util.getJaroWinklerDistanceSimilarity(profile
						.getUser().getContactInfo_familyName(), newProfile
						.getUser().getContactInfo_familyName());
			}

			//Calculate the similarity for User Id
			unameSim = calculateUserIdSimilarityScore(newProfile, profile);
			if (unameSim > 0)
				count += Constants.USERID_WEIGHT;

			if (profile.getUser().getDemographics_gender() != Gender.UNKNOWN
					&& newProfile.getUser().getDemographics_gender() != Gender.UNKNOWN) {
				count += Constants.GENDER_WEIGHT;
				genderSim = profile.getUser().getDemographics_gender() == newProfile
						.getUser().getDemographics_gender() ? 1.0 : 0.0;
			}
			
			//Calculate the similarity for the location
			if (profile.getUser().getLocation().size() != 0
					&& newProfile.getUser().getLocation().size() != 0) {
				count += Constants.LOCATION_WEIGHT;
				locationSim = calculateLocationSimilarityScore(newProfile,
						profile);
			}

			//Calculate the weighted similarity score
			double simScore = Constants.EMAIL_WEIGHT
					* Math.max(emailSim, emailWithoutDomainSim) / count
					+ Constants.FIRST_NAME_WEIGHT * fnameSim / count
					+ Constants.LAST_NAME_WEIGHT * lnameSim / count
					+ Constants.USERID_WEIGHT * unameSim / count
					+ Constants.GENDER_WEIGHT * genderSim / count
					+ Constants.LOCATION_WEIGHT * locationSim / count;

			if (Constants.USER_SIMILARITY_THRESHOLD <= simScore) {
				retVal.add(profile);
				profile.getUser().setSimilarityScore(simScore);

			}

		}
		return retVal;
	}

	/**
	 * Method that calculates the similarity between the two emails and if it
	 * lies in the threshold, add it to the matching email list
	 * 
	 * @param newProfile
	 * @return Collection of similar Emails
	 */
	private Set<String> getSimilarEmails(Profile newProfile) {
		Set<String> matchingEmailList = new HashSet<>();
		for (String existingEmail : this.emailMap.keySet()) {
			double simScore = Util.getDiceSimilarity(newProfile.getUser()
					.getEmailId().split("@")[0], existingEmail.split("@")[0]);

			if (simScore >= Constants.EMAIL_THRESHOLD) {
				matchingEmailList.add(existingEmail);
			}
		}

		return matchingEmailList;
	}

	/**
	 * Method that calculates the similarity between the first name of two users
	 * and if the similarity score is larger than threshold, add the email id's
	 * to the matching email list.
	 *
	 * @param newProfile
	 * @return Collection of similar Emails
	 */
	private Set<String> getSimilarFirstNames(Profile newProfile) {
		Set<String> matchingEmailList = new HashSet<>();
		int count = 0;
		for (String existingFirstName : this.firstNameMap.keySet()) {
			try {
				double firstnameSimScore = Util
						.getJaroWinklerDistanceSimilarity(newProfile.getUser()
								.getContactInfo_givenName(), existingFirstName);

				if (firstnameSimScore >= Constants.FIRST_NAME_THRESHOLD) {
					matchingEmailList.addAll(this.firstNameMap
							.get(existingFirstName));
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
	 * Method that calculates the similarity between the last name of two users
	 * and if the similarity score is larger than threshold, add the email id's
	 * to the matching email list.
	 * 
	 * @param newProfile
	 * @return Collection of similar Emails
	 */
	private Set<String> getSimilarLastNames(Profile newProfile) {
		Set<String> matchingEmailList = new HashSet<>();
		int count = 0;
		for (String existingLastName : this.lastNameMap.keySet()) {
			try {
				double lastnameSimScore = Util
						.getJaroWinklerDistanceSimilarity(newProfile.getUser()
								.getContactInfo_familyName(), existingLastName);
				if (lastnameSimScore >= Constants.LAST_NAME_THRESHOLD) {
					matchingEmailList.addAll(this.lastNameMap
							.get(existingLastName));
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
	 * Method that returns the similarity score value between the location of
	 * two profiles.
	 * 
	 * @param newProfile
	 * @param profile
	 * @return Similarity between profiles based on location
	 */
	private double calculateLocationSimilarityScore(Profile newProfile,
			Profile profile) {
		double locationSim = 0.0;
		int count = 0;
		for (String string : profile.getUser().getLocation()) {
			if (newProfile.getUser().getLocation().contains(string)) {
				count++;
			}
		}

		//Location Similarity calculated as Intersection of matching location 
		// divided by the minimum length of the two location string
		if (count > 0) {
			locationSim = count
					* 1.0
					/ Math.min(profile.getUser().getLocation().size(),
							newProfile.getUser().getLocation().size());
		}
		return locationSim;
	}

	/**
	 * Method that calculates the similarity between the user id's of two users
	 * and if the similarity score is larger than threshold, add the email id's
	 * to the matching email list.
	 * 
	 * @param newProfile
	 * @return Collection of similar Emails based on userId
	 */
	private Set<String> getSimilarUserIds(Profile newProfile) {
		Set<String> matchingEmailList = new HashSet<>();
		for (Profile existingProfile : this.emailMap.values()) {
			boolean flag = false;
			for (String socialMediaType : existingProfile.getMapSocial()
					.keySet()) {
				for (Social social : existingProfile.getMapSocial().get(
						socialMediaType)) {

					// Check for patterns like mgambhir & madhurig
					String firstNameToCheck = newProfile.getUser()
							.getContactInfo_givenName();
					String lastNameToCheck = newProfile.getUser()
							.getContactInfo_familyName();
					String firstCharOfFNLN = "";
					String lNfirstCharOfFN = "";

					if (!(firstNameToCheck.isEmpty())) {
						firstCharOfFNLN = firstNameToCheck.charAt(0)
								+ lastNameToCheck;
					}

					double userNameScore = Util
							.getJaroWinklerDistanceSimilarity(
									social.getUserName(), firstCharOfFNLN);

					if (userNameScore >= Constants.USER_NAME_THRESHOLD) {
						matchingEmailList.add(existingProfile.getUser()
								.getEmailId());
						flag = true;
						break;
					}

					if (!(lastNameToCheck.isEmpty())) {
						lNfirstCharOfFN = firstNameToCheck
								+ lastNameToCheck.charAt(0);
					}

					userNameScore = Util.getJaroWinklerDistanceSimilarity(
							social.getUserName(), lNfirstCharOfFN);

					if (userNameScore >= Constants.USER_NAME_THRESHOLD) {
						matchingEmailList.add(existingProfile.getUser()
								.getEmailId());
						flag = true;
						break;
					}

					// Full Name Pattern check
					userNameScore = Util.getJaroWinklerDistanceSimilarity(
							social.getUserName(),
							newProfile.getUser().getContactInfo_fullName()
									.replace("\\s+", ""));

					if (userNameScore >= Constants.USER_NAME_THRESHOLD) {
						matchingEmailList.add(existingProfile.getUser()
								.getEmailId());
						flag = true;
						break;
					}

					for (char c : Constants.PATTERN) {
						String nameToCompare1 = newProfile.getUser()
								.getContactInfo_givenName()
								+ c
								+ newProfile.getUser()
										.getContactInfo_familyName();
						String nameToCompare2 = newProfile.getUser()
								.getContactInfo_familyName()
								+ c
								+ newProfile.getUser()
										.getContactInfo_givenName();
						userNameScore = Util.getJaroWinklerDistanceSimilarity(
								social.getUserName(), nameToCompare1);
						if (userNameScore >= Constants.USER_NAME_THRESHOLD) {
							matchingEmailList.add(existingProfile.getUser()
									.getEmailId());
							flag = true;
							break;
						}
						userNameScore = Util.getJaroWinklerDistanceSimilarity(
								social.getUserName(), nameToCompare2);
						if (userNameScore >= Constants.USER_NAME_THRESHOLD) {
							matchingEmailList.add(existingProfile.getUser()
									.getEmailId());
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
	 * 
	 * @param newProfile
	 * @param existingProfile
	 * @return Similarity score between two profiles based on UsedId
	 */
	private double calculateUserIdSimilarityScore(Profile newProfile,
			Profile existingProfile) {
		double retVal = 0;
		for (String socialMediaType : existingProfile.getMapSocial().keySet()) {
			for (Social social : existingProfile.getMapSocial().get(
					socialMediaType)) {
				
				//Check the simalarity with the pattern
				for (char c : Constants.PATTERN) {
					String nameToCompare1 = newProfile.getUser()
							.getContactInfo_givenName()
							+ c
							+ newProfile.getUser().getContactInfo_familyName();
					String nameToCompare2 = newProfile.getUser()
							.getContactInfo_familyName()
							+ c
							+ newProfile.getUser().getContactInfo_givenName();
					double userNameScore = Util
							.getJaroWinklerDistanceSimilarity(
									social.getUserName(), nameToCompare1);
					retVal = Math.max(retVal, userNameScore);
					userNameScore = Util.getJaroWinklerDistanceSimilarity(
							social.getUserName(), nameToCompare2);
					retVal = Math.max(retVal, userNameScore);
				}
			}
		}
		return retVal;
	}

	/**
	 * Method that calculate the total similarity score values between two
	 * users, based on their email id, user id, first name, last name, gender
	 * and location. All these parameters are assigned different weights.
	 * 
	 * @param profile1
	 * @param profile2
	 * @return Similarity score between two profiles
	 */
	public double caluclateSimilarities(Profile profile1, Profile profile2) {
		double count = 0;
		double maxSimScore = 0.0;

		/*
		 * double emailSim = Utilities.getSimilarity(profile1.getEmailId(),
		 * user2.getEmailId(), true);
		 */

		double emailSim = 0.0;

		double emailWithoutDomainSim = 0;
		double fnameSim = 0;
		double lnameSim = 0;
		double unameSim = 0;
		double genderSim = 0;
		double locationSim = 0;

		if (profile1.getUser().getEmailId() != null
				&& !profile1.getUser().getEmailId().isEmpty()
				&& profile2.getUser().getEmailId() != null
				&& !profile2.getUser().getEmailId().isEmpty()) {
			count += Constants.EMAIL_WEIGHT;
		}

		if (emailSim < Constants.EMAIL_THRESHOLD) {
			emailWithoutDomainSim = Util.getDiceSimilarity(profile1.getUser()
					.getEmailId().split("@")[0], profile2.getUser()
					.getEmailId().split("@")[0]);
		}

		if (profile1.getUser().getContactInfo_givenName() != null
				&& !profile1.getUser().getContactInfo_givenName().isEmpty()
				&& profile2.getUser().getContactInfo_givenName() != null
				&& !profile2.getUser().getContactInfo_givenName().isEmpty()) {
			count += Constants.FIRST_NAME_WEIGHT;
			fnameSim = Util.getJaroWinklerDistanceSimilarity(profile1.getUser()
					.getContactInfo_givenName(), profile2.getUser()
					.getContactInfo_givenName());
		}

		if (profile1.getUser().getContactInfo_familyName() != null
				&& !profile1.getUser().getContactInfo_familyName().isEmpty()
				&& profile2.getUser().getContactInfo_familyName() != null
				&& !profile2.getUser().getContactInfo_familyName().isEmpty()) {
			count += Constants.LAST_NAME_WEIGHT;
			lnameSim = Util.getJaroWinklerDistanceSimilarity(profile1.getUser()
					.getContactInfo_familyName(), profile2.getUser()
					.getContactInfo_familyName());
		}

		unameSim = calculateUserIdSimilarityScore(profile2, profile1);
		if (unameSim > 0)
			count += Constants.USERID_WEIGHT;

		if (profile1.getUser().getDemographics_gender() != Gender.UNKNOWN
				&& profile2.getUser().getDemographics_gender() != Gender.UNKNOWN) {
			count += Constants.GENDER_WEIGHT;
			genderSim = profile1.getUser().getDemographics_gender() == profile2
					.getUser().getDemographics_gender() ? 1.0 : 0.0;
		}

		if (profile1.getUser().getLocation().size() != 0
				&& profile2.getUser().getLocation().size() != 0) {
			count += Constants.LOCATION_WEIGHT;
			locationSim = calculateLocationSimilarityScore(profile1, profile2);
		}

		double simScore = Constants.EMAIL_WEIGHT
				* Math.max(emailSim, emailWithoutDomainSim) / count
				+ Constants.FIRST_NAME_WEIGHT * fnameSim / count
				+ Constants.LAST_NAME_WEIGHT * lnameSim / count
				+ Constants.USERID_WEIGHT * unameSim / count
				+ Constants.GENDER_WEIGHT * genderSim / count
				+ Constants.LOCATION_WEIGHT * locationSim / count;

		if (maxSimScore < simScore) {
			maxSimScore = simScore;
		}
		return maxSimScore;
	}

	private Map<String, Profile> emailMap = new HashMap<>();
	private Map<String, List<String>> lastNameMap = new HashMap<>();
	private Map<String, List<String>> firstNameMap = new HashMap<>();
}
