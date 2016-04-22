package com.qat.samples.sysmgmt.entidade.model.request;

import com.qat.samples.sysmgmt.entidade.model.criteria.FilialCriteria;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;

/**
 * The Class ProdutoInquiryRequest.
 */
public class FilialInquiryRequest extends PagedInquiryRequest
{

	/** The criteria. */
	private FilialCriteria criteria;

	/**
	 * Gets the criteria.
	 * 
	 * @return the criteria
	 */
	public FilialCriteria getCriteria()
	{
		if (criteria == null)
		{
			criteria = new FilialCriteria();
		}
		return criteria;
	}

	/**
	 * Sets the criteria.
	 * 
	 * @param criteria the criteria
	 */
	public void setCriteria(FilialCriteria criteria)
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
		return "FilialInquiryRequest [getCriteria()=" + getCriteria() + ", toString()=" + super.toString() + "]";
	}

}
