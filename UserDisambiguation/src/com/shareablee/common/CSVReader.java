/**
 * 
 */
package com.shareablee.common;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 *
 */
public abstract class CSVReader<T> {
	
	public abstract T parseLine(String inputLine);
	
	/**
	 * Method to read the csv file line by line
	 * @param filePath
	 * @return
	 */
	public List<T> getData(String filePath) {
		
		if(filePath.isEmpty()) throw new IllegalArgumentException("No file name specified");
		List<T> retVal = new ArrayList<T>();
		BufferedReader bufferedReader = null; 
		int count = 0;
		try {
			bufferedReader = new BufferedReader(new FileReader(filePath));
			String inputLine = "";
			while(true) {
				inputLine = bufferedReader.readLine();
				if (inputLine == null) break;
				if (inputLine.isEmpty())  continue;
				System.out.println(count);
				count++;
				if(count < 613092) continue;
				T t = (T)parseLine(inputLine);
				if(t != null) {
					//retVal.add(t);
				}
				if(count > Program.getCount()) break;
			}
		}catch (FileNotFoundException ex) {
			System.err.println(ex.getMessage());
		} catch (IOException ex) {
			System.err.println("Unable to read the file : " + ex.toString());
		}catch (Exception ex) {
			
		} finally {
			if(bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					System.err.println(e.getStackTrace().toString());
				}
			}
		}
		
		return null;
	}
	
}
