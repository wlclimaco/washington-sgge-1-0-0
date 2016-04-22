package com.qat.samples.sysmgmt.model;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlType;

import com.qat.framework.model.BaseModel;

// TODO: Auto-generated Javadoc
/**
 * The Model Object DrugPrice.
 */
@SuppressWarnings("serial")
@XmlType(name = "DrugPrice", propOrder = {"parentKey", "sexIndicator", "price", "effectiveDateUTC"})
public class DrugPrice extends BaseModel
{

	/** The parent key. */
	private String parentKey;

	/** The sex indicator. */
	private SexIndEnum sexIndicator = SexIndEnum.BOTH;

	/** The price. */
	private BigDecimal price;

	/** The effective date utc. */
	private Long effectiveDateUTC;

	/**
	 * Instantiates a new drug price.
	 */
	public DrugPrice()
	{
	}

	/**
	 * Instantiates a new drug price.
	 * 
	 * @param key the parent key
	 * @param value the value
	 * @param amount the drug price
	 * @param date the date
	 */
	public DrugPrice(String key, String value, BigDecimal amount, Long date)
	{
		setParentKey(key);
		setPriceSexIndicator(SexIndEnum.enumForValue(value));
		setDrugPrice(amount);
		setEffectiveDateUTC(date);
	}

	/**
	 * Gets the parent key.
	 * 
	 * @return the parent key
	 */
	public String getParentKey()
	{
		return parentKey;
	}

	/**
	 * Sets the parent key.
	 * 
	 * @param key the new parent key
	 */
	public void setParentKey(String key)
	{
		parentKey = key;
	}

	/**
	 * Gets the sex ind.
	 * 
	 * @return the sex ind
	 */
	public SexIndEnum getPriceSexIndicator()
	{
		return sexIndicator;
	}

	/**
	 * Gets the sex ind value.
	 * 
	 * @return the sex ind value
	 */
	public String getPriceSexIndicatorValue()
	{
		if (sexIndicator != null)
		{
			return sexIndicator.getValue();
		}
		return null;
	}

	/**
	 * Sets the sex ind.
	 * 
	 * @param value the new price sex indicator
	 */
	public void setPriceSexIndicator(SexIndEnum value)
	{
		sexIndicator = value;
	}

	/**
	 * Sets the sex ind value.
	 * 
	 * @param value the new price sex indicator value
	 */
	public void setPriceSexIndicatorValue(String value)
	{
		sexIndicator = SexIndEnum.enumForValue(value);
	}

	/**
	 * Gets the price.
	 * 
	 * @return the price
	 */
	public BigDecimal getDrugPrice()
	{
		return price;
	}

	/**
	 * Sets the price.
	 * 
	 * @param value the new price
	 */
	public void setDrugPrice(BigDecimal value)
	{
		price = value;
	}

	/**
	 * Gets the effective date.
	 * 
	 * @return the effective date
	 */
	public Long getEffectiveDateUTC()
	{
		return effectiveDateUTC;
	}

	/**
	 * Sets the effective date.
	 * 
	 * @param date the new effective date
	 */
	public void setEffectiveDateUTC(Long date)
	{
		effectiveDateUTC = date;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.BaseModel#toString()
	 */
	@Override
	public String toString()
	{
		return "DrugPrice [getParentKey()=" + getParentKey() + ", getPriceSexIndicator()=" + getPriceSexIndicator()
				+ ", getDrugPrice()=" + getDrugPrice()
				+ ", getEffectiveDateUTC()=" + getEffectiveDateUTC() + "]";
	}

}
