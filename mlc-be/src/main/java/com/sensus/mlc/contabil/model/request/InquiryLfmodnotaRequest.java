package com.sensus.mlc.contabil.model.request;

import com.sensus.common.model.UserContext;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.lfmodnota.model.Lfmodnota;

/**
 * The Class InquiryLfmodnotaRequest.
 *
 * @author - Washinton
 */
public class InquiryLfmodnotaRequest extends InquiryPaginationRequest
{

	/** The lfmodnota. */
	private Lfmodnota lfmodnota;

	/**
	 * Instantiates a new inquiry lfmodnota request.
	 */
	public InquiryLfmodnotaRequest()
	{
	}

	/**
	 * Instantiates a new inquiry lfmodnota request.
	 * 
	 * @param userContext the user context
	 */
	public InquiryLfmodnotaRequest(UserContext userContext)
	{
		super(userContext);
	}

	/**
	 * Sets the lfmodnota.
	 * 
	 * @param lfmodnotaObject the new lfmodnota
	 */
	public void setLfmodnota(Lfmodnota lfmodnotaObject)
	{
		lfmodnota = lfmodnotaObject;
	}

	/**
	 * Gets the lfmodnota.
	 * 
	 * @return the lfmodnota
	 */
	public Lfmodnota getLfmodnota()
	{
		return lfmodnota;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.base.model.request.LightingControlInquiryRequest#toString()
	 */
	@Override
	public String toString()
	{
		return "InquiryLfmodnotaRequest [getLfmodnota()=" + getLfmodnota() + ", getStartRow()=" + getStartRow() + ", getEndRow()="
				+ getEndRow() + ", getPageSize()=" + getPageSize() + ", getStartPage()=" + getStartPage()
				+ ", getSortExpressions()=" + getSortExpressions() + ", getSortExpression()=" + getSortExpression()
				+ ", isPreQueryCount()=" + isPreQueryCount() + ", getMaxPreQueryCount()=" + getMaxPreQueryCount()
				+ ", getTenant()=" + getTenant() + ", getUserContext()=" + getUserContext() + "]";
	}
}

