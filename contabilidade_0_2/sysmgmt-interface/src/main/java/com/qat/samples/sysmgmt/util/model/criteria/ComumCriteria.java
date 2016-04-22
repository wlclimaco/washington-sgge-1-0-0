package com.qat.samples.sysmgmt.util.model.criteria;

import java.io.Serializable;

/**
 * The Class MemberCriteria.
 */
@SuppressWarnings("serial")
public class ComumCriteria implements Serializable
{

	/** The business id. */
	private Integer emprId;

	/** The recipient id. */
	private Integer Id;

	/**
	 * The Constructor.
	 */
	public ComumCriteria()
	{
		super();
	}

	/**
	 * Gets the empresa.
	 * 
	 * @return the empresa
	 */
	public Integer getEmprId()
	{
		return emprId;
	}

	/**
	 * Sets the empresa.
	 * 
	 * @param empresa the empresa to set
	 */
	public void setEmprId(Integer emprId)
	{
		this.emprId = emprId;
	}

	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public Integer getId()
	{
		return Id;
	}

	/**
	 * Sets the id.
	 * 
	 * @param id the id to set
	 */
	public void setId(Integer id)
	{
		Id = id;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "ComumCriteria [getEmprId()=" + getEmprId() + ", getId()="
				+ getId() + ", toString()="
				+ super.toString() + "]";
	}

}
