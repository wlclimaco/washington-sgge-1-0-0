package com.sensus.gateway.converter;

import javax.xml.bind.DatatypeConverter;

/**
 * The Class DoubleConverter.
 */
public final class DoubleConverter
{
	/**
	 * Instantiates a new double converter.
	 */
	private DoubleConverter()
	{
	}

	/**
	 * To double.
	 * 
	 * @param value the value
	 * @return the double
	 */
	public static Double toDouble(String value)
	{
		try
		{
			return new Double(DatatypeConverter.parseDouble(value));
		}
		catch (NumberFormatException e)
		{
			return null;
		}
	}

	/**
	 * From double.
	 * 
	 * @param value the value
	 * @return the string
	 */
	public static String fromDouble(Double value)
	{
		if (value == null)
		{
			return "";
		}
		return DatatypeConverter.printDouble(value.doubleValue());
	}
}
