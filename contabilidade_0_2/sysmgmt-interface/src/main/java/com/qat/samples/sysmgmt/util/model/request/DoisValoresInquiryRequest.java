package com.qat.samples.sysmgmt.util.model.request;

/**
 * The Class ProdutoInquiryRequest.
 */
public class DoisValoresInquiryRequest extends PagedInquiryRequest
{

	/** The criteria. */
	private Integer paginaId;

	private Integer doisValorType;

	private String nome;

	private String descricao;

	private String value;



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


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "DoisValoresInquiryRequest [getPaginaId()=" + getPaginaId() + ", getDoisValorType()="
				+ getDoisValorType() + ", getNome()=" + getNome() + ", getDescricao()=" + getDescricao()
				+ ", getValue()=" + getValue() + ", toString()=" + super.toString() + "]";
	}


}
