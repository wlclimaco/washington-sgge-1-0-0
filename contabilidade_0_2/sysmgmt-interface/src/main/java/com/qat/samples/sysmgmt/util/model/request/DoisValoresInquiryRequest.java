package com.qat.samples.sysmgmt.util.model.request;

/**
 * The Class ProdutoInquiryRequest.
 */
public class DoisValoresInquiryRequest extends PagedInquiryRequest
{

	/** The criteria. */
	private Integer paginaId;



	public DoisValoresInquiryRequest() {

	}

	public Integer getPaginaId() {
		return paginaId;
	}

	public void setPaginaId(Integer paginaId) {
		this.paginaId = paginaId;
	}

	@Override
	public String toString() {
		return "DoisValoresInquiryRequest [getPaginaId()=" + getPaginaId() + ", toString()=" + super.toString() + "]";
	}


}
