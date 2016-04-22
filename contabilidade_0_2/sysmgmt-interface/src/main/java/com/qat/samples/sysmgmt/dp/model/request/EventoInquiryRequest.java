package com.qat.samples.sysmgmt.dp.model.request;

import com.qat.samples.sysmgmt.dp.model.criteria.EventoCriteria;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;

/**
 * The Class ProdutoInquiryRequest.
 */
public class EventoInquiryRequest extends PagedInquiryRequest
{

	/** The criteria. */
	private EventoCriteria criteria;

	/**
	 * Gets the criteria.
	 *
	 * @return the criteria
	 */
	public EventoCriteria getCriteria()
	{
		if (criteria == null)
		{
			criteria = new EventoCriteria();
		}
		return criteria;
	}

	/**
	 * Sets the criteria.
	 *
	 * @param criteria the criteria
	 */
	public void setCriteria(EventoCriteria criteria)
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
		return "ArquivoInquiryRequest [getCriteria()=" + getCriteria() + ", toString()=" + super.toString() + "]";
	}

}
