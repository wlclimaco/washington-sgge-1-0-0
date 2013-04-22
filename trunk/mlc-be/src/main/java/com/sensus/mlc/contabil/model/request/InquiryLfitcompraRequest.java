package com.sensus.mlc.contabil.model.request;

import com.sensus.common.model.UserContext;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.lfitcompra.model.Lfitcompra;

/**
 * The Class InquiryLfitcompraRequest.
 *
 * @author - Washinton
 */
public class InquiryLfitcompraRequest extends InquiryPaginationRequest
{

	/** The lfitcompra. */
	private Lfitcompra lfitcompra;

	/**
	 * Instantiates a new inquiry lfitcompra request.
	 */
	public InquiryLfitcompraRequest()
	{
	}

	/**
	 * Instantiates a new inquiry lfitcompra request.
	 * 
	 * @param userContext the user context
	 */
	public InquiryLfitcompraRequest(UserContext userContext)
	{
		super(userContext);
	}

	/**
	 * Sets the lfitcompra.
	 * 
	 * @param lfitcompraObject the new lfitcompra
	 */
	public void setLfitcompra(Lfitcompra lfitcompraObject)
	{
		lfitcompra = lfitcompraObject;
	}

	/**
	 * Gets the lfitcompra.
	 * 
	 * @return the lfitcompra
	 */
	public Lfitcompra getLfitcompra()
	{
		return lfitcompra;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.base.model.request.LightingControlInquiryRequest#toString()
	 */
	@Override
	public String toString()
	{
		return "InquiryLfitcompraRequest [getLfitcompra()=" + getLfitcompra() + ", getStartRow()=" + getStartRow() + ", getEndRow()="
				+ getEndRow() + ", getPageSize()=" + getPageSize() + ", getStartPage()=" + getStartPage()
				+ ", getSortExpressions()=" + getSortExpressions() + ", getSortExpression()=" + getSortExpression()
				+ ", isPreQueryCount()=" + isPreQueryCount() + ", getMaxPreQueryCount()=" + getMaxPreQueryCount()
				+ ", getTenant()=" + getTenant() + ", getUserContext()=" + getUserContext() + "]";
	}
}

