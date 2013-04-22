package com.sensus.mlc.contabil.model.request;

import com.sensus.common.model.UserContext;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.lfsittrib.model.Lfsittrib;

/**
 * The Class InquiryLfsittribRequest.
 *
 * @author - Washinton
 */
public class InquiryLfsittribRequest extends InquiryPaginationRequest
{

	/** The lfsittrib. */
	private Lfsittrib lfsittrib;

	/**
	 * Instantiates a new inquiry lfsittrib request.
	 */
	public InquiryLfsittribRequest()
	{
	}

	/**
	 * Instantiates a new inquiry lfsittrib request.
	 * 
	 * @param userContext the user context
	 */
	public InquiryLfsittribRequest(UserContext userContext)
	{
		super(userContext);
	}

	/**
	 * Sets the lfsittrib.
	 * 
	 * @param lfsittribObject the new lfsittrib
	 */
	public void setLfsittrib(Lfsittrib lfsittribObject)
	{
		lfsittrib = lfsittribObject;
	}

	/**
	 * Gets the lfsittrib.
	 * 
	 * @return the lfsittrib
	 */
	public Lfsittrib getLfsittrib()
	{
		return lfsittrib;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.base.model.request.LightingControlInquiryRequest#toString()
	 */
	@Override
	public String toString()
	{
		return "InquiryLfsittribRequest [getLfsittrib()=" + getLfsittrib() + ", getStartRow()=" + getStartRow() + ", getEndRow()="
				+ getEndRow() + ", getPageSize()=" + getPageSize() + ", getStartPage()=" + getStartPage()
				+ ", getSortExpressions()=" + getSortExpressions() + ", getSortExpression()=" + getSortExpression()
				+ ", isPreQueryCount()=" + isPreQueryCount() + ", getMaxPreQueryCount()=" + getMaxPreQueryCount()
				+ ", getTenant()=" + getTenant() + ", getUserContext()=" + getUserContext() + "]";
	}
}

