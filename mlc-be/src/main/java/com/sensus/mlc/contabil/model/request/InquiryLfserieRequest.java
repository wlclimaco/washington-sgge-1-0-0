package com.sensus.mlc.contabil.model.request;

import com.sensus.common.model.UserContext;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.lfserie.model.Lfserie;

/**
 * The Class InquiryLfserieRequest.
 *
 * @author - Washinton
 */
public class InquiryLfserieRequest extends InquiryPaginationRequest
{

	/** The lfserie. */
	private Lfserie lfserie;

	/**
	 * Instantiates a new inquiry lfserie request.
	 */
	public InquiryLfserieRequest()
	{
	}

	/**
	 * Instantiates a new inquiry lfserie request.
	 * 
	 * @param userContext the user context
	 */
	public InquiryLfserieRequest(UserContext userContext)
	{
		super(userContext);
	}

	/**
	 * Sets the lfserie.
	 * 
	 * @param lfserieObject the new lfserie
	 */
	public void setLfserie(Lfserie lfserieObject)
	{
		lfserie = lfserieObject;
	}

	/**
	 * Gets the lfserie.
	 * 
	 * @return the lfserie
	 */
	public Lfserie getLfserie()
	{
		return lfserie;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.base.model.request.LightingControlInquiryRequest#toString()
	 */
	@Override
	public String toString()
	{
		return "InquiryLfserieRequest [getLfserie()=" + getLfserie() + ", getStartRow()=" + getStartRow() + ", getEndRow()="
				+ getEndRow() + ", getPageSize()=" + getPageSize() + ", getStartPage()=" + getStartPage()
				+ ", getSortExpressions()=" + getSortExpressions() + ", getSortExpression()=" + getSortExpression()
				+ ", isPreQueryCount()=" + isPreQueryCount() + ", getMaxPreQueryCount()=" + getMaxPreQueryCount()
				+ ", getTenant()=" + getTenant() + ", getUserContext()=" + getUserContext() + "]";
	}
}

