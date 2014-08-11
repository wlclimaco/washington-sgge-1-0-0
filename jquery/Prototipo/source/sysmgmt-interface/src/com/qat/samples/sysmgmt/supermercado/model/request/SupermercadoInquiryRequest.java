package com.qat.samples.sysmgmt.supermercado.model.request;

import com.qat.framework.model.request.InquiryRequest;
import com.qat.samples.sysmgmt.util.Criteria;

/**
 * The Model Object PagedInquiryRequest.
 */
public class SupermercadoInquiryRequest extends InquiryRequest
{
	public Criteria criteria;

	public Criteria getCriteria()
	{
		return criteria;
	}

	public void setCriteria(Criteria criteria)
	{
		this.criteria = criteria;
	}

	public SupermercadoInquiryRequest()
	{

	}

	@Override
	public String toString()
	{
		return "SupermercadoInquiryRequest [getCriteria()=" + getCriteria() + ", toString()=" + super.toString() + "]";
	}

}
