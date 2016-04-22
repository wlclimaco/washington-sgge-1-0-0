package com.qat.samples.sysmgmt.dp.model.request;

import com.qat.samples.sysmgmt.dp.model.criteria.HoraFuncCriteria;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;

/**
 * The Class ProdutoInquiryRequest.
 */
public class HoraFuncInquiryRequest extends PagedInquiryRequest
{

	/** The criteria. */
	private HoraFuncCriteria criteria;

	/**
	 * Gets the criteria.
	 *
	 * @return the criteria
	 */
	public HoraFuncCriteria getCriteria()
	{
		if (criteria == null)
		{
			criteria = new HoraFuncCriteria();
		}
		return criteria;
	}

	/**
	 * Sets the criteria.
	 *
	 * @param criteria the criteria
	 */
	public void setCriteria(HoraFuncCriteria criteria)
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
