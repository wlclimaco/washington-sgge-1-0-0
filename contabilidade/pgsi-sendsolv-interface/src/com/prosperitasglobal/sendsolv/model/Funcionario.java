package com.prosperitasglobal.sendsolv.model;

import java.util.List;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Funcionario extends Pessoa
{
	/** The SendSolv id for the account. */
	private Integer id;

	private Integer cdEmpr;

	/** The matricula. */
	private String matricula;

	/** The data adm. */
	private Long dataAdm;

	/** The salarios. */
	private List<Salario> salarios;

	/** The horarios. */
	private List<HorarioFunc> horarios;

	/** The beneficios. */
	private List<Beneficios> beneficios;

	private List<Eventos> eventosList;

	/**
	 * Default constructor.
	 */
	public Funcionario()
	{
		super();
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	@Override
	public Integer getId()
	{
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the id to set
	 */
	@Override
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * @return the cdEmpr
	 */
	public Integer getCdEmpr()
	{
		return cdEmpr;
	}

	/**
	 * @param cdEmpr the cdEmpr to set
	 */
	public void setCdEmpr(Integer cdEmpr)
	{
		this.cdEmpr = cdEmpr;
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
	 * @return the beneficios
	 */
	public List<Beneficios> getBeneficios()
	{
		return beneficios;
	}

	/**
	 * @param beneficios the beneficios to set
	 */
	public void setBeneficios(List<Beneficios> beneficios)
	{
		this.beneficios = beneficios;
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
	 * @return the eventosList
	 */
	public List<Eventos> getEventosList()
	{
		return eventosList;
	}

	/**
	 * @param eventosList the eventosList to set
	 */
	public void setEventosList(List<Eventos> eventosList)
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
		return "Funcionario [getId()=" + getId() + ", getCdEmpr()=" + getCdEmpr() + ", getMatricula()="
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
