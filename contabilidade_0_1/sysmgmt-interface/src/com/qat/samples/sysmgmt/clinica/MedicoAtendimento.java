package com.qat.samples.sysmgmt.clinica;

import java.util.List;

import com.qat.samples.sysmgmt.dp.HorarioFunc;
import com.qat.samples.sysmgmt.pessoa.Medico;
import com.qat.samples.sysmgmt.util.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class MedicoAtendimento extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;

	/** The type of an account. */
	private Medico medico;

	private List<HorarioFunc> horarios;

	private List<DiaSemana> dias;

	public MedicoAtendimento()
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

	public Medico getMedico()
	{
		return medico;
	}

	public void setMedico(Medico medico)
	{
		this.medico = medico;
	}

	public List<HorarioFunc> getHorarios()
	{
		return horarios;
	}

	public void setHorarios(List<HorarioFunc> horarios)
	{
		this.horarios = horarios;
	}

	public List<DiaSemana> getDias()
	{
		return dias;
	}

	public void setDias(List<DiaSemana> dias)
	{
		this.dias = dias;
	}

	@Override
	public String toString()
	{
		return "MedicoAtendimento [getId()=" + getId() + ", getMedico()=" + getMedico() + ", toString()="
				+ super.toString() + "]";
	}

}
