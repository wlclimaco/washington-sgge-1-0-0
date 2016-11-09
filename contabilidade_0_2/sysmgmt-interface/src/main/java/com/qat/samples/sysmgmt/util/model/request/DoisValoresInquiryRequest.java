package com.qat.samples.sysmgmt.util.model.request;

/**
 * The Class ProdutoInquiryRequest.
 */
public class DoisValoresInquiryRequest extends PagedInquiryRequest
{

	/** The criteria. */
	private Integer paginaId;
	
	private Integer doisValorType;



	public DoisValoresInquiryRequest() {

	}

	public Integer getPaginaId() {
		return paginaId;
	}

	public void setPaginaId(Integer paginaId) {
		this.paginaId = paginaId;
	}

	public Integer getDoisValorType() {
		return doisValorType;
	}

	public void setDoisValorType(Integer doisValorType) {
		this.doisValorType = doisValorType;
	}

	@Override
	public String toString() {
		return "DoisValoresInquiryRequest [getPaginaId()=" + getPaginaId() + ", getDoisValorType()="
				+ getDoisValorType() + ", toString()=" + super.toString() + "]";
	}


}
