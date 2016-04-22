package com.qat.samples.sysmgmt.pessoa.model;

import java.util.List;

import com.qat.samples.sysmgmt.clinica.model.Consulta;
import com.qat.samples.sysmgmt.clinica.model.PlanoSaudePessoa;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Paciente extends Pessoa
{

	private List<PlanoSaudePessoa> planoSaudeList;
	private List<Consulta> consultaList;

	/**
	 * Default constructor.
	 */
	public Paciente()
	{
		super();
	}

	public Paciente(Integer id)
	{
		super();
		setId(id);
	}

	public Paciente(int i, String string) {
		// TODO Auto-generated constructor stub
	}

	public List<PlanoSaudePessoa> getPlanoSaudeList()
	{
		return planoSaudeList;
	}

	public void setPlanoSaudeList(List<PlanoSaudePessoa> planoSaudeList)
	{
		this.planoSaudeList = planoSaudeList;
	}

	public List<Consulta> getConsultaList()
	{
		return consultaList;
	}

	public void setConsultaList(List<Consulta> consultaList)
	{
		this.consultaList = consultaList;
	}

	@Override
	public String toString()
	{
		return "Paciente [getPlanoSaudeList()=" + getPlanoSaudeList() + ", getConsultaList()=" + getConsultaList()
				+ ", toString()=" + super.toString() + "]";
	}

}
