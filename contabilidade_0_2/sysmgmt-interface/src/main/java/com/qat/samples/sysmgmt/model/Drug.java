package com.qat.samples.sysmgmt.model;

import java.util.List;

import javax.xml.bind.annotation.XmlType;

import com.qat.framework.model.BaseModel;

/**
 * Model Object Drug Class.
 */
@SuppressWarnings("serial")
@XmlType(name = "Drug", propOrder = {"code", "description", "drugPrices"})
public class Drug extends BaseModel
{

	private String code;
	private String description;
	private List<DrugPrice> drugPrices;

	/**
	 * Instantiates a new drug.
	 */
	public Drug()
	{
	}

	/**
	 * Instantiates a new drug.
	 * 
	 * @param value the ndc code
	 * @param text the ndc desc
	 */
	public Drug(String value, String text)
	{
		setCode(value);
		setDescription(text);
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
	 * Gets the DrugPrices.
	 * 
	 * @return the DrugPrices
	 */
	public List<DrugPrice> getDrugPrices()
	{
		return drugPrices;
	}

	/**
	 * Sets the DrugPrices.
	 * 
	 * @param prices the new DrugPrices
	 */
	public void setDrugPrices(List<DrugPrice> prices)
	{
		drugPrices = prices;
	}

	@Override
	public String toString()
	{
		// return some interesting information for logging/debugging
		// avoid personally identifying information
		return "Drug [getCode()=" + getCode() + ", getDescription()=" + getDescription() + ", getDrugPrices()="
				+ getDrugPrices() + ", getModelAction()=" + getModelAction() + "]";
	}
}
