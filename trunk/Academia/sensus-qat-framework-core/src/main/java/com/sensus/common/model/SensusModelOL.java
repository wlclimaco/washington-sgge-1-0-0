package com.sensus.common.model;


/**
 * This class can also be used as the base class for all Model Objects but this class extends the {@link SensusModel} class
 * and also includes a version property which is used for optimistic locking.
 */
public class SensusModelOL extends SensusModel
{
	// [start] private fields

	/** The version. */
	private Integer version = 0;

	// [end] private fields

	// [start] public properties

	/**
	 * Gets the version number for this model object.
	 * 
	 * @return The version number
	 */
	public Integer getVersion()
	{
		return version;
	}

	/**
	 * Sets the version number for this model object.<br/>
	 * Typically this method is <B>NOT<B/> used by client applications. It is here so that the ORM persistence layer can
	 * set the value when retrieving data from the DBMS. If you "manually" set this you may cause database integrity
	 * problems.
	 * 
	 * @param version The new version
	 */
	public void setVersion(Integer version)
	{
		this.version = version;
	}

	// [end] public properties

}