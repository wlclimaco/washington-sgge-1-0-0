package com.qat.samples.sysmgmt.clinica.model;

import java.util.List;

import com.qat.samples.sysmgmt.pessoa.model.Medico;
import com.qat.samples.sysmgmt.pessoa.model.Paciente;
import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Consulta extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;

	/** The type of an account. */
	private Long dataConsulta;

	private Double valor;

	private Long dataMarcacao;

	private Medico medico;

	private Paciente paciente;

	private PlanoSaudePessoa planoSaude;

	private List<ExamePessoa> exameList;

	private List<Resultado> resultadoList;



	public Consulta()
	{

	}

	public Consulta(int i, String string) {
		// TODO Auto-generated constructor stub
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

	public Long getDataConsulta()
	{
		return dataConsulta;
	}

	public void setDataConsulta(Long dataConsulta)
	{
		this.dataConsulta = dataConsulta;
	}

	public Long getDataMarcacao()
	{
		return dataMarcacao;
	}

	public void setDataMarcacao(Long dataMarcacao)
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
		return "Consulta [getId()=" + getId() + ", getDataConsulta()=" + getDataConsulta() + ", getDataMarcacao()="
				+ getDataMarcacao() + ", getMedico()=" + getMedico() + ", getPaciente()=" + getPaciente()
				+ ", getExameList()=" + getExameList() + ", getResultadoList()=" + getResultadoList()
				+ ", getPlanoSaude()=" + getPlanoSaude() + ", getValor()=" + getValor() + ", toString()="
				+ super.toString() + "]";
	}

}
