/**
 * 
 */
package com.shareablee.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.shareablee.common.Program;

/**
 * This abstract class is used for CSV file reading
 *
 */
public abstract class CSVReader<T> {

	/**
	 * Method to parse the read line and convert it into an object
	 * 
	 * @param inputLine
	 * @return
	 */
	public abstract T parseLine(String inputLine);

	/**
	 * Method to read the csv file line by line
	 * 
	 * @param filePath
	 * @param map
	 * @return
	 */
	public void getData(String filePath, Map<String, Object> map) {
		return;
	}

	/**
	 * Method to read the csv file line by line
	 * 
	 * @param filePath
	 * @return
	 */
	public List<T> getData(String filePath) {

		if (filePath.isEmpty())
			throw new IllegalArgumentException("No file name specified");
		List<T> retVal = new ArrayList<T>();
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
				T t = (T) parseLine(inputLine);
				if (t != null) {
					retVal.add(t);
				}

				count++;
				if (count > Program.getCount())
					break;
			}
		} catch (FileNotFoundException ex) {
			System.err.println(ex.getMessage());
		} catch (IOException ex) {
			System.err.println("Unable to read the file : " + ex.toString());
		} catch (Exception ex) {

		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					System.err.println(e.getStackTrace().toString());
				}
			}
		}

		return retVal;
	}

}
