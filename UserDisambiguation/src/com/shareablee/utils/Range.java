/**
 * 
 */
package com.shareablee.utils;

/**
 * Class used to define the range
 *
 */
public class Range<T> {

	public Range(T start, T end) {
		this.start = start;
		this.end = end;
	}

	public T getStart() {
		return this.start;
	}
	
	public void setStart(T start) {
		this.start = start;
	}

	public T getEnd() {
		return this.end;
	}
	
	public void setEnd(T end) {
		this.end = end;
	}

	private T start;
	private T end;
}
