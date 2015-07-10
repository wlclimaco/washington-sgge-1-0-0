package com.prosperitasglobal.sendsolv.model;

import java.util.List;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Historico extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;

	/** The numero. */
	private Long data;

	private List<HistoricoItens> historicoItensList;

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * @return the data
	 */
	public Long getData()
	{
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(Long data)
	{
		this.data = data;
	}

	/**
	 * @return the historicoItensList
	 */
	public List<HistoricoItens> getHistoricoItensList()
	{
		return historicoItensList;
	}

	/**
	 * @param historicoItensList the historicoItensList to set
	 */
	public void setHistoricoItensList(List<HistoricoItens> historicoItensList)
	{
		this.historicoItensList = historicoItensList;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Historico [getId()=" + getId() + ", getData()=" + getData() + ", getHistoricoItensList()="
				+ getHistoricoItensList() + ", getTabelaEnumValue()=" + getTabelaEnumValue() + ", getTypeValue()="
				+ getTypeValue() + ", getAcaoEnumValue()=" + getAcaoEnumValue() + ", getParentId()=" + getParentId()
				+ ", getType()=" + getType() + ", getAcaoType()=" + getAcaoType() + ", getTabelaEnum()="
				+ getTabelaEnum() + ", getStatusList()=" + getStatusList() + ", getEmprId()=" + getEmprId()
				+ ", getSite()=" + getSite() + ", getProcessId()=" + getProcessId() + ", getUserId()=" + getUserId()
				+ ", toString()=" + super.toString() + ", getModelAction()=" + getModelAction() + ", getCreateUser()="
				+ getCreateUser() + ", getCreateDateUTC()=" + getCreateDateUTC() + ", getModifyUser()="
				+ getModifyUser() + ", getModifyDateUTC()=" + getModifyDateUTC() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + "]";
	}

}
