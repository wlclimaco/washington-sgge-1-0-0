package com.prosperitasglobal.sendsolv.sdn.model;

import com.prosperitasglobal.sendsolv.sdn.model.generated.SdnEntry;
import com.qat.framework.model.QATModel;

@SuppressWarnings("serial")
public class ExtendedSdnEntry extends QATModel
{
	private SdnEntry sdnEntry;

	private SdnMatchRecord sdnMatchRecord;

	public ExtendedSdnEntry()
	{

	}

	public ExtendedSdnEntry(SdnEntry sdnEntry)
	{
		setSdnEntry(sdnEntry);
	}

	public SdnEntry getSdnEntry()
	{
		return sdnEntry;
	}

	public void setSdnEntry(SdnEntry sdnEntry)
	{
		this.sdnEntry = sdnEntry;
	}

	public String getFirstName()
	{
		return getSdnEntry().getFirstName();
	}

	public String getLastName()
	{
		return getSdnEntry().getLastName();
	}

	public SdnMatchRecord getSdnMatchRecord()
	{
		return sdnMatchRecord;
	}

	public void setSdnMatchRecord(SdnMatchRecord sdnMatchRecord)
	{
		this.sdnMatchRecord = sdnMatchRecord;
	}

	@Override
	public String toString()
	{
		return "ExtendedSdnEntry [getSdnEntry()=" + getSdnEntry() + ", getFirstName()=" + getFirstName()
				+ ", getLastName()=" + getLastName() + ", getSdnMatchRecord()=" + getSdnMatchRecord()
				+ ", getModelAction()=" + getModelAction() + ", getCreateUser()=" + getCreateUser()
				+ ", getCreateDateUTC()=" + getCreateDateUTC() + ", getModifyUser()=" + getModifyUser()
				+ ", getModifyDateUTC()=" + getModifyDateUTC() + "]";
	}

}
