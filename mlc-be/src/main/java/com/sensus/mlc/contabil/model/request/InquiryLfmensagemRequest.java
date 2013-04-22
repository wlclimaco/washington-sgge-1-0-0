package com.sensus.mlc.contabil.model.request;

import com.sensus.common.model.UserContext;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.lfmensagem.model.Lfmensagem;

/**
 * The Class InquiryLfmensagemRequest.
 *
 * @author - Washinton
 */
public class InquiryLfmensagemRequest extends InquiryPaginationRequest
{

	/** The lfmensagem. */
	private Lfmensagem lfmensagem;

	/**
	 * Instantiates a new inquiry lfmensagem request.
	 */
	public InquiryLfmensagemRequest()
	{
	}

	/**
	 * Instantiates a new inquiry lfmensagem request.
	 * 
	 * @param userContext the user context
	 */
	public InquiryLfmensagemRequest(UserContext userContext)
	{
		super(userContext);
	}

	/**
	 * Sets the lfmensagem.
	 * 
	 * @param lfmensagemObject the new lfmensagem
	 */
	public void setLfmensagem(Lfmensagem lfmensagemObject)
	{
		lfmensagem = lfmensagemObject;
	}

	/**
	 * Gets the lfmensagem.
	 * 
	 * @return the lfmensagem
	 */
	public Lfmensagem getLfmensagem()
	{
		return lfmensagem;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.base.model.request.LightingControlInquiryRequest#toString()
	 */
	@Override
	public String toString()
	{
		return "InquiryLfmensagemRequest [getLfmensagem()=" + getLfmensagem() + ", getStartRow()=" + getStartRow() + ", getEndRow()="
				+ getEndRow() + ", getPageSize()=" + getPageSize() + ", getStartPage()=" + getStartPage()
				+ ", getSortExpressions()=" + getSortExpressions() + ", getSortExpression()=" + getSortExpression()
				+ ", isPreQueryCount()=" + isPreQueryCount() + ", getMaxPreQueryCount()=" + getMaxPreQueryCount()
				+ ", getTenant()=" + getTenant() + ", getUserContext()=" + getUserContext() + "]";
	}
}

