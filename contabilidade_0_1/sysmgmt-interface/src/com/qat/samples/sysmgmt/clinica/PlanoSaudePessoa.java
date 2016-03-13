package com.qat.samples.sysmgmt.clinica;

import com.qat.samples.sysmgmt.util.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class PlanoSaudePessoa extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;

	/** The type of an account. */
	private PlanoSaude planoId;

	private Integer pacienteId;

	private Integer dataValidade;

	public PlanoSaudePessoa()
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

	public PlanoSaude getPlanoId()
	{
		return planoId;
	}

	public void setPlanoId(PlanoSaude planoId)
	{
		this.planoId = planoId;
	}

	public Integer getPacienteId()
	{
		return pacienteId;
	}

	public void setPacienteId(Integer pacienteId)
	{
		this.pacienteId = pacienteId;
	}

	public Integer getDataValidade()
	{
		return dataValidade;
	}

	public void setDataValidade(Integer dataValidade)
	{
		this.dataValidade = dataValidade;
	}

	@Override
	public String toString()
	{
		return "PlanoSaudePessoa [getId()=" + getId() + ", getPlanoId()=" + getPlanoId() + ", getPacienteId()="
				+ getPacienteId() + ", getDataValidade()=" + getDataValidade() + ", toString()=" + super.toString()
				+ "]";
	}

}
