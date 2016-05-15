package com.qat.samples.sysmgmt.produto.model;

import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class PorcaoItens extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;

	/** The type of an account. */
	private String nome;

	private Double porcao;

	private Double vd;

	private UniMed unimed;

	/**
	 * Default constructor.
	 */
	public PorcaoItens()
	{
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getPorcao() {
		return porcao;
	}

	public void setPorcao(Double porcao) {
		this.porcao = porcao;
	}

	public Double getVd() {
		return vd;
	}

	public void setVd(Double vd) {
		this.vd = vd;
	}

	public UniMed getUnimed() {
		return unimed;
	}

	public void setUnimed(UniMed unimed) {
		this.unimed = unimed;
	}

	@Override
	public String toString() {
		return "PorcaoItens [getId()=" + getId() + ", getNome()=" + getNome() + ", getPorcao()=" + getPorcao()
				+ ", getVd()=" + getVd() + ", getUnimed()=" + getUnimed() + ", toString()=" + super.toString() + "]";
	}



}
