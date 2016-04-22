package com.qat.samples.sysmgmt.produto.model;

import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class SubGrupo extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;

	/** The type of an account. */
	private String subGrupo;

	/** The description. */
	private String descricao;

	/**
	 * Default constructor.
	 */
	public SubGrupo()
	{
		super();
	}

	public SubGrupo(Integer id)
	{
		super();
		this.id = id;
	}

	public SubGrupo(int i, String string) {
		// TODO Auto-generated constructor stub
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
	 * @param id the id to set
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * @return the subGrupo
	 */
	public String getSubGrupo()
	{
		return subGrupo;
	}

	/**
	 * @param subGrupo the subGrupo to set
	 */
	public void setSubGrupo(String subGrupo)
	{
		this.subGrupo = subGrupo;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao()
	{
		return descricao;
	}

	/**
	 * @param descricao the descricao to set
	 */
	public void setDescricao(String descricao)
	{
		this.descricao = descricao;
	}

	@Override
	public String toString()
	{
		return "SubGrupo [getId()=" + getId() + ", getSubGrupo()=" + getSubGrupo() + ", getDescricao()="
				+ getDescricao() + ", toString()=" + super.toString() + "]";
	}

}
