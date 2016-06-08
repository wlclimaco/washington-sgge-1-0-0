package com.qat.samples.sysmgmt.pessoa.model.request;

import com.qat.samples.sysmgmt.pessoa.model.criteria.ClienteCriteria;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;

/**
 * The Class ProdutoInquiryRequest.
 */
public class SocioInquiryRequest extends PagedInquiryRequest
{

	/** The criteria. */
	private Integer emprId;

	public SocioInquiryRequest() {
		super();
	}

	public Integer getEmprId() {
		return emprId;
	}

	public void setEmprId(Integer emprId) {
		this.emprId = emprId;
	}

	@Override
	public String toString() {
		return "SocioInquiryRequest [getEmprId()=" + getEmprId() + ", toString()=" + super.toString() + "]";
	}



}
