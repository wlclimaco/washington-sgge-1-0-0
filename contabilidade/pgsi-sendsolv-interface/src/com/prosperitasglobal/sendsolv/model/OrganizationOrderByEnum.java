package com.prosperitasglobal.sendsolv.model;

import com.qat.framework.model.IStringEnum;

/**
 * @author aporto
 * @version 1.0
 * @created 20-Aug-2014 10:48:40 AM
 */
public enum OrganizationOrderByEnum implements IStringEnum
{

	/** The name column. */
	NAME_COLUMN("name");

	/** The column name. */
	private String columnName;

	/**
	 * The Constructor.
	 *
	 * @param columnNameParam the column name param
	 */
	private OrganizationOrderByEnum(String columnNameParam)
	{
		columnName = columnNameParam;
	}

	/**
	 * Gets the column name.
	 *
	 * @return the column name
	 */
	public String getColumnName()
	{
		return columnName;
	}

	/**
	 * Sets the column name.
	 *
	 * @param columnName the column name
	 */
	public void setColumnName(String columnName)
	{
		this.columnName = columnName;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.IStringEnum#getValue()
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
	 * @return the organization order by enum
	 */
	public OrganizationOrderByEnum enumForValue(String columnNameValue)
	{
		for (OrganizationOrderByEnum organization : values())
		{
			if (organization.getValue().equals(columnNameValue))
			{
				return organization;
			}
		}
		return null;
	}

}
