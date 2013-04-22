package com.sensus.mlc.contabil.model.request;

import com.sensus.common.model.UserContext;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.lfseqserie.model.Lfseqserie;

/**
 * The Class InquiryLfseqserieRequest.
 *
 * @author - Washinton
 */
public class InquiryLfseqserieRequest extends InquiryPaginationRequest
{

	/** The lfseqserie. */
	private Lfseqserie lfseqserie;

	/**
	 * Instantiates a new inquiry lfseqserie request.
	 */
	public InquiryLfseqserieRequest()
	{
	}

	/**
	 * Instantiates a new inquiry lfseqserie request.
	 * 
	 * @param userContext the user context
	 */
	public InquiryLfseqserieRequest(UserContext userContext)
	{
		super(userContext);
	}

	/**
	 * Sets the lfseqserie.
	 * 
	 * @param lfseqserieObject the new lfseqserie
	 */
	public void setLfseqserie(Lfseqserie lfseqserieObject)
	{
		lfseqserie = lfseqserieObject;
	}

	/**
	 * Gets the lfseqserie.
	 * 
	 * @return the lfseqserie
	 */
	public Lfseqserie getLfseqserie()
	{
		return lfseqserie;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.base.model.request.LightingControlInquiryRequest#toString()
	 */
	@Override
	public String toString()
	{
		return "InquiryLfseqserieRequest [getLfseqserie()=" + getLfseqserie() + ", getStartRow()=" + getStartRow() + ", getEndRow()="
				+ getEndRow() + ", getPageSize()=" + getPageSize() + ", getStartPage()=" + getStartPage()
				+ ", getSortExpressions()=" + getSortExpressions() + ", getSortExpression()=" + getSortExpression()
				+ ", isPreQueryCount()=" + isPreQueryCount() + ", getMaxPreQueryCount()=" + getMaxPreQueryCount()
				+ ", getTenant()=" + getTenant() + ", getUserContext()=" + getUserContext() + "]";
	}
}

