package com.qat.samples.sysmgmt.model;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlType;

import com.qat.framework.model.QATModelOL;

/**
 * The Model Object Procedure.
 */
@SuppressWarnings("serial")
@XmlType(name = "Bundle", propOrder = {"id", "code", "description", "price"})
public class Bundle extends QATModelOL
{

	/** The id. */
	private Integer id;

	/** The code. */
	private String code;

	/** The description. */
	private String description;

	/** The price. */
	private BigDecimal price;

	/**
	 * Instantiates a new bundle.
	 */
	public Bundle()
	{

	}

	/**
	 * Instantiates a new bundle.
	 * 
	 * @param id the id
	 */
	public Bundle(Integer id)
	{
		this.id = id;
	}

	/**
	 * Instantiates a new bundle.
	 * 
	 * @param id the id
	 * @param code the code
	 */
	public Bundle(Integer id, String code)
	{
		this.id = id;
		this.code = code;
	}

	/**
	 * Instantiates a new bundle.
	 * 
	 * @param id the id
	 * @param code the code
	 * @param description the description
	 */
	public Bundle(Integer id, String code, String description)
	{
		this.id = id;
		this.code = code;
		this.description = description;
	}

	/**
	 * Instantiates a new bundle.
	 * 
	 * @param id the id
	 * @param code the code
	 * @param description the description
	 * @param price the price
	 */
	public Bundle(Integer id, String code, String description, BigDecimal price)
	{
		this.id = id;
		this.code = code;
		this.description = description;
		this.price = price;
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
	 * Gets the code.
	 * 
	 * @return the code
	 */
	public String getCode()
	{
		return code;
	}

	/**
	 * Sets the code.
	 * 
	 * @param code the new code
	 */
	public void setCode(String code)
	{
		this.code = code;
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

	/**
	 * Gets the price.
	 * 
	 * @return the price
	 */
	public BigDecimal getPrice()
	{
		return price;
	}

	/**
	 * Sets the price.
	 * 
	 * @param price the new price
	 */
	public void setPrice(BigDecimal price)
	{
		this.price = price;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.QATModel#toString()
	 */
	@Override
	public String toString()
	{
		return "Bundle [getId()=" + getId() + ", getCode()=" + getCode() + ", getDescription()=" + getDescription()
				+ ", getPrice()=" + getPrice() + "]";
	}

}
