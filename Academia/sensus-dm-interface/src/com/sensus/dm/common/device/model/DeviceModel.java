package com.sensus.dm.common.device.model;

import com.sensus.common.model.SensusModel;

/**
 * The Class DeviceModel.
 * 
 * @author QAT Global
 */
@SuppressWarnings("serial")
public class DeviceModel extends SensusModel
{

	/** The id. */
	private Integer id;

	/** The manufacture. */
	private String manufacture;

	/** The description. */
	private String description;

	// -------------------------------------------------------------------------
	// Constructors:

	/**
	 * Instantiates a new device model.
	 */
	public DeviceModel()
	{
	}

	/**
	 * Instantiates a new device model.
	 * 
	 * @param manufactureParam the manufacture param
	 * @param descriptionParam the description param
	 */
	public DeviceModel(String manufactureParam, String descriptionParam)
	{
		setDescription(descriptionParam);
		setManufacture(manufactureParam);
	}

	/**
	 * Instantiates a new device model.
	 * 
	 * @param idParam the id param
	 * @param manufactureParam the manufacture param
	 * @param descriptionParam the description param
	 */
	public DeviceModel(Integer idParam, String manufactureParam, String descriptionParam)
	{
		setId(idParam);
		setDescription(descriptionParam);
		setManufacture(manufactureParam);
	}

	/**
	 * Instantiates a new device model.
	 * 
	 * @param descriptionParam the description
	 */
	public DeviceModel(String descriptionParam)
	{
		setDescription(descriptionParam);
	}

	// -------------------------------------------------------------------------
	// Getters and setters:

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
	 * Gets the manufacture.
	 * 
	 * @return the manufacture
	 */
	public String getManufacture()
	{
		return manufacture;
	}

	/**
	 * Sets the manufacture.
	 * 
	 * @param manufacture the new manufacture
	 */
	public void setManufacture(String manufacture)
	{
		this.manufacture = manufacture;
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

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.SensusModel#toString()
	 */
	@Override
	public String toString()
	{
		return "DeviceModel [getId()=" + getId() + ", getManufacture()=" + getManufacture() + ", getDescription()="
				+ getDescription() + ", getModelAction()=" + getModelAction() + ", getCreateUser()=" + getCreateUser()
				+ ", getCreateDate()=" + getCreateDate() + ", getModifyUser()=" + getModifyUser()
				+ ", getModifyDate()=" + getModifyDate() + "]";
	}

}
