package com.sensus.mlc.contabil.model.request;

import com.sensus.common.model.UserContext;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.lfnatoper.model.Lfnatoper;

/**
 * The Class InquiryLfnatoperRequest.
 *
 * @author - Washinton
 */
public class InquiryLfnatoperRequest extends InquiryPaginationRequest
{

	/** The lfnatoper. */
	private Lfnatoper lfnatoper;

	/**
	 * Instantiates a new inquiry lfnatoper request.
	 */
	public InquiryLfnatoperRequest()
	{
	}

	/**
	 * Instantiates a new inquiry lfnatoper request.
	 * 
	 * @param userContext the user context
	 */
	public InquiryLfnatoperRequest(UserContext userContext)
	{
		super(userContext);
	}

	/**
	 * Sets the lfnatoper.
	 * 
	 * @param lfnatoperObject the new lfnatoper
	 */
	public void setLfnatoper(Lfnatoper lfnatoperObject)
	{
		lfnatoper = lfnatoperObject;
	}

	/**
	 * Gets the lfnatoper.
	 * 
	 * @return the lfnatoper
	 */
	public Lfnatoper getLfnatoper()
	{
		return lfnatoper;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.base.model.request.LightingControlInquiryRequest#toString()
	 */
	@Override
	public String toString()
	{
		return "InquiryLfnatoperRequest [getLfnatoper()=" + getLfnatoper() + ", getStartRow()=" + getStartRow() + ", getEndRow()="
				+ getEndRow() + ", getPageSize()=" + getPageSize() + ", getStartPage()=" + getStartPage()
				+ ", getSortExpressions()=" + getSortExpressions() + ", getSortExpression()=" + getSortExpression()
				+ ", isPreQueryCount()=" + isPreQueryCount() + ", getMaxPreQueryCount()=" + getMaxPreQueryCount()
				+ ", getTenant()=" + getTenant() + ", getUserContext()=" + getUserContext() + "]";
	}
}

