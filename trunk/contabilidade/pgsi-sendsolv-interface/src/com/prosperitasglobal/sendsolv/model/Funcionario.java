package com.prosperitasglobal.sendsolv.model;

import java.util.List;

import com.qat.framework.model.QATModel;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Funcionario extends QATModel
{
	/** The SendSolv id for the account. */
	private Integer id;

	private Integer cdEmpr;

	/** The type of an account. */
	private String nome;

	/** The matricula. */
	private String matricula;

	/** The datanasc. */
	private Integer datanasc;

	/** The data adm. */
	private Integer dataAdm;

	/** The sexo. */
	private Integer sexo;

	/** The enderecos. */
	private List<Endereco> enderecos;

	/** The documentos. */
	private List<Documento> documentos;

	/** The emails. */
	private List<Email> emails;

	/** The Telefones. */
	private List<Telefone> Telefones;

	/** The salarios. */
	private List<Salario> salarios;

	/** The horarios. */
	private List<HorarioFunc> horarios;

	/** The beneficios. */
	private List<Beneficios> beneficios;

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
	public Integer getId()
	{
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the id to set
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * Gets the nome.
	 *
	 * @return the nome
	 */
	public String getNome()
	{
		return nome;
	}

	/**
	 * Sets the nome.
	 *
	 * @param nome the nome to set
	 */
	public void setNome(String nome)
	{
		this.nome = nome;
	}

	/**
	 * Gets the enderecos.
	 *
	 * @return the enderecos
	 */
	public List<Endereco> getEnderecos()
	{
		return enderecos;
	}

	/**
	 * Sets the enderecos.
	 *
	 * @param enderecos the enderecos to set
	 */
	public void setEnderecos(List<Endereco> enderecos)
	{
		this.enderecos = enderecos;
	}

	/**
	 * Gets the documentos.
	 *
	 * @return the documentos
	 */
	public List<Documento> getDocumentos()
	{
		return documentos;
	}

	/**
	 * Sets the documentos.
	 *
	 * @param documentos the documentos to set
	 */
	public void setDocumentos(List<Documento> documentos)
	{
		this.documentos = documentos;
	}

	/**
	 * Gets the emails.
	 *
	 * @return the emails
	 */
	public List<Email> getEmails()
	{
		return emails;
	}

	/**
	 * Sets the emails.
	 *
	 * @param emails the emails to set
	 */
	public void setEmails(List<Email> emails)
	{
		this.emails = emails;
	}

	/**
	 * Gets the telefones.
	 *
	 * @return the telefones
	 */
	public List<Telefone> getTelefones()
	{
		return Telefones;
	}

	/**
	 * Sets the telefones.
	 *
	 * @param telefones the telefones to set
	 */
	public void setTelefones(List<Telefone> telefones)
	{
		Telefones = telefones;
	}

	/**
	 * Gets the matricula.
	 *
	 * @return the matricula
	 */
	public String getMatricula()
	{
		return matricula;
	}

	/**
	 * Sets the matricula.
	 *
	 * @param matricula the matricula to set
	 */
	public void setMatricula(String matricula)
	{
		this.matricula = matricula;
	}

	/**
	 * Gets the datanasc.
	 *
	 * @return the datanasc
	 */
	public Integer getDatanasc()
	{
		return datanasc;
	}

	/**
	 * Sets the datanasc.
	 *
	 * @param datanasc the datanasc to set
	 */
	public void setDatanasc(Integer datanasc)
	{
		this.datanasc = datanasc;
	}

	/**
	 * Gets the data adm.
	 *
	 * @return the dataAdm
	 */
	public Integer getDataAdm()
	{
		return dataAdm;
	}

	/**
	 * Sets the data adm.
	 *
	 * @param dataAdm the dataAdm to set
	 */
	public void setDataAdm(Integer dataAdm)
	{
		this.dataAdm = dataAdm;
	}

	/**
	 * Gets the sexo.
	 *
	 * @return the sexo
	 */
	public Integer getSexo()
	{
		return sexo;
	}

	/**
	 * Sets the sexo.
	 *
	 * @param sexo the sexo to set
	 */
	public void setSexo(Integer sexo)
	{
		this.sexo = sexo;
	}

	/**
	 * Gets the salarios.
	 *
	 * @return the salarios
	 */
	public List<Salario> getSalarios()
	{
		return salarios;
	}

	/**
	 * Sets the salarios.
	 *
	 * @param salarios the salarios to set
	 */
	public void setSalarios(List<Salario> salarios)
	{
		this.salarios = salarios;
	}

	/**
	 * Gets the horarios.
	 *
	 * @return the horarios
	 */
	public List<HorarioFunc> getHorarios()
	{
		return horarios;
	}

	/**
	 * Sets the horarios.
	 *
	 * @param horarios the horarios to set
	 */
	public void setHorarios(List<HorarioFunc> horarios)
	{
		this.horarios = horarios;
	}

	/**
	 * Gets the beneficios.
	 *
	 * @return the beneficios
	 */
	public List<Beneficios> getBeneficios()
	{
		return beneficios;
	}

	/**
	 * Sets the beneficios.
	 *
	 * @param beneficios the beneficios to set
	 */
	public void setBeneficios(List<Beneficios> beneficios)
	{
		this.beneficios = beneficios;
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

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Funcionario [getId()=" + getId() + ", getNome()=" + getNome() + ", getEnderecos()=" + getEnderecos()
				+ ", getDocumentos()=" + getDocumentos() + ", getEmails()=" + getEmails() + ", getTelefones()="
				+ getTelefones() + ", getMatricula()=" + getMatricula() + ", getDatanasc()=" + getDatanasc()
				+ ", getDataAdm()=" + getDataAdm() + ", getSexo()=" + getSexo() + ", getSalarios()=" + getSalarios()
				+ ", getHorarios()=" + getHorarios() + ", getBeneficios()=" + getBeneficios() + ", getCdEmpr()="
				+ getCdEmpr() + ", toString()=" + super.toString() + "]";
	}

}
