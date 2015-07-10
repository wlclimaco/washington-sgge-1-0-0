package com.prosperitasglobal.sendsolv.model;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class HistoricoItens extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;

	/** The bairro. */
	private Integer idHist;

	/** The numero. */
	private Integer processId;

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * @return the idHist
	 */
	public Integer getIdHist()
	{
		return idHist;
	}

	/**
	 * @param idHist the idHist to set
	 */
	public void setIdHist(Integer idHist)
	{
		this.idHist = idHist;
	}

	/**
	 * @return the processId
	 */
	@Override
	public Integer getProcessId()
	{
		return processId;
	}

	/**
	 * @param processId the processId to set
	 */
	@Override
	public void setProcessId(Integer processId)
	{
		this.processId = processId;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "HistoricoItens [getId()=" + getId() + ", getIdHist()=" + getIdHist() + ", getProcessId()="
				+ getProcessId() + ", getTabelaEnumValue()=" + getTabelaEnumValue() + ", getTypeValue()="
				+ getTypeValue() + ", getAcaoEnumValue()=" + getAcaoEnumValue() + ", getParentId()=" + getParentId()
				+ ", getType()=" + getType() + ", getAcaoType()=" + getAcaoType() + ", getTabelaEnum()="
				+ getTabelaEnum() + ", getStatusList()=" + getStatusList() + ", getEmprId()=" + getEmprId()
				+ ", getSite()=" + getSite() + ", getUserId()=" + getUserId() + ", toString()=" + super.toString()
				+ ", getModelAction()=" + getModelAction() + ", getCreateUser()=" + getCreateUser()
				+ ", getCreateDateUTC()=" + getCreateDateUTC() + ", getModifyUser()=" + getModifyUser()
				+ ", getModifyDateUTC()=" + getModifyDateUTC() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + "]";
	}

}
