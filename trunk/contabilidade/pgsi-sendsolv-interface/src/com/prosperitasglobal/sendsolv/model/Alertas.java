package com.prosperitasglobal.sendsolv.model;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Alertas extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;

	private Integer entidadeId;

	/** The type of an account. */
	private Integer qntOs;

	private Integer qntEmpr;

	private Integer qntProd;

	private Integer qntSugs;

	private Integer qntRecl;

	private Integer qntFaleCom;

	/**
	 * Default constructor.
	 */
	public Alertas()
	{
		super();
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
	 * @return the entidadeId
	 */
	public Integer getEntidadeId()
	{
		return entidadeId;
	}

	/**
	 * @param entidadeId the entidadeId to set
	 */
	public void setEntidadeId(Integer entidadeId)
	{
		this.entidadeId = entidadeId;
	}

	/**
	 * @return the qntOs
	 */
	public Integer getQntOs()
	{
		return qntOs;
	}

	/**
	 * @param qntOs the qntOs to set
	 */
	public void setQntOs(Integer qntOs)
	{
		this.qntOs = qntOs;
	}

	/**
	 * @return the qntEmpr
	 */
	public Integer getQntEmpr()
	{
		return qntEmpr;
	}

	/**
	 * @param qntEmpr the qntEmpr to set
	 */
	public void setQntEmpr(Integer qntEmpr)
	{
		this.qntEmpr = qntEmpr;
	}

	/**
	 * @return the qntProd
	 */
	public Integer getQntProd()
	{
		return qntProd;
	}

	/**
	 * @param qntProd the qntProd to set
	 */
	public void setQntProd(Integer qntProd)
	{
		this.qntProd = qntProd;
	}

	/**
	 * @return the qntSugs
	 */
	public Integer getQntSugs()
	{
		return qntSugs;
	}

	/**
	 * @param qntSugs the qntSugs to set
	 */
	public void setQntSugs(Integer qntSugs)
	{
		this.qntSugs = qntSugs;
	}

	/**
	 * @return the qntRecl
	 */
	public Integer getQntRecl()
	{
		return qntRecl;
	}

	/**
	 * @param qntRecl the qntRecl to set
	 */
	public void setQntRecl(Integer qntRecl)
	{
		this.qntRecl = qntRecl;
	}

	/**
	 * @return the qntFaleCom
	 */
	public Integer getQntFaleCom()
	{
		return qntFaleCom;
	}

	/**
	 * @param qntFaleCom the qntFaleCom to set
	 */
	public void setQntFaleCom(Integer qntFaleCom)
	{
		this.qntFaleCom = qntFaleCom;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Alertas [getId()=" + getId() + ", getEntidadeId()=" + getEntidadeId() + ", getQntOs()=" + getQntOs()
				+ ", getQntEmpr()=" + getQntEmpr() + ", getQntProd()=" + getQntProd() + ", getQntSugs()="
				+ getQntSugs() + ", getQntRecl()=" + getQntRecl() + ", getQntFaleCom()=" + getQntFaleCom()
				+ ", getTabelaEnumValue()=" + getTabelaEnumValue() + ", getTypeValue()=" + getTypeValue()
				+ ", getAcaoEnumValue()=" + getAcaoEnumValue() + ", getParentId()=" + getParentId() + ", getType()="
				+ getType() + ", getAcaoType()=" + getAcaoType() + ", getTabelaEnum()=" + getTabelaEnum()
				+ ", getStatusList()=" + getStatusList() + ", getEmprId()=" + getEmprId() + ", getSite()=" + getSite()
				+ ", getProcessId()=" + getProcessId() + ", getUserId()=" + getUserId() + ", getNotes()=" + getNotes()
				+ ", toString()=" + super.toString() + ", getModelAction()=" + getModelAction() + ", getCreateUser()="
				+ getCreateUser() + ", getCreateDateUTC()=" + getCreateDateUTC() + ", getModifyUser()="
				+ getModifyUser() + ", getModifyDateUTC()=" + getModifyDateUTC() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + "]";
	}

}
