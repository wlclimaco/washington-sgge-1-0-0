package com.sensus.common.query;

/**
 * Used to represents a range of values. Both String and numeric values.
 * 
 */
public class Range
{
	// Ending value of the range.
	private String end;

	// Starting value of the range.
	private String start;

	/**
	 * Initializes a Range with a given starting String value and ending String value that bound the range.
	 * 
	 * @param start the starting String value of the range.
	 * 
	 * @param end the ending String value of the range.
	 */
	public Range(String start, String end)
	{
		this.start = start;
		this.end = end;
	}

	/**
	 * Returns the ending String value of the range.
	 * 
	 * @return the ending String value of the range.
	 */
	public String getEnd()
	{
		return this.end;
	}

	/**
	 * Returns the ending String value of the range.
	 * 
	 * @return the ending String value of the range.
	 */
	public String getStart()
	{
		return this.start;
	}
}
