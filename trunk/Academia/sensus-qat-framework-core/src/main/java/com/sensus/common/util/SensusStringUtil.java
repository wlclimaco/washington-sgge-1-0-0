package com.sensus.common.util;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * General purpose Utility class.
 */
public class SensusStringUtil
{

	/**
	 * Creates a default implementation of a toString method for the given object. This method uses reflection and is
	 * slow, but can be used for debugging scenarios. Instead a formal toString method should be created on the object
	 * to insure proper execution.
	 * 
	 * @param object The object upon which to reflect and generate a toString result.
	 * 
	 * @return A reflect toString method result.
	 */
	public static final String createToString(Object object)
	{
		String ret = null;
		try
		{
			ret = object.toString();
		}
		catch (Exception x)
		{
			try
			{
				ret = ToStringBuilder.reflectionToString(object);
			}
			catch (Exception x2)
			{
				ret = null;
			}
		}
		return ret;
	}
}
