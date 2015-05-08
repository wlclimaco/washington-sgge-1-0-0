package com.prosperitasglobal.sendsolv.sdn.model;

import java.util.List;

import com.qat.framework.model.QATModel;

@SuppressWarnings("serial")
public class SdnMatchRecord extends QATModel
{
	private Integer id;
	private Integer parentKey;

	private String xmlRecord;

	private List<SdnMatchField> sdnMatchFieldList;

	public Integer getParentKey()
	{
		return parentKey;
	}

	public void setParentKey(Integer parentKey)
	{
		this.parentKey = parentKey;
	}

	public String getXmlRecord()
	{
		return xmlRecord;
	}

	public void setXmlRecord(String noteText)
	{
		xmlRecord = noteText;
	}

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public List<SdnMatchField> getSdnMatchFieldList()
	{
		return sdnMatchFieldList;
	}

	public void setSdnMatchFieldList(List<SdnMatchField> sdnMatchFieldList)
	{
		this.sdnMatchFieldList = sdnMatchFieldList;
	}

	@Override
	public String toString()
	{
		return "SdnMatchRecord [getParentKey()=" + getParentKey() + ", getXmlRecord()=" + getXmlRecord() + ", getId()="
				+ getId() + ", getSdnMatchFieldList()=" + getSdnMatchFieldList() + ", getModelAction()="
				+ getModelAction() + ", getCreateUser()=" + getCreateUser() + ", getCreateDateUTC()="
				+ getCreateDateUTC() + ", getModifyUser()=" + getModifyUser() + ", getModifyDateUTC()="
				+ getModifyDateUTC() + "]";
	}

}
