package com.qat.samples.sysmgmt.clinica;

import java.util.List;

import com.qat.samples.sysmgmt.pessoa.Medico;
import com.qat.samples.sysmgmt.pessoa.Paciente;
import com.qat.samples.sysmgmt.util.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class ProcessoStatus extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;

	/** The type of an account. */
	private Integer dataProcesso;

	private Note note;
	
	private ProcessTypeEnum statusProcess;

	public ProcessoStatus()
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

	public Integer getDataProcessoStatus()
	{
		return dataProcessoStatus;
	}

	public void setDataProcessoStatus(Integer dataProcessoStatus)
	{
		this.dataProcessoStatus = dataProcessoStatus;
	}

	public Integer getDataMarcacao()
	{
		return dataMarcacao;
	}

	public void setDataMarcacao(Integer dataMarcacao)
	{
		this.dataMarcacao = dataMarcacao;
	}

	public Medico getMedico()
	{
		return medico;
	}

	public void setMedico(Medico medico)
	{
		this.medico = medico;
	}

	public Paciente getPaciente()
	{
		return paciente;
	}

	public void setPaciente(Paciente paciente)
	{
		this.paciente = paciente;
	}

	public List<ExamePessoa> getExameList()
	{
		return exameList;
	}

	public void setExameList(List<ExamePessoa> exameList)
	{
		this.exameList = exameList;
	}

	public List<Resultado> getResultadoList()
	{
		return resultadoList;
	}

	public void setResultadoList(List<Resultado> resultadoList)
	{
		this.resultadoList = resultadoList;
	}

	public PlanoSaudePessoa getPlanoSaude()
	{
		return planoSaude;
	}

	public void setPlanoSaude(PlanoSaudePessoa planoSaude)
	{
		this.planoSaude = planoSaude;
	}

	public Double getValor()
	{
		return valor;
	}

	public void setValor(Double valor)
	{
		this.valor = valor;
	}

	@Override
	public String toString()
	{
		return "ProcessoStatus [getId()=" + getId() + ", getDataProcessoStatus()=" + getDataProcessoStatus() + ", getDataMarcacao()="
				+ getDataMarcacao() + ", getMedico()=" + getMedico() + ", getPaciente()=" + getPaciente()
				+ ", getExameList()=" + getExameList() + ", getResultadoList()=" + getResultadoList()
				+ ", getPlanoSaude()=" + getPlanoSaude() + ", getValor()=" + getValor() + ", toString()="
				+ super.toString() + "]";
	}

}
