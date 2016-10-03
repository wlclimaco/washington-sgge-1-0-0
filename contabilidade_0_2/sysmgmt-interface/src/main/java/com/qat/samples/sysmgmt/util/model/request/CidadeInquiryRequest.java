package com.qat.samples.sysmgmt.util.model.request;

/**
 * The Class ProdutoInquiryRequest.
 */
public class CidadeInquiryRequest extends PagedInquiryRequest
{

	/** The criteria. */
	private Integer estadoId;

	public CidadeInquiryRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getEstadoId() {
		return estadoId;
	}

	public void setEstadoId(Integer estadoId) {
		this.estadoId = estadoId;
	}

	@Override
	public String toString() {
		return "CidadeInquiryRequest [getEstadoId()=" + getEstadoId() + ", toString()=" + super.toString() + "]";
	}


}
