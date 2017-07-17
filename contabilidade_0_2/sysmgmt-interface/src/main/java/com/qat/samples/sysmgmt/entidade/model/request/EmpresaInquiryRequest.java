package com.qat.samples.sysmgmt.entidade.model.request;

import com.qat.samples.sysmgmt.entidade.model.EmpresaTypeEnum;
import com.qat.samples.sysmgmt.util.model.PermissaoTypeEnum;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;

/**
 * The Class ProdutoInquiryRequest.
 */
public class EmpresaInquiryRequest extends PagedInquiryRequest
{

	/** The criteria. */
	private Integer emprId;

	private Integer parceiroId;

	private Integer contabilidadeId;

	public EmpresaInquiryRequest()
	{
		super();
	}

	public Integer getEmprId() {
		return emprId;
	}

	public void setEmprId(Integer emprId) {
		this.emprId = emprId;
	}

	public Integer getParceiroId() {
		return parceiroId;
	}

	public void setParceiroId(Integer parceiroId) {
		this.parceiroId = parceiroId;
	}

	public Integer getContabilidadeId() {
		return contabilidadeId;
	}

	public void setContabilidadeId(Integer contabilidadeId) {
		this.contabilidadeId = contabilidadeId;
	}

	@Override
	public String toString() {
		return "EmpresaInquiryRequest [getPermissaoTypeEnumValue()=" + getPermissaoTypeEnumValue() + ", getEmprId()="
				+ getEmprId() + ", getPermissaoTypeEnum()=" + getPermissaoTypeEnum() + ", getParceiroId()="
				+ getParceiroId() + ", getContabilidadeId()=" + getContabilidadeId() + ", toString()="
				+ super.toString() + "]";
	}


}
