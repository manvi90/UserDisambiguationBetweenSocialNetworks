/**
 * 
 */
package com.shareablee.common;

import com.shareablee.social.Social;
import com.shareablee.social.SocialCSVReader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import com.shareablee.users.User;
import com.shareablee.users.UserCSVReader;

/**
 *
 */
public class Program {

	public static int getCount() {
		return count;
	}
	
	public static Map<String, UserProfile> getUserlist() {
		return userlist;
	}
	
	public static Map<String, List<String>> getLastNameMap() {
		return lastNameMap;
	}

	public static Map<String, List<String>> getFirstNameMap() {
		return firstNameMap;
	}
	
	public static Set<String> getEmailList(){
		return userlist.keySet();
	}
	
	public static Set<String> getFirstNameList(){
		return firstNameMap.keySet();
	}
	public static Set<String> getLastNameList(){
		return lastNameMap.keySet();
	}


	public static List<Cluster> getClusterCollection() {
		return clusterCollection;
	}


	static int count = 10;
	static Map<String, UserProfile> userlist = new HashMap<>();
	static Map<String, UserProfileMaster> listMaster = new HashMap<>();
	static Map<String,List<String>> lastNameMap = new HashMap<>();
	static Map<String,List<String>> firstNameMap = new HashMap<>();
	static List<Cluster> clusterCollection = new ArrayList<Cluster>();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CSVReader<User> scsvr = new UserCSVReader();
		List<User> users = scsvr.getData("./data/new_primary.csv");
		
		for(User user : users) {
			UserProfile temp = new UserProfile();
			UserProfileMaster tempMaster = new UserProfileMaster();
			
			temp.setContactInfo_familyName(user.getContactInfo_familyName());
			temp.setContactInfo_fullName(user.getContactInfo_fullName());
			temp.setContactInfo_givenName(user.getContactInfo_givenName());
			temp.setEmailId(user.getEmailId());
			temp.setDemographics_gender(user.getDemographic().getDemographics_gender());
			temp.setLocation(user.getDemographic().getLocation().getLocationMap());
			
			tempMaster.setUser(user);

			userlist.put(temp.getEmailId(), temp);
			listMaster.put(user.getEmailId(), tempMaster);
			
			//Last Name and Email HashMap
			if (lastNameMap.get(temp.getContactInfo_familyName()) == null){
				lastNameMap.put(temp.getContactInfo_familyName(), new ArrayList<>());
			}
			
			lastNameMap.get(temp.getContactInfo_familyName()).add(temp.getEmailId());
			
			//First Name and Email HashMap
			if (firstNameMap.get(temp.getContactInfo_givenName()) == null){
				firstNameMap.put(temp.getContactInfo_givenName(), new ArrayList<>());
			}
			
			firstNameMap.get(temp.getContactInfo_givenName()).add(temp.getEmailId());
			
			
		}
		
		System.out.println("Read primary data");
		getSocial("./data/new_social.csv");
		
		System.out.println("Read social data");
		//System.out.println(JsonConverter.getJsonString(listMaster));
		
		for(UserProfile user : userlist.values()) {
			processUser(user);		}
		
		System.out.println("Initial collection size"+ clusterCollection.size());
		
		int counter =0;
		for(Cluster c : getClusterCollection()){
			System.out.println("Cluster No" + ++counter);
			for(UserProfile u : c.getUsers()){
				System.out.println(u.getEmailId() + ", " + u.getContactInfo_fullName() +", " + u.getContactInfo_givenName() 
							+ "  " +  u.getContactInfo_familyName() + " " + u.getDemographics_gender());
			}
			System.out.println();
			System.out.println();
		}
		findIdenticalProfile(temp);
		
		System.out.println("updated collection size"+ clusterCollection.size());
		
		//testing
		UserProfile newUser = new UserProfile();

		Scanner in = new Scanner(System.in);
		int input = 1;
		while(input != 0){
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
			
			switch(input) {
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
				System.out.println("Press 1 for Male, 2 for Female 3 for others");
				int gen = Integer.parseInt(in.nextLine());
				switch(gen) {
				case 1: 
					newUser.setDemographics_gender(Gender.MALE); break;
				case 2:
					newUser.setDemographics_gender(Gender.FEMALE); break;
				default:
					newUser.setDemographics_gender(Gender.UNKNOWN); break;
				}
			case 6 :
				findIdenticalProfile(newUser); break;
			default:
				newUser = new UserProfile();
			}
		}
		
		in.close();
		
	}

	/**
	 * 
	 * @param filePath
	 */
	public static void getSocial(String filePath) {
		SocialCSVReader reader = new SocialCSVReader();
		
		if(filePath.isEmpty()) throw new IllegalArgumentException("No file name specified");
		BufferedReader bufferedReader = null; 
		int localcount = 0;
		try {
			bufferedReader = new BufferedReader(new FileReader(filePath));
			String inputLine = "";
			while(true) {
				inputLine = bufferedReader.readLine();
				if (inputLine == null) break;
				if (inputLine.isEmpty())  continue;
				localcount++;
				Social social = reader.parseLine(inputLine);
				if(social != null) {
					UserProfileMaster tempMaster = listMaster.get(social.getEmailId());
					if(tempMaster != null) {
						if(tempMaster.getMapSocial().get(social.getTypeId()) == null) {
							tempMaster.getMapSocial().put(social.getTypeId(), new ArrayList<>());
						}
						List<Social> socialList = tempMaster.getMapSocial().get(social.getTypeId());
						socialList.add(social);
					}
					
					UserProfile temp = userlist.get(social.getEmailId());
					if(temp != null) {
						if(temp.getMapSocial().get(social.getTypeId()) == null) {
							temp.getMapSocial().put(social.getTypeId(), new ArrayList<>());
						}
						List<SocialProfile> socialList = temp.getMapSocial().get(social.getTypeId());
						SocialProfile socialProfile = new SocialProfile();
						socialProfile.setSocialMediaId(social.getSocialMediaId());
						socialProfile.setTypeId(social.getTypeId());
						socialProfile.setTypeName(social.getTypeName());
						socialProfile.setUserName(social.getUserName());
						socialList.add(socialProfile);
					}
				}
				if(localcount > count) break;
			}
		} catch (FileNotFoundException ex) {
			System.err.println(ex.getMessage());
		} catch (IOException ex) {
			System.err.println("Unable to read the file : " + ex.toString());
		} catch (Exception ex) {
			System.err.println("General exception : " + ex.toString());
		} finally {
			if(bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					System.err.println(e.getStackTrace().toString());
				}
			}
		}
	}
	
	/**
	 * Method to find the matching cluster for the new user
	 * @param user
	 */
	public static void processUser(UserProfile newUser){
		List<Cluster> clusterList = ClusterFormation.findCluster(newUser);
		if (clusterList == null || clusterList.isEmpty() || clusterList.size() <= 0){
			Cluster tempcluster = new Cluster();
			clusterCollection.add(tempcluster);
			clusterList.add(tempcluster);
		}
		for(Cluster cluster : clusterList){
			ClusterFormation.addToCluster(cluster, newUser);
		}
	}
	
	
	public static Set<UserProfile> findIdenticalProfile(UserProfile newUser){
		List<Cluster> clusterList = ClusterFormation.findCluster(newUser);
		if (clusterList == null || clusterList.isEmpty() || clusterList.size() <= 0){
			Cluster tempcluster = new Cluster();
			clusterCollection.add(tempcluster);
			clusterList.add(tempcluster);
		}
		
		
		Set<UserProfile> retVal = null;
		userlist = new HashMap<>();
		lastNameMap = new HashMap<>();
		firstNameMap = new HashMap<>();
		
		for (Cluster cluster : clusterList){
			for (UserProfile user : cluster.getUsers()){
				userlist.put(user.getEmailId(), user);
				
				if(lastNameMap.get(user.getContactInfo_familyName()) == null) {
					lastNameMap.put(user.getContactInfo_familyName(), new ArrayList<>());
				}
				lastNameMap.get(user.getContactInfo_familyName()).add(user.getEmailId());
				
				if(firstNameMap.get(user.getContactInfo_givenName()) == null) {
					firstNameMap.put(user.getContactInfo_givenName(), new ArrayList<>());
				}
				firstNameMap.get(user.getContactInfo_givenName()).add(user.getEmailId());
			}
		}
		
		retVal = Disambiguator.userDisambiguator(newUser);
		for(UserProfile result : retVal) {
			System.out.println(result.getEmailId() + " " + result.getContactInfo_fullName() + " " + result.getSimScore());
			for(String string : result.getMapSocial().keySet())
				System.out.println(string);
		}
		
		for(Cluster cluster : clusterList){
			ClusterFormation.addToCluster(cluster, newUser);
		}

		System.out.println("Ended");
		return retVal;
	}
	
	//test code
	static UserProfile temp = null;
	static {
		temp = new UserProfile();
		temp.setContactInfo_familyName("smith");
		temp.setContactInfo_fullName("lisa shaver smith");
		temp.setContactInfo_givenName("lisa");
		temp.setDemographics_gender(Gender.FEMALE);
		Set<String> location = new HashSet<>();
		location.add("debary");
		location.add("fl");
		location.add("florida");
		location.add("united states");
		location.add("north america");
		location.add("us");
		location.add("volusia");
		//temp.setLocation(location);
		temp.setEmailId("smith@gmail.com");
		System.out.println("started");
	}
}
