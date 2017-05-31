package com.qat.samples.sysmgmt.produto.model;

import com.qat.samples.sysmgmt.cfop.model.Cfop;
import com.qat.samples.sysmgmt.nfeItens.model.NFImpostoDevolvido;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImposto;
import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.).
 * This represents an account for a transfer setting.
 */
@SuppressWarnings("serial")
public class Tributacao extends ModelCosmeDamiao {
	/** The SendSolv id for the account. */
	private Integer id;

	private String descricao;

	private Integer prodId;

	private Integer tipo;

	private Cfop cfop;

	/** The econtabil imposto for the NFNotaInfoItem. */
	private NFNotaInfoItemImposto imposto;

	/** The econtabil impostoDevolvido for the NFNotaInfoItem. */
	private NFImpostoDevolvido impostoDevolvido;

	/**
	 * Default constructor.
	 */
	public Tributacao() {
		super();
	}

	public Tributacao(Integer id) {
		super();
		this.id = id;
	}

	public Tributacao(int i, String string) {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProdId() {
		return prodId;
	}

	public void setProdId(Integer prodId) {
		this.prodId = prodId;
	}

	public Cfop getCfop() {
		return cfop;
	}

	public void setCfop(Cfop cfop) {
		this.cfop = cfop;
	}

	public NFNotaInfoItemImposto getImposto() {
		return imposto;
	}

	public void setImposto(NFNotaInfoItemImposto imposto) {
		this.imposto = imposto;
	}

	public NFImpostoDevolvido getImpostoDevolvido() {
		return impostoDevolvido;
	}

	public void setImpostoDevolvido(NFImpostoDevolvido impostoDevolvido) {
		this.impostoDevolvido = impostoDevolvido;
	}


	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "Tributacao [getId()=" + getId() + ", getProdId()=" + getProdId() + ", getCfop()=" + getCfop()
				+ ", getImposto()=" + getImposto() + ", getImpostoDevolvido()=" + getImpostoDevolvido()
				+ ", getDescricao()=" + getDescricao() + ", getTipo()=" + getTipo() + ", toString()=" + super.toString()
				+ "]";
	}



}
