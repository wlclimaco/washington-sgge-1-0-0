package com.qat.samples.sysmgmt.util.model;

import java.util.List;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class ModelCosmeDamiao extends QATModel1
{
	/** The SendSolv id for the account. */
	private Integer parentId;

	private TypeEnum type;

	private AcaoEnum acaoType;

	private TabelaEnum tabelaEnum;

	private Integer processId;

	private Integer emprId;

	private String site;

	private String userId;

	private List<Status> statusList;

	private List<Note> notes;

	public Integer getTabelaEnumValue()
	{
		if (tabelaEnum != null)
		{
			return tabelaEnum.getValue();
		}
		return null;
	}

	public void setTabelaEnumValue(Integer acaoTypeValue)
	{
		tabelaEnum = TabelaEnum.enumForValue(acaoTypeValue);
	}

	public Integer getTypeValue()
	{
		if (type != null)
		{
			return type.getValue();
		}
		return null;
	}

	public void setAcaoEnumValue(Integer acaoTypeValue)
	{
		acaoType = AcaoEnum.enumForValue(acaoTypeValue);
	}

	public Integer getAcaoEnumValue()
	{
		if (acaoType != null)
		{
			return acaoType.getValue();
		}
		return null;
	}

	public void setTypeValue(Integer priorityValue)
	{
		type = TypeEnum.enumForValue(priorityValue);
	}

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
	 * @return the site
	 */
	public String getSite()
	{
		return site;
	}

	/**
	 * @param site the site to set
	 */
	public void setSite(String site)
	{
		this.site = site;
	}

	/**
	 * @return the processId
	 */
	public Integer getProcessId()
	{
		return processId;
	}

	/**
	 * @return the userId
	 */
	public String getUserId()
	{
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId)
	{
		this.userId = userId;
	}

	/**
	 * @param processId the processId to set
	 */
	public void setProcessId(Integer processId)
	{
		this.processId = processId;
	}

	/**
	 * @return the notes
	 */
	public List<Note> getNotes()
	{
		return notes;
	}

	/**
	 * @param notes the notes to set
	 */
	public void setNotes(List<Note> notes)
	{
		this.notes = notes;
	}

	@Override
	public String toString()
	{
		return "ModelCosmeDamiao [getTabelaEnumValue()=" + getTabelaEnumValue() + ", getTypeValue()=" + getTypeValue()
				+ ", getAcaoEnumValue()=" + getAcaoEnumValue() + ", getParentId()=" + getParentId() + ", getType()="
				+ getType() + ", getAcaoType()=" + getAcaoType() + ", getTabelaEnum()=" + getTabelaEnum()
				+ ", getStatusList()=" + getStatusList() + ", getEmprId()=" + getEmprId() + ", getSite()=" + getSite()
				+ ", getProcessId()=" + getProcessId() + ", getUserId()=" + getUserId() + ", getNotes()=" + getNotes()
				+ ", toString()=" + super.toString() + "]";
	}

}
