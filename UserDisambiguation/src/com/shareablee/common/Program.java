/**
 * 
 */
package com.shareablee.common;

import com.shareablee.cluster.Cluster;
import com.shareablee.cluster.ClusterFormation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import com.shareablee.socialprofile.SocialCSVReader;
import com.shareablee.socialprofile.SocialMaster;
import com.shareablee.testing.Test;
import com.shareablee.testing.TestProfile;
import com.shareablee.userprofile.UserMaster;
import com.shareablee.userprofile.UserCSVReader;
import com.shareablee.userprofile.User;
import com.shareablee.utils.CSVReader;

/**
 *
 */
public class Program {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CSVReader<UserMaster> ucsvr = new UserCSVReader();
		List<UserMaster> userMasters = ucsvr.getData(Constants.USERS_FILE_PATH);
		Map<String, Object> users = fillMasterList(userMasters);

		System.out.println("Read primary data");

		CSVReader<SocialMaster> scsvr = new SocialCSVReader();
		scsvr.getData(Constants.SOCIAL_FILE_PATH, users);

		System.out.println("Read social data");

		for (Object user : users.values()) {
			processProfile((Profile) user);
		}

		System.out.println("Clusters formed");

		// printing cluster details for verification
		/*
		 * int counter = 0; for (Cluster cluster : getClusterCollection()) {
		 * System.out.println("Cluster No " + ++counter); for (Profile profile :
		 * cluster.getProfiles()) {
		 * System.out.println(profile.getUser().getEmailId() + ", " +
		 * profile.getUser().getContactInfo_fullName() + ", " +
		 * profile.getUser().getContactInfo_givenName() + "  " +
		 * profile.getUser().getContactInfo_familyName() + ", " +
		 * profile.getUser().getDemographics_gender()); } System.out.println();
		 * System.out.println(); }
		 */

		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.println();
			System.out.println("Enter choice 0 - for break");
			int breakCond = 1;
			breakCond = Integer.parseInt(sc.nextLine());
			if (breakCond == 0) {
				break;
			}
			Test test = new Test();
			System.out.println("Read the testing data.");
			System.out.println("-----------------------");
			List<TestProfile> testProfiles = test
					.getTestData(Constants.TEST_FILE_PATH);

			int success = 0;
			for (TestProfile testProfile : testProfiles) {
				Set<Profile> results = findIdenticalProfile(testProfile
						.getProfile());
				if (results != null) {
					if ((results.isEmpty() && !testProfile.isMatch())
							|| (!results.isEmpty() && testProfile.isMatch())) {
						success++;
						testProfile.setSuccess(true);
					}
				}

				System.out.println("Testing : "
						+ testProfile.getProfile().getUser().toString());
				System.out.println("Result : " + testProfile.isSuccess());

				for (Profile profile : results) {
					System.out.println(profile.getUser().getEmailId() + " "
							+ profile.getUser().getContactInfo_fullName() + " "
							+ profile.getUser().getSimilarityScore());
					for (String string : profile.getMapSocial().keySet())
						System.out.println(string);
				}

				System.out.println("###################################\n");

			}

			System.out.println("Success = "
					+ (100.0 * success / testProfiles.size()));

			System.out.println();
		}

		sc.close();
	}

	public static int getCount() {
		return count;
	}

	public static List<Cluster> getClusterCollection() {
		return clusterCollection;
	}

	public static Map<String, ProfileMaster> getListMaster() {
		return listMaster;
	}

	/**
	 * Method to find the matching cluster for the new user
	 * 
	 * @param user
	 */
	private static void processProfile(Profile newProfile) {
		List<Cluster> clusterList = ClusterFormation.findCluster(newProfile);
		if (clusterList == null || clusterList.isEmpty()
				|| clusterList.size() <= 0) {
			Cluster tempcluster = new Cluster();
			clusterCollection.add(tempcluster);
			clusterList.add(tempcluster);
		}
		for (Cluster cluster : clusterList) {
			ClusterFormation.addToCluster(cluster, newProfile);
		}
	}

	private static Set<Profile> findIdenticalProfile(Profile newUser) {
		List<Cluster> clusterList = ClusterFormation.findCluster(newUser);
		if (clusterList == null || clusterList.isEmpty()
				|| clusterList.size() <= 0) {
			Cluster tempcluster = new Cluster();
			clusterCollection.add(tempcluster);
			clusterList.add(tempcluster);
		}

		Set<Profile> retVal = null;

		Disambiguator disambiguator = new Disambiguator();
		for (Cluster cluster : clusterList) {
			for (Profile profile : cluster.getProfiles()) {
				disambiguator.addProfile(profile);
			}
		}

		retVal = disambiguator.userDisambiguator(newUser);

		// to be un-commented if the test user has to be added to the cluster
		/*
		 * for (Cluster cluster : clusterList) {
		 * ClusterFormation.addToCluster(cluster, newUser); }
		 */

		return retVal;
	}

	/**
	 * 
	 * @param userMasters
	 * @return
	 */
	private static Map<String, Object> fillMasterList(
			List<UserMaster> userMasters) {
		Map<String, Object> users = new HashMap<>();
		for (UserMaster userMaster : userMasters) {
			User user = new User();
			ProfileMaster profileMaster = new ProfileMaster();
			user.setContactInfo_familyName(userMaster
					.getContactInfo_familyName());
			user.setContactInfo_fullName(userMaster.getContactInfo_fullName());
			user.setContactInfo_givenName(userMaster.getContactInfo_givenName());
			user.setEmailId(userMaster.getEmailId());
			user.setDemographics_gender(userMaster.getDemographic()
					.getDemographics_gender());
			user.setLocation(userMaster.getDemographic().getLocation()
					.getLocationMap());

			profileMaster.setUser(userMaster);
			Profile profile = new Profile();
			profile.setUser(user);
			users.put(user.getEmailId(), profile);
			listMaster.put(userMaster.getEmailId(), profileMaster);

		}

		return users;
	}

	private static int count = 1000;
	private static Map<String, ProfileMaster> listMaster = new HashMap<>();
	private static List<Cluster> clusterCollection = new ArrayList<Cluster>();

	// test code
	/*
	 * private static Profile testProfile = null; static { testProfile = new
	 * Profile(); testProfile.setUser(new User());
	 * testProfile.getUser().setContactInfo_familyName("smith");
	 * testProfile.getUser().setContactInfo_fullName("lisa shaver smith");
	 * testProfile.getUser().setContactInfo_givenName("lisa");
	 * testProfile.getUser().setDemographics_gender(Gender.FEMALE); Set<String>
	 * location = new HashSet<>(); location.add("debary"); location.add("fl");
	 * location.add("florida"); location.add("united states");
	 * location.add("north america"); location.add("us");
	 * location.add("volusia"); // testProfile.setLocation(location);
	 * testProfile.getUser().setEmailId("smith@gmail.com");
	 * System.out.println("started"); }
	 */
}
