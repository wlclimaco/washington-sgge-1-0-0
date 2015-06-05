package com.prosperitasglobal.sendsolv.model;

/**
 * The Class Organization represents a main {@link Business} that is signed up to use PGSi services. It owns one or more
 * {@link Location}
 */
@SuppressWarnings("serial")
public class Profissao extends ModelCosmeDamiao
{
	/** The number of locations this organization contains. */
	private Integer id;

	/** The flag that indicates if the payroll is centralized. */
	private String profissao;

	/** The dba name. */
	private Double renda;

	/** The list of documents this organization contains. */
	private Long dataAdmissao;

	/**
	 * The Constructor.
	 */
	public Profissao()
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

	/**
	 * @return the profissao
	 */
	public String getProfissao()
	{
		return profissao;
	}

	/**
	 * @param profissao the profissao to set
	 */
	public void setProfissao(String profissao)
	{
		this.profissao = profissao;
	}

	/**
	 * @return the renda
	 */
	public Double getRenda()
	{
		return renda;
	}

	/**
	 * @param renda the renda to set
	 */
	public void setRenda(Double renda)
	{
		this.renda = renda;
	}

	/**
	 * @return the dataAdmissao
	 */
	public Long getDataAdmissao()
	{
		return dataAdmissao;
	}

	/**
	 * @param dataAdmissao the dataAdmissao to set
	 */
	public void setDataAdmissao(Long dataAdmissao)
	{
		this.dataAdmissao = dataAdmissao;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Profissao [getId()=" + getId() + ", getProfissao()=" + getProfissao() + ", getRenda()=" + getRenda()
				+ ", getDataAdmissao()=" + getDataAdmissao() + ", getParentId()=" + getParentId() + ", getType()="
				+ getType() + ", getAcaoType()=" + getAcaoType() + ", getTabelaEnum()=" + getTabelaEnum()
				+ ", getStatusList()=" + getStatusList() + ", toString()=" + super.toString() + ", getModelAction()="
				+ getModelAction() + ", getCreateUser()=" + getCreateUser() + ", getCreateDateUTC()="
				+ getCreateDateUTC() + ", getModifyUser()=" + getModifyUser() + ", getModifyDateUTC()="
				+ getModifyDateUTC() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}

}