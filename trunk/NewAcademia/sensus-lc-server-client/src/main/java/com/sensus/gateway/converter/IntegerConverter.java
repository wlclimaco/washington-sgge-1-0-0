package com.sensus.gateway.converter;

import java.math.BigInteger;

import javax.xml.bind.DatatypeConverter;

/**
 * The Class IntegerConverter.
 */
public final class IntegerConverter
{
	/**
	 * Instantiates a new integer converter.
	 */
	private IntegerConverter()
	{

	}

	/**
	 * To integer.
	 * 
	 * @param value the value
	 * @return the integer
	 */
	public static Integer toInteger(String value)
	{
		try
		{
			return Integer.valueOf(DatatypeConverter.parseInteger(value).intValue());
		}
		catch (NumberFormatException e)
		{
			return null;
		}
	}

	/**
	 * From integer.
	 * 
	 * @param value the value
	 * @return the string
	 */
	public static String fromInteger(Integer value)
	{
		if (value == null)
		{
			return null;
		}

		return DatatypeConverter.printInteger(BigInteger.valueOf(value));
	}

}
