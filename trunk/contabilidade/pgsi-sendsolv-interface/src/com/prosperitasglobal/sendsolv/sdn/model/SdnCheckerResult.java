package com.prosperitasglobal.sendsolv.sdn.model;

import java.util.List;

import com.qat.framework.model.QATModel;

@SuppressWarnings("serial")
public class SdnCheckerResult extends QATModel
{
	private SDNStatusEnum sdnStatus;
	private List<ExtendedSdnEntry> matchList;

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
	public void getSdnFieldValue(Integer sdnStatusValue)
	{
		sdnStatus = SDNStatusEnum.enumForValue(sdnStatusValue);
	}

	public List<ExtendedSdnEntry> getMatchList()
	{
		return matchList;
	}

	public void setMatchList(List<ExtendedSdnEntry> matchList)
	{
		this.matchList = matchList;
	}

	@Override
	public String toString()
	{
		return "SdnCheckerResult [getSdnStatusValue()=" + getSdnStatusValue() + ", getMatchList()=" + getMatchList()
				+ ", getModelAction()=" + getModelAction() + ", getCreateUser()=" + getCreateUser()
				+ ", getCreateDateUTC()=" + getCreateDateUTC() + ", getModifyUser()=" + getModifyUser()
				+ ", getModifyDateUTC()=" + getModifyDateUTC() + "]";
	}
}
