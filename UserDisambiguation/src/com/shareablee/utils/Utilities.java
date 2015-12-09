/**
 * 
 */
package com.shareablee.utils;

import java.util.Arrays;

/**
 * @author Madhuri
 *
 */
public class Utilities {

	/**
	 *  Utility function to find minimum of three numbers
	 * @param x
	 * @param y
	 * @param z
	 * @return
	 */
	private static int min(int x, int y, int z) {
		return Math.min(Math.min(x, y), z);
	}


	/**
	 * @param str1
	 * @param str2
	 * @param m - length of str1 
	 * @param n - length of str2
	 * @return
	 */

	/*	private static int editDistCostSlow(String str1, String str2, int m, int n) {
		// If first string is empty, the only option is to
		// insert all characters of second string into first
		if (m == 0)
			return n;

		// If second string is empty, the only option is to
		// remove all characters of first string
		if (n == 0)
			return m;

		// If last characters of two strings are same, nothing
		// much to do. Ignore last characters and get count for
		// remaining strings.
		if (str1.charAt(m - 1) == str2.charAt(n - 1))
			return editDistCostSlow(str1, str2, m - 1, n - 1);

		// If last characters are not same, consider all three
		// operations on last character of first string, recursively
		// compute minimum cost for all three operations and take
		// minimum of three values.
		return 1 + min(editDistCostSlow(str1, str2, m, n - 1), // Insert
				editDistCostSlow(str1, str2, m - 1, n), // Remove
				editDistCostSlow(str1, str2, m - 1, n - 1) // Replace
		);

	}
	 */

	/**
	 * @param str1
	 * @param str2
	 * @param m - length of str1 
	 * @param n - length of str2
	 * @return
	 */
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
	 * Function to calculate similarity score
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static double getSimilarity(String str1, String str2, boolean flag) {
		double retVal = 0.0;
		if(str1 == null || str2 == null) return 0;
		if (flag){
			//int distBtwStrings = editDistCost(str1.toLowerCase(), str2.toLowerCase(), str1.length(), str2.length());
			//retVal = 1 - ((distBtwStrings) * 1.0)/
					//Math.max(str1.length(), str2.length());
			retVal = diceCoefficientOptimized(str1, str2);
		}else {
			JaroWinklerDistance jaroDistCalc = new JaroWinklerDistance(); 
			retVal = jaroDistCalc.getDistance(str1, str2);
		}

		return retVal;
	}
	
	
	public static double diceCoefficientOptimized(String s, String t)
	{
		// Verifying the input:
		if (s == null || t == null)
			return 0;
		// Quick check to catch identical objects:
		if (s == t)
			return 1;
	        // avoid exception for single character searches
	        if (s.length() < 2 || t.length() < 2)
	            return 0;

		// Create the bigrams for string s:
		final int n = s.length()-1;
		final int[] sPairs = new int[n];
		for (int i = 0; i <= n; i++)
			if (i == 0)
				sPairs[i] = s.charAt(i) << 16;
			else if (i == n)
				sPairs[i-1] |= s.charAt(i);
			else
				sPairs[i] = (sPairs[i-1] |= s.charAt(i)) << 16;

		// Create the bigrams for string t:
		final int m = t.length()-1;
		final int[] tPairs = new int[m];
		for (int i = 0; i <= m; i++)
			if (i == 0)
				tPairs[i] = t.charAt(i) << 16;
			else if (i == m)
				tPairs[i-1] |= t.charAt(i);
			else
				tPairs[i] = (tPairs[i-1] |= t.charAt(i)) << 16;

		// Sort the bigram lists:
		Arrays.sort(sPairs);
		Arrays.sort(tPairs);

		// Count the matches:
		int matches = 0, i = 0, j = 0;
		while (i < n && j < m)
		{
			if (sPairs[i] == tPairs[j])
			{
				matches += 2;
				i++;
				j++;
			}
			else if (sPairs[i] < tPairs[j])
				i++;
			else
				j++;
		}
		return (double)matches/(n+m);
	}
	
}
