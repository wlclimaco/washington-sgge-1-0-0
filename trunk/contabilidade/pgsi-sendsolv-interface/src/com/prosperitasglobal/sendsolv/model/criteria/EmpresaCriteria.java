package com.prosperitasglobal.sendsolv.model.criteria;

import java.io.Serializable;
import java.util.List;

import com.prosperitasglobal.sendsolv.model.CdStatusTypeEnum;

/**
 * The Class MemberCriteria.
 */
@SuppressWarnings("serial")
public class EmpresaCriteria implements Serializable
{

	/** The member. */
	private String nome;

	/** The business id. */
	private Integer emprId;

	/** The recipient id. */
	private Integer id;

	/** The location name. */
	private String cidade;

	private Integer entidadeEnum;

	/** The organization name. */
	private List<CdStatusTypeEnum> status;

	/** The primary phone number. */
	private String regime;

	/**
	 * The Constructor.
	 */
	public EmpresaCriteria()
	{
		super();
	}

	/**
	 * @return the nome
	 */
	public String getNome()
	{
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome)
	{
		this.nome = nome;
	}

	/**
	 * @return the emprId
	 */
	public Integer getEmprId()
	{
		return emprId;
	}

	/**
	 * @param emprId the emprId to set
	 */
	public void setEmprId(Integer emprId)
	{
		this.emprId = emprId;
	}

	/**
	 * @return the cidade
	 */
	public String getCidade()
	{
		return cidade;
	}

	/**
	 * @param cidade the cidade to set
	 */
	public void setCidade(String cidade)
	{
		this.cidade = cidade;
	}

	/**
	 * @return the status
	 */
	public List<CdStatusTypeEnum> getStatus()
	{
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(List<CdStatusTypeEnum> status)
	{
		this.status = status;
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

	/**
	 * @return the regime
	 */
	public String getRegime()
	{
		return regime;
	}

	/**
	 * @param regime the regime to set
	 */
	public void setRegime(String regime)
	{
		this.regime = regime;
	}

	/**
	 * @return the entidadeEnum
	 */
	public Integer getEntidadeEnum()
	{
		return entidadeEnum;
	}

	/**
	 * @param entidadeEnum the entidadeEnum to set
	 */
	public void setEntidadeEnum(Integer entidadeEnum)
	{
		this.entidadeEnum = entidadeEnum;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "EmprIdCriteria [getNome()=" + getNome()
				+ ", getEmprId()=" + getEmprId() + ", getCidade()=" + getCidade() + ", getStatus()=" + getStatus()
				+ ", getId()=" + getId() + ", getRegime()=" + getRegime() + ", getEntidadeEnum()=" + getEntidadeEnum()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}

}
