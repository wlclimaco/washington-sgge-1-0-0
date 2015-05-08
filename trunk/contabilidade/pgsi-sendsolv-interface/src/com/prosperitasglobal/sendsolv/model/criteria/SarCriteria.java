package com.prosperitasglobal.sendsolv.model.criteria;

import java.io.Serializable;

/**
 * Criteria member used for a lookup on a suspicious activity in the SendSolv system.
 * <p>
 * The properties specified in this class are used when fetching rows from the data store. If a property value is
 * <code>null</code>, then it won't be including in the matching logic. Only properties that have a value will be used.
 */
@SuppressWarnings("serial")
public class SarCriteria implements Serializable
{
	private Integer id;
	private String businessKey;
	private Boolean retrieveAllForBusinessKey;
	private Long beginningRangeDateTime;
	private Long endingRangeDateTime;
	private Integer targetId;

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getBusinessKey()
	{
		return businessKey;
	}

	public void setBusinessKey(String businessKey)
	{
		this.businessKey = businessKey;
	}

	public Boolean getRetrieveAllForBusinessKey()
	{
		return retrieveAllForBusinessKey;
	}

	public void setRetrieveAllForBusinessKey(Boolean retrieveAllForBusinessKey)
	{
		this.retrieveAllForBusinessKey = retrieveAllForBusinessKey;
	}

	public Long getBeginningRangeDateTime()
	{
		return beginningRangeDateTime;
	}

	public void setBeginningRangeDateTime(Long beginningRangeDateTime)
	{
		this.beginningRangeDateTime = beginningRangeDateTime;
	}

	public Long getEndingRangeDateTime()
	{
		return endingRangeDateTime;
	}

	public void setEndingRangeDateTime(Long endingRangeDateTime)
	{
		this.endingRangeDateTime = endingRangeDateTime;
	}

	public Integer getTargetId()
	{
		return targetId;
	}

	public void setTargetId(Integer targetId)
	{
		this.targetId = targetId;
	}

	@Override
	public String toString()
	{
		return "SarCriteria [getId()=" + getId() + ", getBusinessKey()=" + getBusinessKey()
				+ ", getRetrieveAllForBusinessKey()=" + getRetrieveAllForBusinessKey()
				+ ", getBeginningRangeDateTime()=" + getBeginningRangeDateTime() + ", getEndingRangeDateTime()="
				+ getEndingRangeDateTime() + ", getTargetId()=" + getTargetId() + "]";
	}
}
