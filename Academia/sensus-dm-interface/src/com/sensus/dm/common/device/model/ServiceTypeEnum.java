package com.sensus.dm.common.device.model;

import com.sensus.common.model.IIntegerEnum;

/**
 * The Enum ServiceTypeEnum.
 */
public enum ServiceTypeEnum implements IIntegerEnum
{
	/** The electric. */
	ELECTRIC(1, 13, "E"),

	/** The water. */
	WATER(2, 14, "W"),

	/** The gas. */
	GAS(3, 15, "G"),

	/** The arqiva device. */
	ARQIVA(4, 16, "A"),

	/** The light. */
	LIGHT(5, 17, "L");

	/** The service. */
	private Integer serviceType;

	/** The service type description. */
	private String serviceTypeDescription;

	/** The service group type. */
	private Integer serviceGroupType;

	/**
	 * Instantiates a new service type enum.
	 * 
	 * @param serviceTypeParam the service type param
	 * @param serviceGroupTypeParam the service group type param
	 * @param serviceTypeDescriptionParam the service type description param
	 */
	private ServiceTypeEnum(Integer serviceTypeParam, Integer serviceGroupTypeParam, String serviceTypeDescriptionParam)
	{
		serviceType = serviceTypeParam;
		serviceGroupType = serviceGroupTypeParam;
		serviceTypeDescription = serviceTypeDescriptionParam;
	}

	/**
	 * Gets the service type.
	 * 
	 * @return the service type
	 */
	public Integer getServiceType()
	{
		return serviceType;
	}

	/**
	 * Gets the service group type.
	 * 
	 * @return the service group type
	 */
	public Integer getServiceGroupType()
	{
		return serviceGroupType;
	}

	/**
	 * Gets the service type description.
	 * 
	 * @return the service type description
	 */
	public String getServiceTypeDescription()
	{
		return serviceTypeDescription;
	}

	/**
	 * Enum for description.
	 * 
	 * @param value the value
	 * @return the service type enum
	 */
	public static ServiceTypeEnum enumForDescription(String value)
	{
		for (ServiceTypeEnum e : values())
		{
			if (e.getServiceTypeDescription().equals(value))
			{
				return e;
			}
		}

		return null;
	}

	/**
	 * Enum for value.
	 * 
	 * @param value the value
	 * @return the service enum
	 */
	public static ServiceTypeEnum enumForValue(Integer value)
	{
		for (ServiceTypeEnum e : values())
		{
			if (e.getValue().equals(value))
			{
				return e;
			}
		}

		return null;
	}

	/**
	 * Gets the valid values.
	 * 
	 * @return the valid values
	 */
	public static String getValidValues()
	{
		ServiceTypeEnum[] enums = ServiceTypeEnum.class.getEnumConstants();

		String comma = "";
		StringBuilder enumValue = new StringBuilder();
		for (ServiceTypeEnum i : enums)
		{
			enumValue.append(comma + i.getValue());
			comma = ", ";
		}
		return enumValue.toString();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.IIntegerEnum#getValue()
	 */
	@Override
	public Integer getValue()
	{
		return serviceType;
	}

}
