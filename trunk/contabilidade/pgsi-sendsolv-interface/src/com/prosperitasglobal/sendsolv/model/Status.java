package com.prosperitasglobal.sendsolv.model;

import com.qat.framework.model.QATModelOL;

/**
 * The Class Document represents a generic formal business or personal document, such as driver's license or bylaws.
 */
@SuppressWarnings("serial")
public class Status extends QATModelOL
{

	/** Attributes. */
	private Integer id;

	/** The parent key. */
	private Integer dataStatus;

	/** The parent key type. */
	private StatusEnum status;

	private TypeEnum type;

	private AcaoEnum acaoType;

	private TabelaEnum tabelaEnum;

	private String note;

	/**
	 * The Constructor.
	 */
	public Status()
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
	 * @return the dataStatus
	 */
	public Integer getDataStatus()
	{
		return dataStatus;
	}

	/**
	 * @param dataStatus the dataStatus to set
	 */
	public void setDataStatus(Integer dataStatus)
	{
		this.dataStatus = dataStatus;
	}

	/**
	 * @return the status
	 */
	public StatusEnum getStatus()
	{
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(StatusEnum status)
	{
		this.status = status;
	}

	/**
	 * @return the type
	 */
	public TypeEnum getType()
	{
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(TypeEnum type)
	{
		this.type = type;
	}

	/**
	 * @return the acaoType
	 */
	public AcaoEnum getAcaoType()
	{
		return acaoType;
	}

	/**
	 * @param acaoType the acaoType to set
	 */
	public void setAcaoType(AcaoEnum acaoType)
	{
		this.acaoType = acaoType;
	}

	/**
	 * @return the tabelaEnum
	 */
	public TabelaEnum getTabelaEnum()
	{
		return tabelaEnum;
	}

	/**
	 * @param tabelaEnum the tabelaEnum to set
	 */
	public void setTabelaEnum(TabelaEnum tabelaEnum)
	{
		this.tabelaEnum = tabelaEnum;
	}

	/**
	 * @return the note
	 */
	public String getNote()
	{
		return note;
	}

	/**
	 * @param note the note to set
	 */
	public void setNote(String note)
	{
		this.note = note;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Status [getId()=" + getId() + ", getDataStatus()=" + getDataStatus() + ", getStatus()=" + getStatus()
				+ ", getType()=" + getType() + ", getAcaoType()=" + getAcaoType() + ", getTabelaEnum()="
				+ getTabelaEnum() + ", getNote()=" + getNote() + ", getVersion()=" + getVersion()
				+ ", getModelAction()=" + getModelAction() + ", getCreateUser()=" + getCreateUser()
				+ ", getCreateDateUTC()=" + getCreateDateUTC() + ", getModifyUser()=" + getModifyUser()
				+ ", getModifyDateUTC()=" + getModifyDateUTC() + ", toString()=" + super.toString() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + "]";
	}

}