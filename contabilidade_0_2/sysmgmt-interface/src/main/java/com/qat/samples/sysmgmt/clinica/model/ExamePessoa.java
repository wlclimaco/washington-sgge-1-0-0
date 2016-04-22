package com.qat.samples.sysmgmt.clinica.model;

import com.qat.samples.sysmgmt.pessoa.model.Paciente;
import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class ExamePessoa extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;

	private Integer dataExame;

	/** The type of an account. */
	private Exame exame;

	private Paciente paciente;

	private Resultado resultado;

	public ExamePessoa()
	{

	}

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getDataExame()
	{
		return dataExame;
	}

	public void setDataExame(Integer dataExame)
	{
		this.dataExame = dataExame;
	}

	public Exame getExame()
	{
		return exame;
	}

	public void setExame(Exame exame)
	{
		this.exame = exame;
	}

	public Paciente getPaciente()
	{
		return paciente;
	}

	public void setPaciente(Paciente paciente)
	{
		this.paciente = paciente;
	}

	public Resultado getResultado()
	{
		return resultado;
	}

	public void setResultado(Resultado resultado)
	{
		this.resultado = resultado;
	}

	@Override
	public String toString()
	{
		return "ExamePessoa [getId()=" + getId() + ", getDataExame()=" + getDataExame() + ", getExame()=" + getExame()
				+ ", getPaciente()=" + getPaciente() + ", getResultado()=" + getResultado() + ", toString()="
				+ super.toString() + "]";
	}

}
