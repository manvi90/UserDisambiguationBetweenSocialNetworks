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

import com.shareablee.users.User;
import com.shareablee.users.UserCSVReader;

/**
 *
 */
public class Program {

	static int count = 0;
	static Map<String, UserProfile> list = new HashMap<>();
	static Map<String, UserProfileMaster> listMaster = new HashMap<>();
	
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
			temp.setLocation(user.getDemographic().getLocation().toString());
			
			tempMaster.setUser(user);

			list.put(temp.getEmailId(), temp);
			listMaster.put(user.getEmailId(), tempMaster);
		}
		
		getSocial("./data/new_social.csv");
		
		//System.out.println(JsonConverter.getJsonString(listMaster));
		
	}

	/**
	 * 
	 * @param filePath
	 */
	public static void getSocial(String filePath) {
		SocialCSVReader reader = new SocialCSVReader();
		
		if(filePath.isEmpty()) throw new IllegalArgumentException("No file name specified");
		BufferedReader bufferedReader = null; 
		count = 0;
		try {
			bufferedReader = new BufferedReader(new FileReader(filePath));
			String inputLine = "";
			while(true) {
				inputLine = bufferedReader.readLine();
				if (inputLine == null) break;
				if (inputLine.isEmpty())  continue;
				count++;
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
					
					UserProfile temp = list.get(social.getEmailId());
					if(temp != null) {
						if(temp.getMapSocial().get(social.getTypeId()) == null) {
							temp.getMapSocial().put(social.getTypeId(), new ArrayList<>());
						}
						List<SocialProfile> socialList = temp.getMapSocial().get(social.getTypeId());
						SocialProfile socialProfile = new SocialProfile();
						socialProfile.setSocialMediaId(social.getSocialMediaId());
						socialProfile.setTypeId(social.getTypeId());
						socialProfile.setTypeName(social.getTypeName());
						socialList.add(socialProfile);
					}
				}
				if(count > 10000) break;
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
}
