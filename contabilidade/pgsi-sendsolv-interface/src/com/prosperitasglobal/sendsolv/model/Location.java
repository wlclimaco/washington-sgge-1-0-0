package com.prosperitasglobal.sendsolv.model;

import java.util.ArrayList;
import java.util.List;

import com.qat.framework.validation.ValidationUtil;

/**
 * The Class Location. Specializes {@link Business} and represents different possible business units for an
 * {@link Organization}
 */
@SuppressWarnings("serial")
public class Location extends Business
{
	/** The batch approval day limit. */
	private Integer batchApprovalDayLimit;

	/** The list of product plans that this location contains. */
	private List<BusinessProductPlan> businessProductPlanList;

	/** The change transfer day limit attribute. */
	private Integer changeTransferDayLimit;

	/** The list of frequency based even lists that this location contains. */
	private List<FrequencyBasedEvent> frequencyBasedEventList;

	/** The funds transfer day limit. */
	private Integer fundsTransferDayLimit;

	/** The SendSolv id of the parent organization. */
	private Integer parentOrganizationId;

	/** The name of the parent organization. */
	private String parentOrganizationName;

	/**
	 * The Constructor.
	 */
	public Location()
	{

	}

	/**
	 * Get the batch approval day limit.
	 *
	 * @return The batch approval day limit.
	 */
	public Integer getBatchApprovalDayLimit()
	{
		return batchApprovalDayLimit;
	}

	/**
	 * Set the batch approval day limit.
	 *
	 * @param batchApprovalDayLimit The batch approval day limit to set.
	 */
	public void setBatchApprovalDayLimit(Integer batchApprovalDayLimit)
	{
		this.batchApprovalDayLimit = batchApprovalDayLimit;
	}

	/**
	 * Get the list of business product plans.
	 *
	 * @return The business product plan list.
	 */
	public List<BusinessProductPlan> getBusinessProductPlanList()
	{
		if (ValidationUtil.isNull(businessProductPlanList))
		{
			setBusinessProductPlanList(new ArrayList<BusinessProductPlan>());
		}
		return businessProductPlanList;
	}

	/**
	 * Set the list of business product plans.
	 *
	 * @param businessProductPlanList The business product plan list to set.
	 */
	public void setBusinessProductPlanList(List<BusinessProductPlan> businessProductPlanList)
	{
		this.businessProductPlanList = businessProductPlanList;
	}

	/**
	 * Get the change transfer day limit.
	 *
	 * @return The change transfer day limit.
	 */
	public Integer getChangeTransferDayLimit()
	{
		return changeTransferDayLimit;
	}

	/**
	 * Set the change transfer day limit.
	 *
	 * @param changeTransferDayLimit The change transfer day limit to set.
	 */
	public void setChangeTransferDayLimit(Integer changeTransferDayLimit)
	{
		this.changeTransferDayLimit = changeTransferDayLimit;
	}

	/**
	 * Get the frequency based event list.
	 *
	 * @return The frequency based event list.
	 */
	public List<FrequencyBasedEvent> getFrequencyBasedEventList()
	{
		if (ValidationUtil.isNull(frequencyBasedEventList))
		{
			setFrequencyBasedEventList(new ArrayList<FrequencyBasedEvent>());
		}
		return frequencyBasedEventList;
	}

	/**
	 * Set the frequency based event list.
	 *
	 * @param frequencyBasedEventList The frequency based event list to set.
	 */
	public void setFrequencyBasedEventList(List<FrequencyBasedEvent> frequencyBasedEventList)
	{
		this.frequencyBasedEventList = frequencyBasedEventList;
	}

	/**
	 * Get the funds transfer day limit.
	 *
	 * @return The funds transfer day limit.
	 */
	public Integer getFundsTransferDayLimit()
	{
		return fundsTransferDayLimit;
	}

	/**
	 * Set the funds transfer day limit.
	 *
	 * @param fundsTransferDayLimit The funds transfer day limit to set.
	 */
	public void setFundsTransferDayLimit(Integer fundsTransferDayLimit)
	{
		this.fundsTransferDayLimit = fundsTransferDayLimit;
	}

	/**
	 * Get the parent organization id.
	 *
	 * @return The parent Organization Id.
	 */
	public Integer getParentOrganizationId()
	{
		return parentOrganizationId;
	}

	/**
	 * Set the parent organization id.
	 *
	 * @param parentOrganizationId The parent organization id to set.
	 */
	public void setParentOrganizationId(Integer parentOrganizationId)
	{
		this.parentOrganizationId = parentOrganizationId;
	}

	/**
	 * Gets the parent organization name.
	 *
	 * @return the parent organization name
	 */
	public String getParentOrganizationName()
	{
		return parentOrganizationName;
	}

	/**
	 * Sets the parent organization name.
	 *
	 * @param parentOrganizationName the parent organization name
	 */
	public void setParentOrganizationName(String parentOrganizationName)
	{
		this.parentOrganizationName = parentOrganizationName;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Location [getBatchApprovalDayLimit()=" + getBatchApprovalDayLimit() + ", getBusinessProductPlanList()="
				+ getBusinessProductPlanList() + ", getChangeTransferDayLimit()=" + getChangeTransferDayLimit()
				+ ", getFrequencyBasedEventList()=" + getFrequencyBasedEventList() + ", getFundsTransferDayLimit()="
				+ getFundsTransferDayLimit() + ", getParentOrganizationId()=" + getParentOrganizationId()
				+ ", getParentOrganizationName()=" + getParentOrganizationName() + ", toString()=" + super.toString()
				+ "]";
	}
}