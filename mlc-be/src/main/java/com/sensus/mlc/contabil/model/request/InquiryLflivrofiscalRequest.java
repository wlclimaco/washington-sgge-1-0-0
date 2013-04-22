package com.sensus.mlc.contabil.model.request;

import com.sensus.common.model.UserContext;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.lflivrofiscal.model.Lflivrofiscal;

/**
 * The Class InquiryLflivrofiscalRequest.
 *
 * @author - Washinton
 */
public class InquiryLflivrofiscalRequest extends InquiryPaginationRequest
{

	/** The lflivrofiscal. */
	private Lflivrofiscal lflivrofiscal;

	/**
	 * Instantiates a new inquiry lflivrofiscal request.
	 */
	public InquiryLflivrofiscalRequest()
	{
	}

	/**
	 * Instantiates a new inquiry lflivrofiscal request.
	 * 
	 * @param userContext the user context
	 */
	public InquiryLflivrofiscalRequest(UserContext userContext)
	{
		super(userContext);
	}

	/**
	 * Sets the lflivrofiscal.
	 * 
	 * @param lflivrofiscalObject the new lflivrofiscal
	 */
	public void setLflivrofiscal(Lflivrofiscal lflivrofiscalObject)
	{
		lflivrofiscal = lflivrofiscalObject;
	}

	/**
	 * Gets the lflivrofiscal.
	 * 
	 * @return the lflivrofiscal
	 */
	public Lflivrofiscal getLflivrofiscal()
	{
		return lflivrofiscal;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.base.model.request.LightingControlInquiryRequest#toString()
	 */
	@Override
	public String toString()
	{
		return "InquiryLflivrofiscalRequest [getLflivrofiscal()=" + getLflivrofiscal() + ", getStartRow()=" + getStartRow() + ", getEndRow()="
				+ getEndRow() + ", getPageSize()=" + getPageSize() + ", getStartPage()=" + getStartPage()
				+ ", getSortExpressions()=" + getSortExpressions() + ", getSortExpression()=" + getSortExpression()
				+ ", isPreQueryCount()=" + isPreQueryCount() + ", getMaxPreQueryCount()=" + getMaxPreQueryCount()
				+ ", getTenant()=" + getTenant() + ", getUserContext()=" + getUserContext() + "]";
	}
}

