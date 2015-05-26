package com.prosperitasglobal.sendsolv.model;

import java.util.List;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class ModelCosmeDamiao extends QATModel
{
	/** The SendSolv id for the account. */
	private Integer parentId;

	private TypeEnum type;

	private AcaoEnum acaoType;

	private TabelaEnum tabelaEnum;

	private Integer emprId;

	private List<Status> statusList;

	/**
	 * @return the parentKey
	 */
	public Integer getParentId()
	{
		return parentId;
	}

	/**
	 * @param parentKey the parentKey to set
	 */
	public void setParentId(Integer parentKey)
	{
		parentId = parentKey;
	}

	public TypeEnum getType()
	{
		return type;
	}

	public void setType(TypeEnum type)
	{
		this.type = type;
	}

	public AcaoEnum getAcaoType()
	{
		return acaoType;
	}

	public void setAcaoType(AcaoEnum acaoType)
	{
		this.acaoType = acaoType;
	}

	public TabelaEnum getTabelaEnum()
	{
		return tabelaEnum;
	}

	public void setTabelaEnum(TabelaEnum tabelaEnum)
	{
		this.tabelaEnum = tabelaEnum;
	}

	public List<Status> getStatusList()
	{
		return statusList;
	}

	public void setStatusList(List<Status> statusList)
	{
		this.statusList = statusList;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "ModelCosmeDamiao [getParentId()=" + getParentId() + ", getType()=" + getType() + ", getAcaoType()="
				+ getAcaoType() + ", getTabelaEnum()=" + getTabelaEnum() + ", getStatusList()=" + getStatusList()
				+ ", getModelAction()=" + getModelAction() + ", getCreateUser()=" + getCreateUser()
				+ ", getCreateDateUTC()=" + getCreateDateUTC() + ", getModifyUser()=" + getModifyUser()
				+ ", getModifyDateUTC()=" + getModifyDateUTC() + ", toString()=" + super.toString() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + "]";
	}

}
