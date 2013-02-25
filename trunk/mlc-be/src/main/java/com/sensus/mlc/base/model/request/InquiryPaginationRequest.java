package com.sensus.mlc.base.model.request;

import java.util.Date;

import com.sensus.common.model.UserContext;

/**
 * The Class InquiryPaginationRequest.
 */
public class InquiryPaginationRequest extends LightingControlInquiryRequest
{

	/** The fileName. */
	private String fileName;

	/** The process id. */
	private Integer processId;

	/** The initial date. */
	private Date initialDate;

	/** The end date. */
	private Date endDate;

	/**
	 * Instantiates a new inquiry pagination request.
	 */
	public InquiryPaginationRequest()
	{
	}

	/**
	 * Instantiates a new inquiry pagination request.
	 * 
	 * @param userContext the user context
	 */
	public InquiryPaginationRequest(UserContext userContext)
	{
		super(userContext);
	}

	/**
	 * Gets the fileName.
	 * 
	 * @return the fileName
	 */
	public String getFileName()
	{
		return fileName;
	}

	/**
	 * Sets the fileName.
	 * 
	 * @param fileName the new fileName
	 */
	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}

	/**
	 * Gets the process id.
	 * 
	 * @return the process id
	 */
	public Integer getProcessId()
	{
		return processId;
	}

	/**
	 * Sets the process id.
	 * 
	 * @param processId the new process id
	 */
	public void setProcessId(Integer processId)
	{
		this.processId = processId;
	}

	/**
	 * Gets the initial date.
	 * 
	 * @return the initial date
	 */
	public Date getInitialDate()
	{
		return initialDate;
	}

	/**
	 * Sets the initial date.
	 * 
	 * @param initialDate the new initial date
	 */
	public void setInitialDate(Date initialDate)
	{
		this.initialDate = initialDate;
	}

	/**
	 * Gets the end date.
	 * 
	 * @return the end date
	 */
	public Date getEndDate()
	{
		return endDate;
	}

	/**
	 * Sets the end date.
	 * 
	 * @param endDate the new end date
	 */
	public void setEndDate(Date endDate)
	{
		this.endDate = endDate;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.base.model.request.LightingControlInquiryRequest#toString()
	 */
	@Override
	public String toString()
	{
		return "ExportDataRequest [getFileName()=" + getFileName() + ", getProcessId()=" + getProcessId()
				+ ", getInitialDate()=" + getInitialDate() + ", getEndDate()=" + getEndDate() + "]";
	}

}
