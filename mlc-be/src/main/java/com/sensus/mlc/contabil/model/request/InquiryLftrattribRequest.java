package com.sensus.mlc.contabil.model.request;

import com.sensus.common.model.UserContext;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.lftrattrib.model.Lftrattrib;

/**
 * The Class InquiryLftrattribRequest.
 *
 * @author - Washinton
 */
public class InquiryLftrattribRequest extends InquiryPaginationRequest
{

	/** The lftrattrib. */
	private Lftrattrib lftrattrib;

	/**
	 * Instantiates a new inquiry lftrattrib request.
	 */
	public InquiryLftrattribRequest()
	{
	}

	/**
	 * Instantiates a new inquiry lftrattrib request.
	 * 
	 * @param userContext the user context
	 */
	public InquiryLftrattribRequest(UserContext userContext)
	{
		super(userContext);
	}

	/**
	 * Sets the lftrattrib.
	 * 
	 * @param lftrattribObject the new lftrattrib
	 */
	public void setLftrattrib(Lftrattrib lftrattribObject)
	{
		lftrattrib = lftrattribObject;
	}

	/**
	 * Gets the lftrattrib.
	 * 
	 * @return the lftrattrib
	 */
	public Lftrattrib getLftrattrib()
	{
		return lftrattrib;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.base.model.request.LightingControlInquiryRequest#toString()
	 */
	@Override
	public String toString()
	{
		return "InquiryLftrattribRequest [getLftrattrib()=" + getLftrattrib() + ", getStartRow()=" + getStartRow() + ", getEndRow()="
				+ getEndRow() + ", getPageSize()=" + getPageSize() + ", getStartPage()=" + getStartPage()
				+ ", getSortExpressions()=" + getSortExpressions() + ", getSortExpression()=" + getSortExpression()
				+ ", isPreQueryCount()=" + isPreQueryCount() + ", getMaxPreQueryCount()=" + getMaxPreQueryCount()
				+ ", getTenant()=" + getTenant() + ", getUserContext()=" + getUserContext() + "]";
	}
}

