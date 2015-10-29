package com.qat.samples.sysmgmt.model.response;

import javax.xml.bind.annotation.XmlElement;

import com.qat.framework.model.response.InternalResponse;

/**
 * The Model Object ProcedureResponse.
 */
public class InternalResponseLocal extends InternalResponse
{

	/** The bundles. */
	@XmlElement(nillable = true)
	private Integer id;

	/**
	 * Gets the bundles.
	 * 
	 * @return the bundles
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * Sets the bundles.
	 * 
	 * @param id the new bundles
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InternalResponse#toString()
	 */
	@Override
	public String toString()
	{
		return "InternalResponseLocal [getId()=" + getId() + ", toString()=" + super.toString() + "]";
	}

}
