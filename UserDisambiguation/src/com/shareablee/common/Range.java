/**
 * 
 */
package com.shareablee.common;

/**
 * 
 *
 */
public class Range<T> {
	
	public Range(T start, T end ) {
		this.start = start;
		this.end = end;
	}
	
	public T getStart() {
		return this.start;
	}
	
	public T getEnd() {
		return this.end;
	}
	
	private T start;
	private T end;
}
