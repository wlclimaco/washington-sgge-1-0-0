package com.sensus.mlc.process.model;

import com.sensus.common.model.SensusModel;

/**
 * The Class ProcessStatusReason.
 */
@SuppressWarnings("serial")
public class ProcessStatusReason extends SensusModel
{

	/** The id. */
	private Integer id;

	/** The label key. */
	private String labelKey;

	/**
	 * Instantiates a new process status reason.
	 * 
	 * @param newId the new id
	 * @param newLabelKey the new label key
	 */
	public ProcessStatusReason(Integer newId, String newLabelKey)
	{
		setId(newId);
		setLabelKey(newLabelKey);
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

	/**
	 * Gets the label key.
	 * 
	 * @return the label key
	 */
	public String getLabelKey()
	{
		return labelKey;
	}

	/**
	 * Sets the label key.
	 * 
	 * @param labelKey the new label key
	 */
	public void setLabelKey(String labelKey)
	{
		this.labelKey = labelKey;
	}

	@Override
	public String toString()
	{
		return "ProcessStatusReason [getId()=" + getId() + ", getLabelKey()=" + getLabelKey() + "]";
	}

}
