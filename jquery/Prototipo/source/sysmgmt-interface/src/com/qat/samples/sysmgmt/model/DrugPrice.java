package com.qat.samples.sysmgmt.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.xml.bind.annotation.XmlType;

import com.qat.framework.model.QATModel;
import com.qat.framework.util.QATDateUtil;

/**
 * The Model Object DrugPrice.
 */
@SuppressWarnings("serial")
@XmlType(name = "DrugPrice", propOrder = {"parentKey", "sexIndicator", "price", "effectiveDate"})
public class DrugPrice extends QATModel
{
	private String parentKey;
	private SexIndEnum sexIndicator = SexIndEnum.BOTH;
	private BigDecimal price;
	private Date effectiveDate;

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
	 * @param sexInd the price sex ind
	 * @param amount the drug price
	 * @param effectiveDate the eff date
	 */
	public DrugPrice(String key, String value, BigDecimal amount, Date date)
	{
		setParentKey(key);
		setPriceSexIndicator(SexIndEnum.enumForValue(value));
		setDrugPrice(amount);
		setEffectiveDate(date);
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
		return sexIndicator.getValue();
	}

	/**
	 * Sets the sex ind.
	 * 
	 * @param sexInd the new sex ind
	 */
	public void setPriceSexIndicator(SexIndEnum value)
	{
		sexIndicator = value;
	}

	/**
	 * Sets the sex ind value.
	 * 
	 * @param sexIndValue the new sex ind value
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
	public Date getEffectiveDate()
	{
		return effectiveDate;
	}

	/**
	 * Sets the effective date.
	 * 
	 * @param date the new effective date
	 */
	public void setEffectiveDate(Date date)
	{
		effectiveDate = QATDateUtil.truncateTime(date);
	}

	@Override
	public String toString()
	{
		return "DrugPrice [getParentKey()=" + getParentKey() + ", getPriceSexIndicator()=" + getPriceSexIndicator() + ", getDrugPrice()=" + getDrugPrice()
				+ ", getEffectiveDate()=" + getEffectiveDate() + ", getModelAction()=" + getModelAction()
				+ "]";
	}

}
