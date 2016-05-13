package com.qat.samples.sysmgmt.util.model.request;

import com.qat.framework.model.request.InquiryRequest;
import com.qat.samples.sysmgmt.util.model.criteria.ComumCriteria;

/**
 * The Model Object PagedInquiryRequest.
 */
public class PagedInquiryRequest extends InquiryRequest
{

	private ComumCriteria baseCriteria;

	public PagedInquiryRequest()
	{


	}



	public ComumCriteria getBaseCriteria() {
		return baseCriteria;
	}



	public void setBaseCriteria(ComumCriteria baseCriteria) {
		this.baseCriteria = baseCriteria;
	}



	@Override
	public String toString() {
		return "PagedInquiryRequest [getBaseCriteria()=" + getBaseCriteria() + ", toString()=" + super.toString() + "]";
	}

}
