/**
 * 
 */
package com.shareablee.common;

//import com.shareablee.social.SocialCSVReader;
import java.util.List;

import com.shareablee.users.User;
import com.shareablee.users.UserCSVReader;

/**
 *
 */
public class Program {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CSVReader<User> scsvr = new UserCSVReader();
		List<User> users = scsvr.getData("./data/new_primary.csv");
		int count = 0;
//		Map<String, >
		for(User user : users) {
			System.out.println(count++ + " :" + user.getEmailId());
		}
	}

}
