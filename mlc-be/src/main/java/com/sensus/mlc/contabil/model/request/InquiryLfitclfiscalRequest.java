package com.sensus.mlc.contabil.model.request;

import com.sensus.common.model.UserContext;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.lfitclfiscal.model.Lfitclfiscal;

/**
 * The Class InquiryLfitclfiscalRequest.
 *
 * @author - Washinton
 */
public class InquiryLfitclfiscalRequest extends InquiryPaginationRequest
{

	/** The lfitclfiscal. */
	private Lfitclfiscal lfitclfiscal;

	/**
	 * Instantiates a new inquiry lfitclfiscal request.
	 */
	public InquiryLfitclfiscalRequest()
	{
	}

	/**
	 * Instantiates a new inquiry lfitclfiscal request.
	 * 
	 * @param userContext the user context
	 */
	public InquiryLfitclfiscalRequest(UserContext userContext)
	{
		super(userContext);
	}

	/**
	 * Sets the lfitclfiscal.
	 * 
	 * @param lfitclfiscalObject the new lfitclfiscal
	 */
	public void setLfitclfiscal(Lfitclfiscal lfitclfiscalObject)
	{
		lfitclfiscal = lfitclfiscalObject;
	}

	/**
	 * Gets the lfitclfiscal.
	 * 
	 * @return the lfitclfiscal
	 */
	public Lfitclfiscal getLfitclfiscal()
	{
		return lfitclfiscal;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.base.model.request.LightingControlInquiryRequest#toString()
	 */
	@Override
	public String toString()
	{
		return "InquiryLfitclfiscalRequest [getLfitclfiscal()=" + getLfitclfiscal() + ", getStartRow()=" + getStartRow() + ", getEndRow()="
				+ getEndRow() + ", getPageSize()=" + getPageSize() + ", getStartPage()=" + getStartPage()
				+ ", getSortExpressions()=" + getSortExpressions() + ", getSortExpression()=" + getSortExpression()
				+ ", isPreQueryCount()=" + isPreQueryCount() + ", getMaxPreQueryCount()=" + getMaxPreQueryCount()
				+ ", getTenant()=" + getTenant() + ", getUserContext()=" + getUserContext() + "]";
	}
}

