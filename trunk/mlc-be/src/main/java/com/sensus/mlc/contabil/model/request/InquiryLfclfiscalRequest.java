package com.sensus.mlc.contabil.model.request;

import com.sensus.common.model.UserContext;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.lfclfiscal.model.Lfclfiscal;

/**
 * The Class InquiryLfclfiscalRequest.
 *
 * @author - Washinton
 */
public class InquiryLfclfiscalRequest extends InquiryPaginationRequest
{

	/** The lfclfiscal. */
	private Lfclfiscal lfclfiscal;

	/**
	 * Instantiates a new inquiry lfclfiscal request.
	 */
	public InquiryLfclfiscalRequest()
	{
	}

	/**
	 * Instantiates a new inquiry lfclfiscal request.
	 * 
	 * @param userContext the user context
	 */
	public InquiryLfclfiscalRequest(UserContext userContext)
	{
		super(userContext);
	}

	/**
	 * Sets the lfclfiscal.
	 * 
	 * @param lfclfiscalObject the new lfclfiscal
	 */
	public void setLfclfiscal(Lfclfiscal lfclfiscalObject)
	{
		lfclfiscal = lfclfiscalObject;
	}

	/**
	 * Gets the lfclfiscal.
	 * 
	 * @return the lfclfiscal
	 */
	public Lfclfiscal getLfclfiscal()
	{
		return lfclfiscal;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.base.model.request.LightingControlInquiryRequest#toString()
	 */
	@Override
	public String toString()
	{
		return "InquiryLfclfiscalRequest [getLfclfiscal()=" + getLfclfiscal() + ", getStartRow()=" + getStartRow() + ", getEndRow()="
				+ getEndRow() + ", getPageSize()=" + getPageSize() + ", getStartPage()=" + getStartPage()
				+ ", getSortExpressions()=" + getSortExpressions() + ", getSortExpression()=" + getSortExpression()
				+ ", isPreQueryCount()=" + isPreQueryCount() + ", getMaxPreQueryCount()=" + getMaxPreQueryCount()
				+ ", getTenant()=" + getTenant() + ", getUserContext()=" + getUserContext() + "]";
	}
}

