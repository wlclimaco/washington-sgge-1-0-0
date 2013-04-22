package com.sensus.mlc.contabil.model.request;

import com.sensus.common.model.UserContext;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.lfitregrafiscal.model.Lfitregrafiscal;

/**
 * The Class InquiryLfitregrafiscalRequest.
 *
 * @author - Washinton
 */
public class InquiryLfitregrafiscalRequest extends InquiryPaginationRequest
{

	/** The lfitregrafiscal. */
	private Lfitregrafiscal lfitregrafiscal;

	/**
	 * Instantiates a new inquiry lfitregrafiscal request.
	 */
	public InquiryLfitregrafiscalRequest()
	{
	}

	/**
	 * Instantiates a new inquiry lfitregrafiscal request.
	 * 
	 * @param userContext the user context
	 */
	public InquiryLfitregrafiscalRequest(UserContext userContext)
	{
		super(userContext);
	}

	/**
	 * Sets the lfitregrafiscal.
	 * 
	 * @param lfitregrafiscalObject the new lfitregrafiscal
	 */
	public void setLfitregrafiscal(Lfitregrafiscal lfitregrafiscalObject)
	{
		lfitregrafiscal = lfitregrafiscalObject;
	}

	/**
	 * Gets the lfitregrafiscal.
	 * 
	 * @return the lfitregrafiscal
	 */
	public Lfitregrafiscal getLfitregrafiscal()
	{
		return lfitregrafiscal;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.base.model.request.LightingControlInquiryRequest#toString()
	 */
	@Override
	public String toString()
	{
		return "InquiryLfitregrafiscalRequest [getLfitregrafiscal()=" + getLfitregrafiscal() + ", getStartRow()=" + getStartRow() + ", getEndRow()="
				+ getEndRow() + ", getPageSize()=" + getPageSize() + ", getStartPage()=" + getStartPage()
				+ ", getSortExpressions()=" + getSortExpressions() + ", getSortExpression()=" + getSortExpression()
				+ ", isPreQueryCount()=" + isPreQueryCount() + ", getMaxPreQueryCount()=" + getMaxPreQueryCount()
				+ ", getTenant()=" + getTenant() + ", getUserContext()=" + getUserContext() + "]";
	}
}

