package com.qat.samples.sysmgmt.estado.model.request;

import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;

/**
 * The Class ProdutoInquiryRequest.
 */
public class EstadoInquiryRequest extends PagedInquiryRequest
{

	/** The criteria. */
	private Integer estadoId;

	public Integer getEstadoId() {
		return estadoId;
	}

	public void setEstadoId(Integer estadoId) {
		this.estadoId = estadoId;
	}

	@Override
	public String toString() {
		return "EstadoInquiryRequest [getEstadoId()=" + getEstadoId() + ", toString()=" + super.toString() + "]";
	}

}
