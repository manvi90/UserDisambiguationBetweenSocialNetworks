/**
 * 
 */
package com.shareablee.social;

import com.shareablee.common.CSVReader;

/**
 * 
 *
 */
public class SocialCSVReader extends CSVReader<Social> {
	
	@Override
	public Social parseLine(String inputLine) {
		
		Social retVal = null;
		
		String[] inputLineArray = inputLine.split(",");
		retVal = new Social(inputLineArray[0]); // ID
		retVal.setBio(inputLineArray[1]); // BIO
		// 2 - followers
		// 3 - following
		retVal.setSocialMediaId(inputLineArray[4]); // FB ID
		retVal.setRss(inputLineArray[5]); // RSS
		retVal.setTypeId(inputLineArray[6]); // Type ID
		retVal.setTypeName(inputLineArray[7]); // Type Name
		retVal.setUrl(inputLineArray[8]); // URL
		
		if(inputLineArray.length >= 9) {
			retVal.setUserName(inputLineArray[9]); // Username
		}
		
		return retVal;
	}
	
		
}
