package com.sensus.dm.common.device.model;

import com.sensus.common.model.SensusModel;
import com.sensus.dm.common.base.model.SearchTypeEnum;

/**
 * The Class SearchParameter.
 */
@SuppressWarnings("serial")
public class SearchParameter extends SensusModel
{
	/** The name. */
	private String name;

	/** The valid parameter. */
	private Boolean validParameter;

	/** The filter enum. */
	private DeviceFilterEnum filterEnum;

	/** The value. */
	private String value;

	/** The search type. */
	private SearchTypeEnum searchType;

	/**
	 * Instantiates a new search parameter.
	 */
	public SearchParameter()
	{
	}

	/**
	 * Instantiates a new search parameter.
	 * 
	 * @param mfe the mfe
	 * @param value the value
	 */
	public SearchParameter(DeviceFilterEnum mfeParam, String valueParam)
	{
		setFilterEnum(mfeParam);
		setValue(valueParam);
	}

	/**
	 * Instantiates a new search parameter.
	 * 
	 * @param name the name
	 * @param mfe the mfe
	 * @param value the value
	 */
	public SearchParameter(String nameParam, DeviceFilterEnum mfeParam, String valueParam)
	{
		setName(nameParam);
		setFilterEnum(mfeParam);
		setValue(valueParam);
	}

	/**
	 * Gets the name.
	 * 
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Sets the name.
	 * 
	 * @param nameString the new name
	 */
	public void setName(String nameString)
	{
		name = nameString;
	}

	/**
	 * Checks if is valid parameter.
	 * 
	 * @return the boolean
	 */
	public Boolean isValidParameter()
	{
		return validParameter;
	}

	/**
	 * Sets the valid parameter.
	 * 
	 * @param validParameter the new valid parameter
	 */
	public void setValidParameter(Boolean validParameter)
	{
		this.validParameter = validParameter;
	}

	/**
	 * Gets the filter enum.
	 * 
	 * @return the filter enum
	 */
	public DeviceFilterEnum getFilterEnum()
	{
		return filterEnum;
	}

	/**
	 * Sets the filter enum.
	 * 
	 * @param filterEnum the new filter enum
	 */
	public void setFilterEnum(DeviceFilterEnum filterEnum)
	{
		this.filterEnum = filterEnum;
	}

	/**
	 * Gets the value.
	 * 
	 * @return the value
	 */
	public String getValue()
	{
		return value;
	}

	/**
	 * Sets the value.
	 * 
	 * @param value the new value
	 */
	public void setValue(String value)
	{
		this.value = value;
	}

	/**
	 * Gets the search type.
	 * 
	 * @return the search type
	 */
	public SearchTypeEnum getSearchType()
	{
		return searchType;
	}

	/**
	 * Sets the search type.
	 * 
	 * @param searchType the new search type
	 */
	public void setSearchType(SearchTypeEnum searchType)
	{
		this.searchType = searchType;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.SensusModel#toString()
	 */
	@Override
	public String toString()
	{
		return "SearchParameter [getName()=" + getName() + ", isValidParameter()=" + isValidParameter()
				+ ", getFilterEnum()=" + getFilterEnum() + ", getValue()=" + getValue() + ", getSearchType()="
				+ getSearchType() + ", getModelAction()=" + getModelAction() + ", getCreateUser()=" + getCreateUser()
				+ ", getCreateDate()=" + getCreateDate() + ", getModifyUser()=" + getModifyUser()
				+ ", getModifyDate()=" + getModifyDate() + "]";
	}
}
