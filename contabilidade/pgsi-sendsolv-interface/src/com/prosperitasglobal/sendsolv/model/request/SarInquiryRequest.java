package com.prosperitasglobal.sendsolv.model.request;

import com.prosperitasglobal.sendsolv.model.criteria.SarCriteria;

public class SarInquiryRequest extends PagedInquiryRequest
{
	private SarCriteria criteria;

	public SarCriteria getCriteria()
	{
		return criteria;
	}

	public void setCriteria(SarCriteria criteria)
	{
		this.criteria = criteria;
	}

	@Override
	public String toString()
	{
		return "SarInquiryRequest [getCriteria()=" + getCriteria() + ", toString()=" + super.toString() + "]";
	}

}
