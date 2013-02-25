package com.sensus.mlc.ecomode.model;

import com.sensus.common.model.IStringEnum;

/**
 * The Enum EcoModeOrderByEnum.
 */
public enum EcoModeOrderByEnum implements IStringEnum
{
	/** The date. */
	DATE("consumption_day"),

	/** The measured consumption. */
	MEASURED_CONSUMPTION("consumption"),

	ECOMODE_BASELINE("ecomode_baseline"),

	/** The eco mode. */
	ECOMODE("consumption_ecomode");

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
