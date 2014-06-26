package com.qat.samples.sysmgmt.cidade.model.request;

import com.qat.framework.model.request.InquiryRequest;
import com.qat.samples.sysmgmt.cidade.model.Cidade;

/**
 * The Model Object PagedInquiryRequest.
 */
public class CidadeInquiryRequest extends InquiryRequest
{

	private Cidade cidade;

	public CidadeInquiryRequest()
	{

	}

	public Cidade getCidade()
	{
		return cidade;
	}

	public void setCidade(Cidade cidade)
	{
		this.cidade = cidade;
	}

	public CidadeInquiryRequest(Cidade cidade)
	{
		super();
		this.cidade = cidade;
	}

	@Override
	public String toString()
	{
		return "CidadeInquiryRequest [getCidade()=" + getCidade() + ", toString()=" + super.toString() + "]";
	}

}
