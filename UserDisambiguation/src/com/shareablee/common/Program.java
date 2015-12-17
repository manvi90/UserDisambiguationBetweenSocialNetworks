/**
 * 
 */
package com.shareablee.common;

import com.shareablee.cluster.Cluster;
import com.shareablee.cluster.ClusterFormation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import com.shareablee.socialprofile.SocialCSVReader;
import com.shareablee.socialprofile.SocialMaster;
import com.shareablee.userprofile.UserMaster;
import com.shareablee.userprofile.UserCSVReader;
import com.shareablee.userprofile.User;
import com.shareablee.utils.CSVReader;
import com.shareablee.utils.Gender;

/**
 *
 */
public class Program {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CSVReader<UserMaster> ucsvr = new UserCSVReader();
		List<UserMaster> userMasters = ucsvr.getData("./data/new_primary.csv");
		Map<String, Object> users = fillMasterList(userMasters);

		System.out.println("Read primary data");

		CSVReader<SocialMaster> scsvr = new SocialCSVReader();
		scsvr.getData("./data/new_social.csv", users);

		System.out.println("Read social data");

		for (Object user : users.values()) {
			processProfile((Profile) user);
		}

		// printing cluster details for verification
		System.out.println("Initial collection size "
				+ clusterCollection.size());

		int counter = 0;
		for (Cluster cluster : getClusterCollection()) {
			System.out.println("Cluster No " + ++counter);
			for (Profile profile : cluster.getProfiles()) {
				System.out.println(profile.getUser().getEmailId() + ", "
						+ profile.getUser().getContactInfo_fullName() + ", "
						+ profile.getUser().getContactInfo_givenName() + "  "
						+ profile.getUser().getContactInfo_familyName() + ", "
						+ profile.getUser().getDemographics_gender());
			}
			System.out.println();
			System.out.println();
		}
		
		// process the dummy profile
		findIdenticalProfile(testProfile);

		System.out.println("updated collection size "
				+ clusterCollection.size());

		// testing one by one (take profiles as input from the users)
		User newUser = new User();

		Scanner in = new Scanner(System.in);
		int input = 1;
		while (input != 0) {
			System.out.println("1.Email");
			System.out.println("2.First name");
			System.out.println("3.Last Name");
			System.out.println("4.Location");
			System.out.println("5.Gender");
			System.out.println("6.Execute");
			System.out.println("7.Clear All");
			System.out.println("0.Exit");
			System.out.println("Enter choice:");
			input = Integer.parseInt(in.nextLine());

			switch (input) {
			case 1:
				System.out.println("Enter mail : ");
				newUser.setEmailId(in.nextLine());
				break;
			case 2:
				System.out.println("Enter first name : ");
				newUser.setContactInfo_givenName(in.nextLine());
				break;
			case 3:
				System.out.println("Enter last name : ");
				newUser.setContactInfo_familyName(in.nextLine());
				break;
			case 4:
				System.out.println("Enter location");
				String temp = in.nextLine();
				newUser.getLocation().add(temp);
				break;
			case 5:
				System.out
						.println("Press 1 for Male, 2 for Female 3 for others");
				int gen = Integer.parseInt(in.nextLine());
				switch (gen) {
				case 1:
					newUser.setDemographics_gender(Gender.MALE);
					break;
				case 2:
					newUser.setDemographics_gender(Gender.FEMALE);
					break;
				default:
					newUser.setDemographics_gender(Gender.UNKNOWN);
					break;
				}
			case 6:
				Profile newProfile = new Profile();
				newProfile.setUser(newUser);
				findIdenticalProfile(newProfile);
				break;
			default:
				newUser = new User();
			}
		}

		in.close();

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
		for (Profile profile : retVal) {
			System.out.println(profile.getUser().getEmailId() + " "
					+ profile.getUser().getContactInfo_fullName() + " "
					+ profile.getUser().getSimilarityScore());
			for (String string : profile.getMapSocial().keySet())
				System.out.println(string);
		}

		for (Cluster cluster : clusterList) {
			ClusterFormation.addToCluster(cluster, newUser);
		}

		System.out.println("Ended");
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

	private static int count = 10;
	private static Map<String, ProfileMaster> listMaster = new HashMap<>();
	private static List<Cluster> clusterCollection = new ArrayList<Cluster>();

	// test code
	private static Profile testProfile = null;
	static {
		testProfile = new Profile();
		testProfile.setUser(new User());
		testProfile.getUser().setContactInfo_familyName("smith");
		testProfile.getUser().setContactInfo_fullName("lisa shaver smith");
		testProfile.getUser().setContactInfo_givenName("lisa");
		testProfile.getUser().setDemographics_gender(Gender.FEMALE);
		Set<String> location = new HashSet<>();
		location.add("debary");
		location.add("fl");
		location.add("florida");
		location.add("united states");
		location.add("north america");
		location.add("us");
		location.add("volusia");
		// testProfile.setLocation(location);
		testProfile.getUser().setEmailId("smith@gmail.com");
		System.out.println("started");
	}
}
