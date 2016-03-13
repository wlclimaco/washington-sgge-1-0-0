package com.qat.samples.sysmgmt.clinica;

import com.qat.samples.sysmgmt.util.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class EspecialidadePessoa extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;

	/** The type of an account. */
	private Integer idFunc;

	private Especialidade especialidade;

	public EspecialidadePessoa()
	{

	}

	/**
	 * @return the id
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getIdFunc()
	{
		return idFunc;
	}

	public void setIdFunc(Integer idFunc)
	{
		this.idFunc = idFunc;
	}

	public Especialidade getEspecialidade()
	{
		return especialidade;
	}

	public void setEspecialidade(Especialidade especialidade)
	{
		this.especialidade = especialidade;
	}

	@Override
	public String toString()
	{
		return "EspecialidadePessoa [getId()=" + getId() + ", getIdFunc()=" + getIdFunc() + ", getEspecialidade()="
				+ getEspecialidade() + ", toString()=" + super.toString() + "]";
	}

}
