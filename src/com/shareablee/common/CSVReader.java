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
public class CSVReader<T> {
	
	public T parseLine(String inputLine) {
		return null;
	}
	
	public List<T> getData(String filePath) {
		
		if(filePath.isEmpty()) throw new IllegalArgumentException("No file name specified");
		List<T> retVal = new ArrayList<T>();
		BufferedReader bufferedReader = null; 
		try {
			bufferedReader = new BufferedReader(new FileReader(filePath));
			String inputLine = "";
			while(true) {
				inputLine = bufferedReader.readLine();
				if (inputLine == null) break;
				if (inputLine.isEmpty())  continue;
				T t = (T)parseLine(inputLine);
				if(t != null) {
					retVal.add(t);
				}
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
		
		return retVal;
	}
	
}
