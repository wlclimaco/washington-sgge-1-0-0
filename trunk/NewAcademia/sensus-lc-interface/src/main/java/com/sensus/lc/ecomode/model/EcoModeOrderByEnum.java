package com.sensus.lc.ecomode.model;

import com.sensus.common.model.IStringEnum;

/**
 * The Enum EcoModeOrderByEnum.
 */
public enum EcoModeOrderByEnum implements IStringEnum
{
	/** The date. */
	DATE("CONSUMPTION_DAY"),

	/** The measured consumption. */
	MEASURED_CONSUMPTION("CONSUMPTION"),

	ECOMODE_BASELINE("ECOMODE_BASELINE"),

	/** The eco mode. */
	ECOMODE("ECOMODE");

	/** The colum name. */
	private String columnName;

	/**
	 * Instantiates a new eco mode order by enum.
	 * 
	 * @param columnNameValue the column name value
	 */
	private EcoModeOrderByEnum(String columnNameValue)
	{
		columnName = columnNameValue;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.IStringEnum#getValue()
	 */
	@Override
	public String getValue()
	{
		return columnName;
	}

	/**
	 * Enum for value.
	 * 
	 * @param columnNameValue the column name value
	 * @return the eco mode order by enum
	 */
	public EcoModeOrderByEnum enumForValue(String columnNameValue)
	{
		for (EcoModeOrderByEnum ecomode : values())
		{
			if (ecomode.getValue().equals(columnNameValue))
			{
				return ecomode;
			}
		}
		return null;
	}
}
