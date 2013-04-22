package com.sensus.mlc.tabela.model.request;

import com.sensus.common.model.UserContext;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.chaveestrangeira.model.Chaveestrangeira;

/**
 * The Class InquiryChaveestrangeiraRequest.
 *
 * @author - Washinton
 */
public class InquiryChaveestrangeiraRequest extends InquiryPaginationRequest
{

	/** The chaveestrangeira. */
	private Chaveestrangeira chaveestrangeira;

	/**
	 * Instantiates a new inquiry chaveestrangeira request.
	 */
	public InquiryChaveestrangeiraRequest()
	{
	}

	/**
	 * Instantiates a new inquiry chaveestrangeira request.
	 * 
	 * @param userContext the user context
	 */
	public InquiryChaveestrangeiraRequest(UserContext userContext)
	{
		super(userContext);
	}

	/**
	 * Sets the chaveestrangeira.
	 * 
	 * @param chaveestrangeiraObject the new chaveestrangeira
	 */
	public void setChaveestrangeira(Chaveestrangeira chaveestrangeiraObject)
	{
		chaveestrangeira = chaveestrangeiraObject;
	}

	/**
	 * Gets the chaveestrangeira.
	 * 
	 * @return the chaveestrangeira
	 */
	public Chaveestrangeira getChaveestrangeira()
	{
		return chaveestrangeira;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.base.model.request.LightingControlInquiryRequest#toString()
	 */
	@Override
	public String toString()
	{
		return "InquiryChaveestrangeiraRequest [getChaveestrangeira()=" + getChaveestrangeira() + ", getStartRow()=" + getStartRow() + ", getEndRow()="
				+ getEndRow() + ", getPageSize()=" + getPageSize() + ", getStartPage()=" + getStartPage()
				+ ", getSortExpressions()=" + getSortExpressions() + ", getSortExpression()=" + getSortExpression()
				+ ", isPreQueryCount()=" + isPreQueryCount() + ", getMaxPreQueryCount()=" + getMaxPreQueryCount()
				+ ", getTenant()=" + getTenant() + ", getUserContext()=" + getUserContext() + "]";
	}
}

