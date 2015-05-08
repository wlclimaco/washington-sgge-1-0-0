package com.prosperitasglobal.sendsolv.sdn.model.request;

import com.prosperitasglobal.sendsolv.sdn.model.SdnMatchTypeEnum;
import com.prosperitasglobal.sendsolv.sdn.model.SdnStatusHistory;
import com.qat.framework.model.request.Request;

public class SdnStatusHistoryRequest extends Request
{
	private SdnMatchTypeEnum matchType;

	private SdnStatusHistory sdnStatusHistory;

	public SdnMatchTypeEnum getMatchType()
	{
		return matchType;
	}

	public void setMatchType(SdnMatchTypeEnum matchType)
	{
		this.matchType = matchType;
	}

	public SdnStatusHistory getSdnStatusHistory()
	{
		return sdnStatusHistory;
	}

	public void setSdnStatusHistory(SdnStatusHistory sdnStatusHistory)
	{
		this.sdnStatusHistory = sdnStatusHistory;
	}

	@Override
	public String toString()
	{
		return "SdnHistoryRequest [getMatchType()=" + getMatchType() + ", getSdnStatusHistory()="
				+ getSdnStatusHistory() + ", getUserContext()=" + getUserContext() + "]";
	}
}
