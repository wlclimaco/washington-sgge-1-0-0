package com.qat.samples.sysmgmt.entidade.model.request;

import com.qat.samples.sysmgmt.entidade.model.EmpresaTypeEnum;
import com.qat.samples.sysmgmt.entidade.model.criteria.EmpresaCriteria;
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
	
	
	private EmpresaTypeEnum permissaoTypeEnum;

	public EmpresaInquiryRequest()
	{
		super();
	}
	
	public Integer getPermissaoTypeEnumValue()
	{
		if (permissaoTypeEnum != null)
		{
			return permissaoTypeEnum.getValue();
		}
		return null;
	}

	public void setPermissaoTypeEnumValue(Integer acaoTypeValue)
	{
		permissaoTypeEnum = EmpresaTypeEnum.enumForValue(acaoTypeValue);
	}

	public Integer getEmprId() {
		return emprId;
	}

	public void setEmprId(Integer emprId) {
		this.emprId = emprId;
	}

	public EmpresaTypeEnum getPermissaoTypeEnum() {
		return permissaoTypeEnum;
	}

	public void setPermissaoTypeEnum(EmpresaTypeEnum permissaoTypeEnum) {
		this.permissaoTypeEnum = permissaoTypeEnum;
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
