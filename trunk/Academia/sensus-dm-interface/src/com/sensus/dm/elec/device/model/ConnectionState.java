package com.sensus.dm.elec.device.model;

import com.sensus.common.model.SensusModel;

/**
 * * The Class Connection State.
 * 
 * @author - QAT Brazil.
 */
@SuppressWarnings("serial")
public class ConnectionState extends SensusModel 
{
	
	/** The id. */
	private Integer id;
	
	/** The short name. */
	private String shortName;
	
	/** The description. */
	private String description;
	
	public ConnectionState()
	{		
	}
	
	/**
	 * Instantiates a new connection state.
	 *
	 * @param paramId the param id
	 */
	public ConnectionState(Integer paramId)
	{		
		setId(paramId);
	}
	
	/**
	 * Instantiates a new connection state.
	 *
	 * @param paramId the param id
	 * @param paramShortName the param short name
	 * @param paramDescription the param description
	 */
	public ConnectionState(Integer paramId, String paramShortName, String paramDescription)
	{
		setId(paramId);
		setShortName(paramShortName);
		setDescription(paramDescription);
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
	 * Gets the short name.
	 *
	 * @return the short name
	 */
	public String getShortName() 
	{
		return shortName;
	}
	
	/**
	 * Sets the short name.
	 *
	 * @param shortName the new short name
	 */
	public void setShortName(String shortName) 
	{
		this.shortName = shortName;
	}
	
	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() 
	{
		return description;
	}
	
	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) 
	{
		this.description = description;
	}
	
	/* (non-Javadoc)
	 * @see com.sensus.common.model.SensusModel#toString()
	 */
	@Override
	public String toString() 
	{
		return "ConnectionState [getId()=" + getId() + ", getShortName()="
				+ getShortName() + ", getDescription()=" + getDescription() + "]";
	}	
}
