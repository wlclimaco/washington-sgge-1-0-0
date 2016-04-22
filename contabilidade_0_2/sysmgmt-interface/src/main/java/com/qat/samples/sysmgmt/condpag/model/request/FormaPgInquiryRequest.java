package com.qat.samples.sysmgmt.condpag.model.request;

import com.qat.samples.sysmgmt.condpag.model.criteria.FormaPgCriteria;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;

/**
 * The Class ProdutoInquiryRequest.
 */
public class FormaPgInquiryRequest extends PagedInquiryRequest
{

	/** The criteria. */
	private FormaPgCriteria criteria;

	/**
	 * Gets the criteria.
	 *
	 * @return the criteria
	 */
	public FormaPgCriteria getCriteria()
	{
		if (criteria == null)
		{
			criteria = new FormaPgCriteria();
		}
		return criteria;
	}

	/**
	 * Sets the criteria.
	 *
	 * @param criteria the criteria
	 */
	public void setCriteria(FormaPgCriteria criteria)
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
		return "FormaPgInquiryRequest [getCriteria()=" + getCriteria() + ", toString()=" + super.toString() + "]";
	}

}
