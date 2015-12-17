/**
 * 
 */
package com.shareablee.utils;

/**
 * Class used to define the range
 *
 */
public class Range<T> {

	/**
	 * 
	 * @param start
	 * @param end
	 */
	public Range(T start, T end) {
		this.start = start;
		this.end = end;
	}

	/**
	 * 
	 * @return Start value
	 */
	public T getStart() {
		return this.start;
	}
	
	/**
	 * 
	 * @param start
	 */
	public void setStart(T start) {
		this.start = start;
	}

	/**
	 * 
	 * @return End value
	 */
	public T getEnd() {
		return this.end;
	}
	
	/**
	 * 
	 * @param end
	 */
	public void setEnd(T end) {
		this.end = end;
	}

	private T start;
	private T end;
}
