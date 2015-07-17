package com.prosperitasglobal.sendsolv.model;

import java.util.List;

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

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Funcionario [getId()=" + getId() + ", getMatricula()="
				+ getMatricula() + ", getDataAdm()=" + getDataAdm() + ", getSalarios()=" + getSalarios()
				+ ", getHorarios()=" + getHorarios() + ", getBeneficios()=" + getBeneficios() + ", getNome()="
				+ getNome() + ", getNomePai()=" + getNomePai() + ", getNomeMae()=" + getNomeMae()
				+ ", getNomeConjugue()=" + getNomeConjugue() + ", getEstadoCivil()=" + getEstadoCivil()
				+ ", getDatanasc()=" + getDatanasc() + ", getFoto()=" + getFoto() + ", getSexo()=" + getSexo()
				+ ", getEnderecos()=" + getEnderecos() + ", getDocumentos()=" + getDocumentos() + ", getEmails()="
				+ getEmails() + ", getTelefones()=" + getTelefones() + ", getNotes()=" + getNotes() + ", getBancos()="
				+ getBancos() + ", toString()=" + super.toString() + ", getParentId()=" + getParentId()
				+ ", getType()=" + getType() + ", getAcaoType()=" + getAcaoType() + ", getTabelaEnum()="
				+ getTabelaEnum() + ", getStatusList()=" + getStatusList() + ", getModelAction()=" + getModelAction()
				+ ", getCreateUser()=" + getCreateUser() + ", getCreateDateUTC()=" + getCreateDateUTC()
				+ ", getModifyUser()=" + getModifyUser() + ", getModifyDateUTC()=" + getModifyDateUTC()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}

}
