package com.prosperitasglobal.sendsolv.sdn.model;

import java.util.List;

import com.qat.framework.model.QATModel;

@SuppressWarnings("serial")
public class SdnStatusHistory extends QATModel
{
	private Integer id;

	private Integer parentKey;

	private SDNStatusEnum sdnStatus;

	private Integer parentSdnStatusHistoryId;

	private String noteText;

	private List<SdnMatchRecord> sdnMatchRecordList;

	public Integer getParentKey()
	{
		return parentKey;
	}

	public void setParentKey(Integer parentKey)
	{
		this.parentKey = parentKey;
	}

	public SDNStatusEnum getSdnStatus()
	{
		return sdnStatus;
	}

	public void setSdnStatus(SDNStatusEnum sdnStatus)
	{
		this.sdnStatus = sdnStatus;
	}

	/**
	 * Methods that follow the naming pattern get.....Value() provide convenience for returning the primitive value of
	 * an enum. For example, database mapping of an enum to a database column could make use of this method.
	 *
	 * @return the contact type value
	 */
	public Integer getSdnStatusValue()
	{
		if (sdnStatus != null)
		{
			return sdnStatus.getValue();
		}
		return null;
	}

	/**
	 * Methods that follow the naming pattern set.....Value(argValue) provide convenience for assigning the primitive
	 * value of an enum. For example, database mapping of an database column to an enum could make use of this method.
	 *
	 * @param contactTypeValue the contact type value
	 */
	public void setSdnStatusValue(Integer sdnStatusValue)
	{
		sdnStatus = SDNStatusEnum.enumForValue(sdnStatusValue);
	}

	public String getNoteText()
	{
		return noteText;
	}

	public void setNoteText(String noteText)
	{
		this.noteText = noteText;
	}

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public List<SdnMatchRecord> getSdnMatchRecordList()
	{
		return sdnMatchRecordList;
	}

	public void setSdnMatchRecordList(List<SdnMatchRecord> sdnMatchRecordList)
	{
		this.sdnMatchRecordList = sdnMatchRecordList;
	}

	public Integer getParentSdnStatusHistoryId()
	{
		return parentSdnStatusHistoryId;
	}

	public void setParentSdnStatusHistoryId(Integer parentSdnStatusHistoryId)
	{
		this.parentSdnStatusHistoryId = parentSdnStatusHistoryId;
	}

	@Override
	public String toString()
	{
		return "SdnStatusHistory [getParentKey()=" + getParentKey() + ", getSdnStatus()=" + getSdnStatus()
				+ ", getSdnStatusValue()=" + getSdnStatusValue() + ", getNoteText()=" + getNoteText() + ", getId()="
				+ getId() + ", getSdnMatchRecordList()=" + getSdnMatchRecordList() + ", getParentSdnStatusHistoryId()="
				+ getParentSdnStatusHistoryId() + ", getModelAction()=" + getModelAction() + ", getCreateUser()="
				+ getCreateUser() + ", getCreateDateUTC()=" + getCreateDateUTC() + ", getModifyUser()="
				+ getModifyUser() + ", getModifyDateUTC()=" + getModifyDateUTC() + "]";
	}
}
