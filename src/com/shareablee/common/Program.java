/**
 * 
 */
package com.shareablee.common;

import com.shareablee.social.SocialCSVReader;

/**
 *
 */
public class Program {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SocialCSVReader scsvr = new SocialCSVReader();
		scsvr.getData("./data/user_sample.csv");
	}

}
