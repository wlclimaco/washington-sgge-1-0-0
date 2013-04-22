package com.sensus.mlc.contabil.model.request;

import com.sensus.common.model.UserContext;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.lfregrafiscal.model.Lfregrafiscal;

/**
 * The Class InquiryLfregrafiscalRequest.
 *
 * @author - Washinton
 */
public class InquiryLfregrafiscalRequest extends InquiryPaginationRequest
{

	/** The lfregrafiscal. */
	private Lfregrafiscal lfregrafiscal;

	/**
	 * Instantiates a new inquiry lfregrafiscal request.
	 */
	public InquiryLfregrafiscalRequest()
	{
	}

	/**
	 * Instantiates a new inquiry lfregrafiscal request.
	 * 
	 * @param userContext the user context
	 */
	public InquiryLfregrafiscalRequest(UserContext userContext)
	{
		super(userContext);
	}

	/**
	 * Sets the lfregrafiscal.
	 * 
	 * @param lfregrafiscalObject the new lfregrafiscal
	 */
	public void setLfregrafiscal(Lfregrafiscal lfregrafiscalObject)
	{
		lfregrafiscal = lfregrafiscalObject;
	}

	/**
	 * Gets the lfregrafiscal.
	 * 
	 * @return the lfregrafiscal
	 */
	public Lfregrafiscal getLfregrafiscal()
	{
		return lfregrafiscal;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.base.model.request.LightingControlInquiryRequest#toString()
	 */
	@Override
	public String toString()
	{
		return "InquiryLfregrafiscalRequest [getLfregrafiscal()=" + getLfregrafiscal() + ", getStartRow()=" + getStartRow() + ", getEndRow()="
				+ getEndRow() + ", getPageSize()=" + getPageSize() + ", getStartPage()=" + getStartPage()
				+ ", getSortExpressions()=" + getSortExpressions() + ", getSortExpression()=" + getSortExpression()
				+ ", isPreQueryCount()=" + isPreQueryCount() + ", getMaxPreQueryCount()=" + getMaxPreQueryCount()
				+ ", getTenant()=" + getTenant() + ", getUserContext()=" + getUserContext() + "]";
	}
}

