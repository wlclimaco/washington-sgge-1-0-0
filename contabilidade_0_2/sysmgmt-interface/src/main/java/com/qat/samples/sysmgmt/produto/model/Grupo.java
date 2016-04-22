package com.qat.samples.sysmgmt.produto.model;

import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Grupo extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;

	/** The type of an account. */
	private String grupo;

	/** The description. */
	private String descricao;

	/** The estado. */
	private SubGrupo subGrupo;

	/**
	 * Default constructor.
	 */
	public Grupo()
	{
		super();
	}

	public Grupo(Integer id)
	{
		super();
		this.id = id;
	}

	public Grupo(int i, String string) {
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
	 * @param id the new id
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * @return the grupo
	 */
	public String getGrupo()
	{
		return grupo;
	}

	/**
	 * @param grupo the grupo to set
	 */
	public void setGrupo(String grupo)
	{
		this.grupo = grupo;
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

	/**
	 * @return the subGrupo
	 */
	public SubGrupo getSubGrupo()
	{
		return subGrupo;
	}

	/**
	 * @param subGrupo the subGrupo to set
	 */
	public void setSubGrupo(SubGrupo subGrupo)
	{
		this.subGrupo = subGrupo;
	}

	@Override
	public String toString()
	{
		return "Grupo [getId()=" + getId() + ", getGrupo()=" + getGrupo() + ", getDescricao()=" + getDescricao()
				+ ", getSubGrupo()=" + getSubGrupo() + ", toString()=" + super.toString() + "]";
	}

}
