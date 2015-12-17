/**
 * 
 */
package com.shareablee.socialprofile;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.shareablee.common.Profile;
import com.shareablee.common.ProfileMaster;
import com.shareablee.common.Program;
import com.shareablee.utils.CSVReader;

/**
 * This class is used to read the social data from a CSV file
 * 
 * @author ravidugar
 *
 */
public class SocialCSVReader extends CSVReader<SocialMaster> {

	/**
	 * Method to read the social csv file line by line
	 */
	@Override
	public void getData(String filePath, Map<String, Object> map) {
		if (filePath.isEmpty())
			throw new IllegalArgumentException("No file name specified");
		BufferedReader bufferedReader = null;
		int count = 0;
		try {
			bufferedReader = new BufferedReader(new FileReader(filePath));
			String inputLine = "";
			while (true) {
				inputLine = bufferedReader.readLine();
				if (inputLine == null)
					break;
				if (inputLine.isEmpty())
					continue;
				inputLine = inputLine.toLowerCase();
				inputLine = inputLine.replace("\\,", "");
				count++;
				SocialMaster socialMaster = parseLine(inputLine);
				if (socialMaster != null) {
					ProfileMaster profileMaster = Program.getListMaster().get(
							socialMaster.getEmailId());
					if (profileMaster != null) {
						if (profileMaster.getMapSocial().get(
								socialMaster.getTypeId()) == null) {
							profileMaster.getMapSocial()
									.put(socialMaster.getTypeId(),
											new ArrayList<>());
						}
						List<SocialMaster> socialList = profileMaster
								.getMapSocial().get(socialMaster.getTypeId());
						socialList.add(socialMaster);
					}

					Profile profile = (Profile) map.get(socialMaster
							.getEmailId());
					if (profile != null) {
						if (profile.getMapSocial()
								.get(socialMaster.getTypeId()) == null) {
							profile.getMapSocial()
									.put(socialMaster.getTypeId(),
											new ArrayList<>());
						}
						List<Social> socialList = profile.getMapSocial().get(
								socialMaster.getTypeId());
						Social social = new Social();
						social.setSocialMediaId(socialMaster.getSocialMediaId());
						social.setTypeId(socialMaster.getTypeId());
						social.setTypeName(socialMaster.getTypeName());
						social.setUserName(socialMaster.getUserName());
						socialList.add(social);
					}
				}
				if (count > Program.getCount())
					break;
			}
		} catch (FileNotFoundException ex) {
			System.err.println(ex.getMessage());
		} catch (IOException ex) {
			System.err.println("Unable to read the file : " + ex.toString());
		} catch (Exception ex) {
			System.err.println("General exception : " + ex.toString());
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					System.err.println(e.getStackTrace().toString());
				}
			}
		}
	}

	/**
	 * Method to parse the read line and convert it into an object
	 */
	@Override
	public SocialMaster parseLine(String inputLine) {

		SocialMaster retVal = null;

		String[] inputLineArray = inputLine.split(",");
		retVal = new SocialMaster(inputLineArray[0]); // ID
		retVal.setBio(inputLineArray[1]); // BIO

		if (inputLineArray[2] != null && !inputLineArray[2].isEmpty())
			retVal.setFollowers(Integer.parseInt(inputLineArray[2])); // followers
		if (inputLineArray[3] != null && !inputLineArray[3].isEmpty())
			retVal.setFollowers(Integer.parseInt(inputLineArray[3])); // following

		retVal.setSocialMediaId(inputLineArray[4]); // FB ID
		retVal.setRss(inputLineArray[5]); // RSS
		retVal.setTypeId(inputLineArray[6]); // Type ID
		retVal.setTypeName(inputLineArray[7]); // Type Name
		retVal.setUrl(inputLineArray[8]); // URL

		if (inputLineArray.length > 9) {
			retVal.setUserName(inputLineArray[9]); // Username
		}

		return retVal;
	}

}
