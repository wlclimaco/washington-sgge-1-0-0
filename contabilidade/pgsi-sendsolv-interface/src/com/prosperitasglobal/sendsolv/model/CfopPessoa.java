package com.prosperitasglobal.sendsolv.model;


/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class CfopPessoa extends ModelCosmeDamiao
{

	private Integer id;

	/** The description. */
	private Cfop idCfop;

	/**
	 * Default constructor.
	 */
	public CfopPessoa()
	{
		super();
	}

	public CfopPessoa(Integer id, PersistanceActionEnum mode)
	{
		super();
		this.id = id;
		setModelAction(mode);
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
	 * @return the idCfop
	 */
	public Cfop getIdCfop()
	{
		return idCfop;
	}

	/**
	 * @param idCfop the idCfop to set
	 */
	public void setIdCfop(Cfop idCfop)
	{
		this.idCfop = idCfop;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "CfopPessoa [getId()=" + getId() + ", getIdCfop()=" + getIdCfop() + ", getTabelaEnumValue()="
				+ getTabelaEnumValue() + ", getTypeValue()=" + getTypeValue() + ", getAcaoEnumValue()="
				+ getAcaoEnumValue() + ", getParentId()=" + getParentId() + ", getType()=" + getType()
				+ ", getAcaoType()=" + getAcaoType() + ", getTabelaEnum()=" + getTabelaEnum() + ", getStatusList()="
				+ getStatusList() + ", getEmprId()=" + getEmprId() + ", getSite()=" + getSite() + ", getProcessId()="
				+ getProcessId() + ", getUserId()=" + getUserId() + ", toString()=" + super.toString()
				+ ", getModelAction()=" + getModelAction() + ", getCreateUser()=" + getCreateUser()
				+ ", getCreateDateUTC()=" + getCreateDateUTC() + ", getModifyUser()=" + getModifyUser()
				+ ", getModifyDateUTC()=" + getModifyDateUTC() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + "]";
	}

}
