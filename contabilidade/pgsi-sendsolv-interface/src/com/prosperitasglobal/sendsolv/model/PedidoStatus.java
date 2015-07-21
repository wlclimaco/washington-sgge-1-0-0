package com.prosperitasglobal.sendsolv.model;

import java.util.List;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class PedidoStatus extends ModelCosmeDamiao
{

	private Integer id;

	private Integer notaId;

	private Long data;

	private List<NotaTypeEnum> notaTypeEnumList;

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
	 * @return the notaId
	 */
	public Integer getNotaId()
	{
		return notaId;
	}

	/**
	 * @param notaId the notaId to set
	 */
	public void setNotaId(Integer notaId)
	{
		this.notaId = notaId;
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
	 * @return the notaTypeEnumList
	 */
	public List<NotaTypeEnum> getNotaTypeEnumList()
	{
		return notaTypeEnumList;
	}

	/**
	 * @param notaTypeEnumList the notaTypeEnumList to set
	 */
	public void setNotaTypeEnumList(List<NotaTypeEnum> notaTypeEnumList)
	{
		this.notaTypeEnumList = notaTypeEnumList;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "PedidoStatus [getId()=" + getId() + ", getNotaId()=" + getNotaId() + ", getData()=" + getData()
				+ ", getNotaTypeEnumList()=" + getNotaTypeEnumList() + ", getTabelaEnumValue()=" + getTabelaEnumValue()
				+ ", getTypeValue()=" + getTypeValue() + ", getAcaoEnumValue()=" + getAcaoEnumValue()
				+ ", getParentId()=" + getParentId() + ", getType()=" + getType() + ", getAcaoType()=" + getAcaoType()
				+ ", getTabelaEnum()=" + getTabelaEnum() + ", getStatusList()=" + getStatusList() + ", getEmprId()="
				+ getEmprId() + ", getSite()=" + getSite() + ", getProcessId()=" + getProcessId() + ", getUserId()="
				+ getUserId() + ", getNotes()=" + getNotes() + ", toString()=" + super.toString()
				+ ", getModelAction()=" + getModelAction() + ", getCreateUser()=" + getCreateUser()
				+ ", getCreateDateUTC()=" + getCreateDateUTC() + ", getModifyUser()=" + getModifyUser()
				+ ", getModifyDateUTC()=" + getModifyDateUTC() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + "]";
	}

}
