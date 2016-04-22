package com.qat.samples.sysmgmt.model;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlType;

import com.qat.framework.model.ModelOL;

/**
 * The Model Object Procedure.
 */
@SuppressWarnings("serial")
@XmlType(name = "Procedure", propOrder = {"id", "code", "description", "price"})
public class Procedure extends ModelOL
{
	private Integer id;
	private String code;
	private String description;
	private BigDecimal price;

	/**
	 * Instantiates a new procedure.
	 */
	public Procedure()
	{
	}

	/**
	 * Instantiates a new procedure.
	 * 
	 * @param value the code
	 * @param text the description
	 * @param amount the price
	 */
	public Procedure(String value, String text, BigDecimal amount)
	{
		setCode(value);
		setDescription(text);
		setPrice(amount);
	}

	/**
	 * Instantiates a new procedure.
	 * 
	 * @param identifier the id
	 * @param value the code
	 * @param text the description
	 * @param amount the price
	 */
	public Procedure(Integer identifier, String value, String text, BigDecimal amount)
	{
		setId(identifier);
		setCode(value);
		setDescription(text);
		setPrice(amount);
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
	 * @param identifier the new id
	 */
	public void setId(Integer identifier)
	{
		id = identifier;
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
	 * @param value the new code
	 */
	public void setCode(String value)
	{
		code = value;
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
	 * @param value the new description
	 */
	public void setDescription(String value)
	{
		description = value;
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
	 * @param value the new price
	 */
	public void setPrice(BigDecimal value)
	{
		price = value;
	}

	@Override
	public String toString()
	{
		// return some interesting information for logging/debugging
		// avoid personally identifying information
		return "Procedure [getCode()=" + getCode() + ", getDescription()=" + getDescription() + ", getId()=" + getId()
				+ ", getPrice()=" + getPrice() + ", getVersion()=" + getVersion() + ", getModelAction()="
				+ getModelAction() + "]";
	}

}
