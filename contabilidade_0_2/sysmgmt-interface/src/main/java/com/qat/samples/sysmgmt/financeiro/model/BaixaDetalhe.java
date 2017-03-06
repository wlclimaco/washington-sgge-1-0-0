package com.qat.samples.sysmgmt.financeiro.model;

import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting. c.id, c.descricao,c.datavencimento,c.docid,c.valor as "valor titulo",(select d.descricao from doisvalor d where id = c.tipodoc ) as "Tipo Doc" ,
 * (select d.descricao from doisvalor d where id = c.situacao ) as "Situacao" ,b.databaixa , b.valor as "Valor Baixa"
 */
@SuppressWarnings("serial")
public class BaixaDetalhe extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;
	private String descricao;
	private Long dataVencimento;
	private Long dataBaixa;
	private String docid;
	private Double valorTitulo;
	private Double valorBaixa;
	private String tipoDoc;
	private String situacao;


	/**
	 * Default constructor.
	 */
	public BaixaDetalhe()
	{
		super();
	}

	public Integer getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Long dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Long getDataBaixa() {
		return dataBaixa;
	}

	public void setDataBaixa(Long dataBaixa) {
		this.dataBaixa = dataBaixa;
	}

	public String getDocid() {
		return docid;
	}

	public void setDocid(String docid) {
		this.docid = docid;
	}

	public Double getValorTitulo() {
		return valorTitulo;
	}

	public void setValorTitulo(Double valorTitulo) {
		this.valorTitulo = valorTitulo;
	}

	public Double getValorBaixa() {
		return valorBaixa;
	}

	public void setValorBaixa(Double valorBaixa) {
		this.valorBaixa = valorBaixa;
	}

	public String getTipoDoc() {
		return tipoDoc;
	}

	public void setTipoDoc(String tipoDoc) {
		this.tipoDoc = tipoDoc;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "BaixaDetalhe [getId()=" + getId() + ", getDescricao()=" + getDescricao() + ", getDataVencimento()="
				+ getDataVencimento() + ", getDataBaixa()=" + getDataBaixa() + ", getDocid()=" + getDocid()
				+ ", getValorTitulo()=" + getValorTitulo() + ", getValorBaixa()=" + getValorBaixa() + ", getTipoDoc()="
				+ getTipoDoc() + ", getSituacao()=" + getSituacao() + ", toString()=" + super.toString() + "]";
	}

}
