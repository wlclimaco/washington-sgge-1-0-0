package com.qat.samples.sysmgmt.pessoa.model.request;

import com.qat.samples.sysmgmt.dp.model.criteria.ConvenioCriteria;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;

/**
 * The Class ProdutoInquiryRequest.
 */
public class ConvenioInquiryRequest extends PagedInquiryRequest
{

	/** The criteria. */
	private ConvenioCriteria criteria;

	/**
	 * Gets the criteria.
	 *
	 * @return the criteria
	 */
	public ConvenioCriteria getCriteria()
	{
		if (criteria == null)
		{
			criteria = new ConvenioCriteria();
		}
		return criteria;
	}

	/**
	 * Sets the criteria.
	 *
	 * @param criteria the criteria
	 */
	public void setCriteria(ConvenioCriteria criteria)
	{
		this.criteria = criteria;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "ConvenioInquiryRequest [getCriteria()=" + getCriteria() + ", toString()=" + super.toString() + "]";
	}

}
