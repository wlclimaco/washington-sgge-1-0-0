package com.prosperitasglobal.sendsolv.model.criteria;

import java.io.Serializable;
import java.util.List;

import com.prosperitasglobal.sendsolv.model.MoneyTransferBatchStatusEnum;

/**
 * Criteria member used for doing a lookup on a money transfer batch in the SendSolv system.
 * <p>
 * The properties specified in this class are used when fetching rows from the data store. If a property value is
 * <code>null</code>, then it won't be including in the matching logic. Only properties that have a value will be used.
 */
@SuppressWarnings("serial")
public class MoneyTransferBatchCriteria implements Serializable
{
	/** The SendSolv id of the location. */
	private Integer locationId;

	/** The organization id. */
	private Integer organizationId;

	/** The status list. */
	private List<MoneyTransferBatchStatusEnum> statusList;

	/** The transfer date. */
	private Long transferDate;

	/**
	 * Default constructor.
	 */
	public MoneyTransferBatchCriteria()
	{
		super();
	}

	/**
	 * The Constructor.
	 *
	 * @param statusList the status list
	 */
	public MoneyTransferBatchCriteria(List<MoneyTransferBatchStatusEnum> statusList)
	{
		this.statusList = statusList;
	}

	/**
	 * Get the SendSolv id of the location.
	 *
	 * @return The id.
	 */
	public Integer getLocationId()
	{
		return locationId;
	}

	/**
	 * Set the SendSolv id of the location.
	 *
	 * @param locationId The id to set.
	 */
	public void setLocationId(Integer locationId)
	{
		this.locationId = locationId;
	}

	/**
	 * Gets the status list.
	 *
	 * @return the statusList
	 */
	public List<MoneyTransferBatchStatusEnum> getStatusList()
	{
		return statusList;
	}

	/**
	 * Sets the status list.
	 *
	 * @param statusList the statusList to set
	 */
	public void setStatusList(List<MoneyTransferBatchStatusEnum> statusList)
	{
		this.statusList = statusList;
	}

	/**
	 * Get the transfer date.
	 *
	 * @return The date.
	 */
	public Long getTransferDate()
	{
		return transferDate;
	}

	/**
	 * Set the transfer date.
	 *
	 * @param transferDate The date.
	 */
	public void setTransferDate(Long transferDate)
	{
		this.transferDate = transferDate;
	}

	/**
	 * Gets the organization id.
	 *
	 * @return the organizationId
	 */
	public Integer getOrganizationId()
	{
		return organizationId;
	}

	/**
	 * Sets the organization id.
	 *
	 * @param organizationId the organizationId to set
	 */
	public void setOrganizationId(Integer organizationId)
	{
		this.organizationId = organizationId;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "MoneyTransferBatchCriteria [getLocationId()=" + getLocationId() + ", getStatusList()="
				+ getStatusList() + ", getTransferDate()=" + getTransferDate() + ", getOrganizationId()="
				+ getOrganizationId() + ", toString()=" + super.toString() + "]";
	}

}
