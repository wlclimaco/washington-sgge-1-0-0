package com.sensus.dm.common.base.model;

import com.sensus.common.model.IStringEnum;

/**
 * The Enum ObjectToBeValidatedKeyEnum.
 */
public enum ObjectToBeValidatedKeyEnum implements IStringEnum
{
	/** The end date. */
	END_DATE("endDate"),

	/** The file name. */
	FILE_NAME("fileName"),

	/** The initial date. */
	INITIAL_DATE("initialDate"),

	/** The page size. */
	PAGE_SIZE("pageSize"),

	/** The process id. */
	PROCESS_ID("processId"),

	/** The property provider type. */
	PROPERTY_PROVIDER_TYPE("propertyProviderType");

	/** The object to be validated key. */
	private String objectToBeValidatedKey;

	/**
	 * Instantiates a new object to be validated key enum.
	 * 
	 * @param objectToBeValidatedKey the object to be validated key
	 */
	private ObjectToBeValidatedKeyEnum(String objectToBeValidatedKeyParam)
	{
		objectToBeValidatedKey = objectToBeValidatedKeyParam;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.IStringEnum#getValue()
	 */
	@Override
	public String getValue()
	{
		return objectToBeValidatedKey;
	}
}
