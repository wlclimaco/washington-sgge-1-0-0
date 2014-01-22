package com.sensus.lc.light.model.criteria;

import static com.sensus.common.validation.ValidationUtil.isNull;

/**
 * Criteria object for process.
 */
public class ProcessCriteria
{

	/**
	 * Attributes.
	 */
	private Integer processId;
	private Boolean monitored;
	private Boolean failed;

	/**
	 * Gets the process id.
	 * 
	 * @return the processId
	 */
	public Integer getProcessId()
	{
		return processId;
	}

	/**
	 * Sets the process id.
	 * 
	 * @param processId the processId to set
	 */
	public void setProcessId(Integer processId)
	{
		this.processId = processId;
	}

	/**
	 * @return the monitored
	 */
	public Boolean isMonitored()
	{
		return monitored;
	}

	/**
	 * @param monitored the monitored to set
	 */
	public void setMonitored(Boolean monitored)
	{
		this.monitored = monitored;
	}

	/**
	 * Checks if is failed.
	 * 
	 * @return the boolean
	 */
	public Boolean isFailed()
	{
		return failed;
	}

	/**
	 * Sets the failed.
	 * 
	 * @param failed the new failed
	 */
	public void setFailed(Boolean failed)
	{
		this.failed = failed;
	}

	/**
	 * Checks for filter.
	 * 
	 * @return true, if successful
	 */
	public boolean hasFilter()
	{
		return !isNull(getProcessId()) || !isNull(isFailed());
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "ProcessCriteria [processId=" + processId + ", monitored=" + monitored + "]";
	}

}
