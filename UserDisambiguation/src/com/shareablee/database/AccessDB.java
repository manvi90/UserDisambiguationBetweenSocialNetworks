/**
 * 
 */
package com.shareablee.database;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

import com.shareablee.users.User;

/**
 *
 */
public class AccessDB {

	public static void insertToDB(User user){
		ConnectToDB.DBConnect();
		try {
			String insert = "INSERT INTO user_profile (emailID,lastName,firstName,gender,location) VALUES (?, ?, ?, ?, ?)";
			
			PreparedStatement stmt = ConnectToDB.conn.prepareStatement(insert);

			stmt.setString(1, user.getEmailId());
			stmt.setString(2, user.getContactInfo_familyName() == null  || user.getContactInfo_familyName().isEmpty() ? "" : user.getContactInfo_familyName());
			stmt.setString(3, user.getContactInfo_givenName() == null || user.getContactInfo_givenName().isEmpty() ? "" : user.getContactInfo_givenName());
			stmt.setString(4, user.getDemographic().getDemographics_gender().toString());
			stmt.setString(5, user.getDemographic().getLocation().toString());
			stmt.execute();
			
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
			ex.getStackTrace();
		}
	}
	
}
