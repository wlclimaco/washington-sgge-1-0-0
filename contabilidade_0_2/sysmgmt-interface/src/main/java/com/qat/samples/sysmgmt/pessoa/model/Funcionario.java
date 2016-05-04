package com.qat.samples.sysmgmt.pessoa.model;

import java.util.Date;
import java.util.List;

import com.qat.samples.sysmgmt.beneficios.model.BeneficioPessoa;
import com.qat.samples.sysmgmt.dp.model.EventoPessoa;
import com.qat.samples.sysmgmt.dp.model.HorarioFunc;
import com.qat.samples.sysmgmt.dp.model.Salario;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Funcionario extends Pessoa
{

	/** The matricula. */
	private String matricula;

	/** The data adm. */
	private Long dataAdm;

	/** The salarios. */
	private List<Salario> salarios;

	/** The horarios. */
	private List<HorarioFunc> horarios;

	/** The beneficios. */
	private List<BeneficioPessoa> beneficios;

	private List<EventoPessoa> eventosList;

	/**
	 * Default constructor.
	 */
	public Funcionario()
	{
		super();
	}

	public Funcionario(int i, String string) {
		setId(i);
		setNome(string);
		setModelAction(PersistenceActionEnum.INSERT);
		setModifyDateUTC((new Date()).getTime());
		setModifyUser("system");
	}

	/**
	 * @return the matricula
	 */
	public String getMatricula()
	{
		return matricula;
	}

	/**
	 * @param matricula the matricula to set
	 */
	public void setMatricula(String matricula)
	{
		this.matricula = matricula;
	}

	/**
	 * @return the salarios
	 */
	public List<Salario> getSalarios()
	{
		return salarios;
	}

	/**
	 * @param salarios the salarios to set
	 */
	public void setSalarios(List<Salario> salarios)
	{
		this.salarios = salarios;
	}

	/**
	 * @return the horarios
	 */
	public List<HorarioFunc> getHorarios()
	{
		return horarios;
	}

	/**
	 * @param horarios the horarios to set
	 */
	public void setHorarios(List<HorarioFunc> horarios)
	{
		this.horarios = horarios;
	}

	/**
	 * @return the dataAdm
	 */
	public Long getDataAdm()
	{
		return dataAdm;
	}

	/**
	 * @param dataAdm the dataAdm to set
	 */
	public void setDataAdm(Long dataAdm)
	{
		this.dataAdm = dataAdm;
	}

	/**
	 * @return the beneficios
	 */
	public List<BeneficioPessoa> getBeneficios()
	{
		return beneficios;
	}

	/**
	 * @param beneficios the beneficios to set
	 */
	public void setBeneficios(List<BeneficioPessoa> beneficios)
	{
		this.beneficios = beneficios;
	}

	/**
	 * @return the eventosList
	 */
	public List<EventoPessoa> getEventosList()
	{
		return eventosList;
	}

	/**
	 * @param eventosList the eventosList to set
	 */
	public void setEventosList(List<EventoPessoa> eventosList)
	{
		this.eventosList = eventosList;
	}

	@Override
	public String toString()
	{
		return "Funcionario [getMatricula()=" + getMatricula() + ", getSalarios()=" + getSalarios()
				+ ", getHorarios()=" + getHorarios() + ", getDataAdm()=" + getDataAdm() + ", getBeneficios()="
				+ getBeneficios() + ", getEventosList()=" + getEventosList() + ", toString()=" + super.toString() + "]";
	}

}
