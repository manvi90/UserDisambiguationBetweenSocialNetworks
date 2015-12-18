package com.shareablee.testing;

import java.util.List;

import com.shareablee.utils.CSVReader;

/**
 * Class for testing
 * 
 * @author Madhuri
 *
 */
public class Test {

	/**
	 * 
	 * @param inputPath
	 * @return Collection of Test profiles
	 */
	public List<TestProfile> getTestData(String inputPath) {
		CSVReader<TestProfile> tcsv = new TestCSVReader();
		List<TestProfile> retVal = tcsv.getData(inputPath);
		return retVal;
	}
}
