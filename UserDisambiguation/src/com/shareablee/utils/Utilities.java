/**
 * 
 */
package com.shareablee.utils;

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

	private static int editDistCostSlow(String str1, String str2, int m, int n) {
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
	public static double getSimilarity(String str1, String str2){
		int distBtwStrings = editDistCost(str1, str2, str1.length(), str2.length());
		double retVal = 1 - ((distBtwStrings) * 1.0)/
							  	Math.max(str1.length(), str2.length());
		
		return retVal;
	}
}
