package com.sensus.common.model;


/**
 * The Class Tenant.
 */
@SuppressWarnings("serial")
public class Tenant extends SensusModel
{
	/** The id. */
	private Integer id;

	/**
	 * Instantiates a new tenant.
	 */
	public Tenant()
	{
	}

	/**
	 * Instantiates a new abstract tenant.
	 * 
	 * @param id the id
	 */
	public Tenant(Integer id)
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

	/**
	 * Sets the id.
	 * 
	 * @param id the new id
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.SensusModel#toString()
	 */
	@Override
	public String toString()
	{
		return "AbstractTenant [getId()=" + getId() + ", getModelAction()=" + getModelAction() + ", getCreateUser()="
				+ getCreateUser() + ", getCreateDate()=" + getCreateDate() + ", getModifyUser()=" + getModifyUser()
				+ ", getModifyDate()=" + getModifyDate() + "]";
	}
}
