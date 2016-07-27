package com.qat.samples.sysmgmt.fiscal.model;

import com.qat.samples.sysmgmt.produto.model.Incidencia;
import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Icms extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;

	private Integer produtoId;

	private String situacaoTributaria;
	private Integer origem;
	private Integer modalidadeBC;
	private Double reducaoBase;
	private Double aliqICMS;
	private Integer motivoDesonerecao;



	/**
	 * Default constructor.
	 */
	public Icms()
	{
		super();
	}

	public Icms(int i, String string) {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProdutoId() {
		return produtoId;
	}

	public void setProdutoId(Integer produtoId) {
		this.produtoId = produtoId;
	}

	public String getSituacaoTributaria() {
		return situacaoTributaria;
	}

	public void setSituacaoTributaria(String situacaoTributaria) {
		this.situacaoTributaria = situacaoTributaria;
	}

	public Integer getOrigem() {
		return origem;
	}

	public void setOrigem(Integer origem) {
		this.origem = origem;
	}

	public Integer getModalidadeBC() {
		return modalidadeBC;
	}

	public void setModalidadeBC(Integer modalidadeBC) {
		this.modalidadeBC = modalidadeBC;
	}

	public Double getReducaoBase() {
		return reducaoBase;
	}

	public void setReducaoBase(Double reducaoBase) {
		this.reducaoBase = reducaoBase;
	}

	public Double getAliqICMS() {
		return aliqICMS;
	}

	public void setAliqICMS(Double aliqICMS) {
		this.aliqICMS = aliqICMS;
	}

	public Integer getMotivoDesonerecao() {
		return motivoDesonerecao;
	}

	public void setMotivoDesonerecao(Integer motivoDesonerecao) {
		this.motivoDesonerecao = motivoDesonerecao;
	}

	@Override
	public String toString() {
		return "Icms [getId()=" + getId() + ", getProdutoId()=" + getProdutoId() + ", getSituacaoTributaria()="
				+ getSituacaoTributaria() + ", getOrigem()=" + getOrigem() + ", getModalidadeBC()=" + getModalidadeBC()
				+ ", getReducaoBase()=" + getReducaoBase() + ", getAliqICMS()=" + getAliqICMS()
				+ ", getMotivoDesonerecao()=" + getMotivoDesonerecao() + ", toString()=" + super.toString() + "]";
	}

}
