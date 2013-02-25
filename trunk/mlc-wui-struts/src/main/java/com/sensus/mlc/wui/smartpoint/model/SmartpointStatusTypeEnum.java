package com.sensus.mlc.wui.smartpoint.model;

/**
 * Enumeration representing SmartPoint Status values.
 * 
 * @author Anke Doerfel-Parker
 * 
 */
public enum SmartpointStatusTypeEnum
{

	/** The ERROR. */
	ERROR(1),
	/** The WARNING. */
	WARNING(2),
	/** The ACTIVE. */
	ACTIVE(3);

	/**
	 * The SmartPoint Status Id.
	 */
	private Integer id;

	/**
	 * Create an enum member with id.
	 * 
	 * @param idIn the status Id
	 */
	SmartpointStatusTypeEnum(Integer idIn)
	{

		setId(idIn);
	}

	/**
	 * Sets the id.
	 * 
	 * @param id the id to set
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public Integer getId()
	{
		return id;
	}
}
