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
import java.util.List;
import java.util.Map;
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


	static int count = 1000;
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
		
		getSocial("./data/new_social.csv");
		
		//System.out.println(JsonConverter.getJsonString(listMaster));
		
		UserProfile result = Disambiguator.userDisambiguator(temp);
		System.out.println(result.getEmailId() + " " + result.getContactInfo_fullName());
		
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
	
	static UserProfile temp = null;
	static {
		temp = new UserProfile();
		temp.setContactInfo_familyName("Malik");
		temp.setContactInfo_fullName("Manvi Malik");
		temp.setContactInfo_givenName("Manvi");
		temp.setDemographics_gender(Gender.FEMALE);
		temp.setEmailId("003manvi@yahoo.com");
	}
}
