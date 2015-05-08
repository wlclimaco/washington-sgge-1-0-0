package com.prosperitasglobal.sendsolv.model;

import java.util.ArrayList;
import java.util.List;

import com.prosperitasglobal.cbof.model.Country;
import com.prosperitasglobal.cbof.model.StateProvinceRegion;
import com.qat.framework.model.QATModel;
import com.qat.framework.util.QATConvertUtil;
import com.qat.framework.validation.ValidationUtil;

/**
 * Represents a payer in the SendSolv system.
 */
@SuppressWarnings("serial")
public class Payer extends QATModel
{
	/** The SendSolv id of the payer. */
	private Integer id;

	/** The automated clearing house payee code. */
	private String achPayeeCode;

	/** The SendSolv id for the automated clearing house. */
	private Integer automatedClearingHouseId;

	/** The name of the payer. */
	private String name;

	/** The country of the payer. */
	private Country country;

	/** The payment type list of the payer. */
	private List<PaymentType> paymentTypeList;

	/** Is post funding allowed for the payer? */
	private Boolean postfundAllowed;

	/** The state/province/region list of the payer. */
	private List<StateProvinceRegion> stateProvinceRegionList;

	/** The list of daily currency rates for the payer. */
	private List<DailyCurrencyRate> dailyCurrencyRateList;

	/** The list of available currency amounts for the payer. */
	private List<CurrencyAvailability> currencyAvailabilityList;

	/**
	 * Get the SendSolv id of the payer.
	 *
	 * @return The id.
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * Set the SendSolv id of the payer.
	 *
	 * @param id The id to set.
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * Get the automated clearing house payee code.
	 *
	 * @return The automated clearing house payee code.
	 */
	public String getAchPayeeCode()
	{
		return achPayeeCode;
	}

	/**
	 * Set the automated clearing house payee code.
	 *
	 * @param achPayeeCode The automated clearing house payee code to set.
	 */
	public void setAchPayeeCode(String achPayeeCode)
	{
		this.achPayeeCode = achPayeeCode;
	}

	/**
	 * Get the SendSolv id for the automated clearing house.
	 *
	 * @return The id.
	 */
	public Integer getAutomatedClearingHouseId()
	{
		return automatedClearingHouseId;
	}

	/**
	 * Set the SendSolv id for the automated clearing house.
	 *
	 * @param automatedClearingHouseId The id.
	 */
	public void setAutomatedClearingHouseId(Integer automatedClearingHouseId)
	{
		this.automatedClearingHouseId = automatedClearingHouseId;
	}

	/**
	 * Get the name of the payer.
	 *
	 * @return The name.
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Set the name of the payer.
	 *
	 * @param name The name to set.
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * Get the country of the payer.
	 *
	 * @return The country.
	 */
	public Country getCountry()
	{
		return country;
	}

	/**
	 * Set the country of the payer.
	 *
	 * @param country the country to set
	 */
	public void setCountry(Country country)
	{
		this.country = country;
	}

	/**
	 * Get the payment type list of the payer.
	 *
	 * @return The payment type list.
	 */
	public List<PaymentType> getPaymentTypeList()
	{
		if (ValidationUtil.isNull(paymentTypeList))
		{
			setPaymentTypeList(new ArrayList<PaymentType>());
		}

		return paymentTypeList;
	}

	/**
	 * Set the payment type list of the payer.
	 *
	 * @param paymentTypeList The payment type list to set.
	 */
	public void setPaymentTypeList(List<PaymentType> paymentTypeList)
	{
		this.paymentTypeList = paymentTypeList;
	}

	/**
	 * Get the list of daily currency rates for the payer.
	 *
	 * @return The daily currency rates list.
	 */
	public List<DailyCurrencyRate> getDailyCurrencyRateList()
	{
		if (ValidationUtil.isNull(dailyCurrencyRateList))
		{
			setDailyCurrencyRateList(new ArrayList<DailyCurrencyRate>());
		}

		return dailyCurrencyRateList;
	}

	/**
	 * Set the list of daily currency rates for the payer.
	 *
	 * @param dailyCurrencyRateList The daily currency rates list to set.
	 */
	public void setDailyCurrencyRateList(List<DailyCurrencyRate> dailyCurrencyRateList)
	{
		this.dailyCurrencyRateList = dailyCurrencyRateList;
	}

	/**
	 * Get the list of available currency amounts for the payer.
	 *
	 * @return The available currency list.
	 */
	public List<CurrencyAvailability> getCurrencyAvailabilityList()
	{
		if (ValidationUtil.isNull(currencyAvailabilityList))
		{
			setCurrencyAvailabilityList(new ArrayList<CurrencyAvailability>());
		}

		return currencyAvailabilityList;
	}

	/**
	 * Set the list of available currency amounts for the payer.
	 *
	 * @param currencyAvailabilityList The available currency list to set.
	 */
	public void setCurrencyAvailabilityList(List<CurrencyAvailability> currencyAvailabilityList)
	{
		this.currencyAvailabilityList = currencyAvailabilityList;
	}

	/**
	 * Get the answer to whether the payer can do post funding.
	 *
	 * @return The answer.
	 */
	public Boolean isPostfundAllowed()
	{
		if (postfundAllowed == null)
		{
			return Boolean.FALSE;
		}

		return postfundAllowed;
	}

	/**
	 * Get the answer to whether the payer can do post funding as a String value.
	 *
	 * @return The answer.
	 */
	public String getPostfundAllowedValue()
	{
		return QATConvertUtil.fromBoolean(isPostfundAllowed());
	}

	/**
	 * Set the answer to whether the payer can do post funding.
	 *
	 * @param postfundAllowed The answer.
	 */
	public void setPostfundAllowed(Boolean postfundAllowed)
	{
		this.postfundAllowed = postfundAllowed;
	}

	/**
	 * Set the answer to whether the payer can do post funding as a String value.
	 *
	 * @param postFundAllowedValue The answer as a String value to set.
	 */
	public void setPostfundAllowedValue(String postFundAllowedValue)
	{
		postfundAllowed = QATConvertUtil.toBoolean(postFundAllowedValue);
	}

	/**
	 * Get the state/province/region list of the payer.
	 *
	 * @return The state/province/region list.
	 */
	public List<StateProvinceRegion> getStateProvinceRegionList()
	{
		if (ValidationUtil.isNull(stateProvinceRegionList))
		{
			setStateProvinceRegionList(new ArrayList<StateProvinceRegion>());
		}

		return stateProvinceRegionList;
	}

	/**
	 * Set the state/province/region list of the payer.
	 *
	 * @param stateProvinceRegionList The state/province/region list to set.
	 */
	public void setStateProvinceRegionList(List<StateProvinceRegion> stateProvinceRegionList)
	{
		this.stateProvinceRegionList = stateProvinceRegionList;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Payer [getId()=" + getId() + ", getAchPayeeCode()=" + getAchPayeeCode()
				+ ", getAutomatedClearingHouseId()=" + getAutomatedClearingHouseId() + ", getName()=" + getName()
				+ ", getCountry()=" + getCountry() + ", getPaymentTypeList()=" + getPaymentTypeList()
				+ ", getDailyCurrencyRateList()=" + getDailyCurrencyRateList() + ", getCurrencyAvailabilityList()="
				+ getCurrencyAvailabilityList() + ", isPostfundAllowed()=" + isPostfundAllowed()
				+ ", getPostfundAllowedValue()=" + getPostfundAllowedValue() + ", getStateProvinceRegionList()="
				+ getStateProvinceRegionList() + ", toString()=" + super.toString() + "]";
	}
}
