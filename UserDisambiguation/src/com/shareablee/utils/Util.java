/**
 * 
 */
package com.shareablee.utils;

import java.util.Arrays;

/**
 * This class provides helper methods
 * 
 * @author Madhuri
 */
public class Util {

	/**
	 * Method to find the minimum of the 3 integers
	 * 
	 * @param x
	 * @param y
	 * @param z
	 * @return Minimum of three numbers
	 */
	private static int min(int x, int y, int z) {
		return Math.min(Math.min(x, y), z);
	}

	/**
	 * Method to get the edit distance cost for two strings
	 * 
	 * @param str1
	 * @param str2
	 * @param m
	 *            - length of str1
	 * @param n
	 *            - length of str2
	 * @return
	 */
	@SuppressWarnings("unused")
	private static int editDistCost(String str1, String str2, int m, int n) {
		// Create a table to store results of subproblems
		int dp[][] = new int[m + 1][n + 1];

		// Fill d[][] in bottom up manner
		for (int i = 0; i <= m; i++) {
			for (int j = 0; j <= n; j++) {
				// If first string is empty, only option is to
				// insert all characters of second string
				if (i == 0)
					dp[i][j] = j; // Min. operations = j

				// If second string is empty, only option is to
				// remove all characters of second string
				else if (j == 0)
					dp[i][j] = i; // Min. operations = i

				// If last characters are same, ignore last char
				// and recur for remaining string
				else if (str1.charAt(i - 1) == str2.charAt(j - 1))
					dp[i][j] = dp[i - 1][j - 1];

				// If last character are different, consider all
				// possibilities and find minimum
				else
					dp[i][j] = 1 + min(dp[i][j - 1], // Insert
							dp[i - 1][j], // Remove
							dp[i - 1][j - 1]); // Replace
			}
		}

		return dp[m][n];
	}

	/**
	 * Method to calculate the JaroWinkler distance similarity between the two
	 * strings
	 * 
	 * @param str1
	 * @param str2
	 * @return Similarity score
	 */
	public static double getJaroWinklerDistanceSimilarity(String str1,
			String str2) {
		double retVal = 0.0;
		if (str1 == null || str2 == null)
			return 0;
		JaroWinklerDistance jaroDistCalc = new JaroWinklerDistance();
		retVal = jaroDistCalc.getDistance(str1, str2);
		return retVal;
	}

	/**
	 * Method to calculate the dice similarity between the two strings
	 * 
	 * @param str1
	 * @param str2
	 * @return Similarity score
	 */
	public static double getDiceSimilarity(String str1, String str2) {
		// Verifying the input:
		if (str1 == null || str2 == null)
			return 0;
		// Quick check to catch identical objects:
		if (str1 == str2)
			return 1;
		// avoid exception for single character searches
		if (str1.length() < 2 || str2.length() < 2)
			return 0;

		// Create the bigrams for string s:
		final int n = str1.length() - 1;
		final int[] sPairs = new int[n];
		for (int i = 0; i <= n; i++)
			if (i == 0)
				sPairs[i] = str1.charAt(i) << 16;
			else if (i == n)
				sPairs[i - 1] |= str1.charAt(i);
			else
				sPairs[i] = (sPairs[i - 1] |= str1.charAt(i)) << 16;

		// Create the bigrams for string t:
		final int m = str2.length() - 1;
		final int[] tPairs = new int[m];
		for (int i = 0; i <= m; i++)
			if (i == 0)
				tPairs[i] = str2.charAt(i) << 16;
			else if (i == m)
				tPairs[i - 1] |= str2.charAt(i);
			else
				tPairs[i] = (tPairs[i - 1] |= str2.charAt(i)) << 16;

		// Sort the bigram lists:
		Arrays.sort(sPairs);
		Arrays.sort(tPairs);

		// Count the matches:
		int matches = 0, i = 0, j = 0;
		while (i < n && j < m) {
			if (sPairs[i] == tPairs[j]) {
				matches += 2;
				i++;
				j++;
			} else if (sPairs[i] < tPairs[j])
				i++;
			else
				j++;
		}
		return (double) matches / (n + m);
	}

}
