package com.prosperitasglobal.sendsolv.payer.model.request.criteria;

import java.io.Serializable;

import com.prosperitasglobal.sendsolv.payer.model.PayerCountry;
import com.qat.framework.validation.ValidationUtil;

/**
 * Criteria member used for doing a lookup on a payer state province region in the SendSolv system.
 * <p>
 * The properties specified in this class are used when fetching rows from the data store. If a property value is
 * <code>null</code>, then it won't be including in the matching logic. Only properties that have a value will be used.
 */
@SuppressWarnings("serial")
public class PayerStateProvinceRegionCriteria implements Serializable
{
	/** The payer_state_province_region_id */
	private Integer id;

	/** The full name of the state/province/region */
	private String name;

	/** The long name. */
	private String longName;

	private PayerCountry country;

	private Integer payerId;

	/** The no relationship. */
	private Boolean noRelationship;

	/** Default Constructor */
	public PayerStateProvinceRegionCriteria()
	{
		super();
	}

	/**
	 * @return the id
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * @return the country
	 */
	public PayerCountry getCountry()
	{
		if (ValidationUtil.isNull(country))
		{
			setCountry(new PayerCountry());
		}

		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(PayerCountry country)
	{
		this.country = country;
	}

	/**
	 * @return the payerId
	 */
	public Integer getPayerId()
	{
		return payerId;
	}

	/**
	 * @param payerId the payerId to set
	 */
	public void setPayerId(Integer payerId)
	{
		this.payerId = payerId;
	}

	/**
	 * @return the noRelationship
	 */
	public Boolean getNoRelationship()
	{
		return noRelationship;
	}

	/**
	 * @param noRelationship the noRelationship to set
	 */
	public void setNoRelationship(Boolean noRelationship)
	{
		this.noRelationship = noRelationship;
	}

	/**
	 * @return the longName
	 */
	public String getLongName()
	{
		return longName;
	}

	/**
	 * @param longName the longName to set
	 */
	public void setLongName(String longName)
	{
		this.longName = longName;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "PayerStateProvinceRegionCriteria [getId()=" + getId() + ", getName()=" + getName() + ", getCountry()="
				+ getCountry() + ", getPayerId()=" + getPayerId() + ", getNoRelationship()=" + getNoRelationship()
				+ "]";
	}

}
