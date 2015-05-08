package com.prosperitasglobal.sendsolv.sdn.model.request.criteria;

import java.io.Serializable;

import com.prosperitasglobal.sendsolv.sdn.model.SdnMatchTypeEnum;

/**
 * The Class MemberCriteria.
 */
@SuppressWarnings("serial")
public class SdnStatusHistoryCriteria implements Serializable
{
	private SdnMatchTypeEnum matchType;

	public SdnMatchTypeEnum getMatchType()
	{
		return matchType;
	}

	public void setMatchType(SdnMatchTypeEnum matchType)
	{
		this.matchType = matchType;
	}

	@Override
	public String toString()
	{
		return "SdnStatusHistoryCriteria [getMatchType()=" + getMatchType() + "]";
	}
}
