package com.qat.samples.sysmgmt.produto.model;

import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class PorcaoItem extends ModelCosmeDamiao
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
	public PorcaoItem()
	{
		super();
	}

	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * Sets the id.
	 * 
	 * @param id the new id
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * @return the nome
	 */
	public String getNome()
	{
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome)
	{
		this.nome = nome;
	}

	/**
	 * @return the porcao
	 */
	public Double getPorcao()
	{
		return porcao;
	}

	/**
	 * @param porcao the porcao to set
	 */
	public void setPorcao(Double porcao)
	{
		this.porcao = porcao;
	}

	/**
	 * @return the vd
	 */
	public Double getVd()
	{
		return vd;
	}

	/**
	 * @param vd the vd to set
	 */
	public void setVd(Double vd)
	{
		this.vd = vd;
	}

	/**
	 * @return the unimed
	 */
	public UniMed getUnimed()
	{
		return unimed;
	}

	/**
	 * @param unimed the unimed to set
	 */
	public void setUnimed(UniMed unimed)
	{
		this.unimed = unimed;
	}

	@Override
	public String toString()
	{
		return "PorcaoItem [getId()=" + getId() + ", getNome()=" + getNome() + ", getPorcao()=" + getPorcao()
				+ ", getVd()=" + getVd() + ", getUnimed()=" + getUnimed() + ", toString()=" + super.toString() + "]";
	}

}
