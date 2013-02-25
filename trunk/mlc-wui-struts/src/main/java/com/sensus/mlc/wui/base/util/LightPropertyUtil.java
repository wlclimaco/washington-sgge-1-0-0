package com.sensus.mlc.wui.base.util;

import java.util.List;

import com.sensus.common.validation.ValidationUtil;
import com.sensus.mlc.smartpoint.model.LightParameter;
import com.sensus.mlc.smartpoint.model.PropertyEnum;

/**
 * The Class LightPropertyUtil.
 * 
 * @author Alexandre Tiveron
 */
public final class LightPropertyUtil
{

	/**
	 * Instantiates a new light property util.
	 */
	private LightPropertyUtil()
	{

	}

	/**
	 * Gets the light property value.
	 * 
	 * @param parameter the properties
	 * @param property the property
	 * @return the light property value
	 */
	public static String getLightParameterValue(List<LightParameter> parameter, PropertyEnum property)
	{
		for (LightParameter p : parameter)
		{
			if (!ValidationUtil.isNull(p.getValue()) && p.getPropertyEnum().equals(property))
			{
				return p.getValue();
			}
		}

		return null;
	}
}
