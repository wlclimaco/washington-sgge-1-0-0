package com.sensus.mlc.contabil.model.request;

import com.sensus.common.model.UserContext;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.lftipofisccli.model.Lftipofisccli;

/**
 * The Class InquiryLftipofisccliRequest.
 *
 * @author - Washinton
 */
public class InquiryLftipofisccliRequest extends InquiryPaginationRequest
{

	/** The lftipofisccli. */
	private Lftipofisccli lftipofisccli;

	/**
	 * Instantiates a new inquiry lftipofisccli request.
	 */
	public InquiryLftipofisccliRequest()
	{
	}

	/**
	 * Instantiates a new inquiry lftipofisccli request.
	 * 
	 * @param userContext the user context
	 */
	public InquiryLftipofisccliRequest(UserContext userContext)
	{
		super(userContext);
	}

	/**
	 * Sets the lftipofisccli.
	 * 
	 * @param lftipofisccliObject the new lftipofisccli
	 */
	public void setLftipofisccli(Lftipofisccli lftipofisccliObject)
	{
		lftipofisccli = lftipofisccliObject;
	}

	/**
	 * Gets the lftipofisccli.
	 * 
	 * @return the lftipofisccli
	 */
	public Lftipofisccli getLftipofisccli()
	{
		return lftipofisccli;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.base.model.request.LightingControlInquiryRequest#toString()
	 */
	@Override
	public String toString()
	{
		return "InquiryLftipofisccliRequest [getLftipofisccli()=" + getLftipofisccli() + ", getStartRow()=" + getStartRow() + ", getEndRow()="
				+ getEndRow() + ", getPageSize()=" + getPageSize() + ", getStartPage()=" + getStartPage()
				+ ", getSortExpressions()=" + getSortExpressions() + ", getSortExpression()=" + getSortExpression()
				+ ", isPreQueryCount()=" + isPreQueryCount() + ", getMaxPreQueryCount()=" + getMaxPreQueryCount()
				+ ", getTenant()=" + getTenant() + ", getUserContext()=" + getUserContext() + "]";
	}
}

