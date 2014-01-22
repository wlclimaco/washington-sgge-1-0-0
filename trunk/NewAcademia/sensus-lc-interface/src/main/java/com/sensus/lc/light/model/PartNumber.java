package com.sensus.lc.light.model;

import java.io.Serializable;
import java.util.Map;

import com.sensus.common.model.SensusModel;

/**
 * The Class SensusPartNumber.
 */
@SuppressWarnings("serial")
public class PartNumber extends SensusModel implements Serializable
{
	/** The name. */
	private String name;

	/** The product model number. */
	private String productModelNumber;

	/** The product model mask. */
	private Map<Integer, String> productModelMask;

	/** The dim on delay. */
	private Integer dimOnDelay;

	/** The dim table. */
	private String dimTable;

	/** The blink slot time enum. */
	private Integer blinkSlotTimeEnum;

	/**
	 * Instantiates a new sensus part number.
	 */
	public PartNumber()
	{
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
	 * @param name the new name
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * Gets the product model number.
	 *
	 * @return the product model number
	 */
	public String getProductModelNumber()
	{
		return productModelNumber;
	}

	/**
	 * Sets the product model number.
	 *
	 * @param productModelNumber the new product model number
	 */
	public void setProductModelNumber(String productModelNumber)
	{
		this.productModelNumber = productModelNumber;
	}

	/**
	 * Gets the product model mask.
	 *
	 * @return the product model mask
	 */
	public Map<Integer, String> getProductModelMask()
	{
		return productModelMask;
	}

	/**
	 * Sets the product model mask.
	 *
	 * @param productModelMask the product model mask
	 */
	public void setProductModelMask(Map<Integer, String> productModelMask)
	{
		this.productModelMask = productModelMask;
	}

	/**
	 * Gets the dim on delay.
	 *
	 * @return the dim on delay
	 */
	public Integer getDimOnDelay()
	{
		return dimOnDelay;
	}

	/**
	 * Sets the dim on delay.
	 *
	 * @param dimOnDelay the new dim on delay
	 */
	public void setDimOnDelay(Integer dimOnDelay)
	{
		this.dimOnDelay = dimOnDelay;
	}

	/**
	 * Gets the dim table.
	 *
	 * @return the dim table
	 */
	public String getDimTable()
	{
		return dimTable;
	}

	/**
	 * Sets the dim table.
	 *
	 * @param dimTable the new dim table
	 */
	public void setDimTable(String dimTable)
	{
		this.dimTable = dimTable;
	}

	/**
	 * Gets the blink slot time enum.
	 *
	 * @return the blink slot time enum
	 */
	public Integer getBlinkSlotTimeEnum()
	{
		return blinkSlotTimeEnum;
	}

	/**
	 * Sets the blink slot time enum.
	 *
	 * @param blinkSlotTimeEnum the new blink slot time enum
	 */
	public void setBlinkSlotTimeEnum(Integer blinkSlotTimeEnum)
	{
		this.blinkSlotTimeEnum = blinkSlotTimeEnum;
	}

	/* (non-Javadoc)
	 * @see com.sensus.common.model.SensusModel#toString()
	 */
	@Override
	public String toString()
	{
		return "PartNumber [getName()=" + getName() + ", getProductModelNumber()=" + getProductModelNumber()
				+ ", getProductModelMask()=" + getProductModelMask() + ", getDimOnDelay()=" + getDimOnDelay()
				+ ", getDimTable()=" + getDimTable() + ", getBlinkSlotTimeEnum()=" + getBlinkSlotTimeEnum()
				+ ", toString()=" + super.toString() + "]";
	}

}
