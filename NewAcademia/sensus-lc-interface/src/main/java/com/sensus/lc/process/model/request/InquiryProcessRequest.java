package com.sensus.lc.process.model.request;

import com.sensus.common.model.UserContext;
import com.sensus.lc.base.model.request.InquiryPaginationRequest;
import com.sensus.lc.process.model.ProcessFilter;

/**
 * The Class InquiryProcessRequest.
 */
public class InquiryProcessRequest extends InquiryPaginationRequest
{
	/** The process filter. */
	private ProcessFilter processFilter;

	/**
	 * Instantiates a new inquiry process request.
	 */
	public InquiryProcessRequest()
	{
	}

	/**
	 * Instantiates a new inquiry process request.
	 * 
	 * @param userContext the user context
	 */
	public InquiryProcessRequest(UserContext userContext)
	{
		super(userContext);
	}

	/**
	 * Sets the process filter.
	 * 
	 * @param processFilter the new process filter
	 */
	public void setProcessFilter(ProcessFilter processFilter)
	{
		this.processFilter = processFilter;
	}

	/**
	 * Gets the process filter.
	 * 
	 * @return the process filter
	 */
	public ProcessFilter getProcessFilter()
	{
		return processFilter;
	}

}
