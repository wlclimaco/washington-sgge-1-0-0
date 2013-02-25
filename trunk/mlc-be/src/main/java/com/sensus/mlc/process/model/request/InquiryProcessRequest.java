package com.sensus.mlc.process.model.request;

import com.sensus.common.model.UserContext;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.process.model.ProcessFilter;

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

	@Override
	public String toString()
	{
		return "InquiryProcessRequest [getProcessFilter()=" + getProcessFilter() + ", getFileName()=" + getFileName()
				+ ", getProcessId()=" + getProcessId() + ", toString()=" + super.toString() + ", getStartRow()="
				+ getStartRow() + ", getEndRow()=" + getEndRow() + ", getPageSize()=" + getPageSize()
				+ ", getStartPage()=" + getStartPage() + ", getSortExpressions()=" + getSortExpressions()
				+ ", getSortExpression()=" + getSortExpression() + ", isPreQueryCount()=" + isPreQueryCount()
				+ ", getMaxPreQueryCount()=" + getMaxPreQueryCount() + ", getListColumn()=" + getListColumn()
				+ ", isMonitored()=" + isMonitored() + ", getSearchLight()=" + getSearchLight()
				+ ", getPaginationAllSelected()=" + getPaginationAllSelected() + ", getSelectionPaginationIds()="
				+ getSelectionPaginationIds() + ", getUnselectionPaginationIds()=" + getUnselectionPaginationIds()
				+ ", isCurrentLightStatus()=" + isCurrentLightStatus() + ", getTenant()=" + getTenant()
				+ ", getAllowedGroupIdList()=" + getAllowedGroupIdList() + ", getStringAllowedGroups()="
				+ getStringAllowedGroups() + ", getTimezone()=" + getTimezone() + ", getUserContext()="
				+ getUserContext() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}
}
