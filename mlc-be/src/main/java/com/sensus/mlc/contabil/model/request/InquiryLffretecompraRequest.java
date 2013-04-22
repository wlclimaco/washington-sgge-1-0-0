package com.sensus.mlc.contabil.model.request;

import com.sensus.common.model.UserContext;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.lffretecompra.model.Lffretecompra;

/**
 * The Class InquiryLffretecompraRequest.
 *
 * @author - Washinton
 */
public class InquiryLffretecompraRequest extends InquiryPaginationRequest
{

	/** The lffretecompra. */
	private Lffretecompra lffretecompra;

	/**
	 * Instantiates a new inquiry lffretecompra request.
	 */
	public InquiryLffretecompraRequest()
	{
	}

	/**
	 * Instantiates a new inquiry lffretecompra request.
	 * 
	 * @param userContext the user context
	 */
	public InquiryLffretecompraRequest(UserContext userContext)
	{
		super(userContext);
	}

	/**
	 * Sets the lffretecompra.
	 * 
	 * @param lffretecompraObject the new lffretecompra
	 */
	public void setLffretecompra(Lffretecompra lffretecompraObject)
	{
		lffretecompra = lffretecompraObject;
	}

	/**
	 * Gets the lffretecompra.
	 * 
	 * @return the lffretecompra
	 */
	public Lffretecompra getLffretecompra()
	{
		return lffretecompra;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.base.model.request.LightingControlInquiryRequest#toString()
	 */
	@Override
	public String toString()
	{
		return "InquiryLffretecompraRequest [getLffretecompra()=" + getLffretecompra() + ", getStartRow()=" + getStartRow() + ", getEndRow()="
				+ getEndRow() + ", getPageSize()=" + getPageSize() + ", getStartPage()=" + getStartPage()
				+ ", getSortExpressions()=" + getSortExpressions() + ", getSortExpression()=" + getSortExpression()
				+ ", isPreQueryCount()=" + isPreQueryCount() + ", getMaxPreQueryCount()=" + getMaxPreQueryCount()
				+ ", getTenant()=" + getTenant() + ", getUserContext()=" + getUserContext() + "]";
	}
}

