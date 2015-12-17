/**
 * 
 */
package com.shareablee.common;

/**
 * defined constants
 * 
 * @author mm
 *
 */
public class Constants {

	public static char[] PATTERN = { '\0', '.', '_', '-' };
	public static double EMAIL_THRESHOLD = 0.7;
	public static double FIRST_NAME_THRESHOLD = 0.95;
	public static double LAST_NAME_THRESHOLD = 0.98;
	public static double USER_NAME_THRESHOLD = 0.7;
	public static double CLUSTER_THRESHOLD = 0.55;
	public static double USER_SIMILARITY_THRESHOLD = 0.70;

	public static double EMAIL_WEIGHT = 0.25;
	public static double FIRST_NAME_WEIGHT = 0.20;
	public static double LAST_NAME_WEIGHT = 0.25;
	public static double USERID_WEIGHT = 0.15;
	public static double GENDER_WEIGHT = 0.05;
	public static double LOCATION_WEIGHT = 0.10;
}
