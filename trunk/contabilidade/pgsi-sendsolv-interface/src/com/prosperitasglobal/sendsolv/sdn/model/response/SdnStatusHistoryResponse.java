package com.prosperitasglobal.sendsolv.sdn.model.response;

import java.util.Collection;
import java.util.List;

import com.prosperitasglobal.sendsolv.sdn.model.SdnStatusHistory;
import com.qat.framework.model.response.InquiryResponse;

/**
 * The Class SdnCheckerResponse.
 */
public class SdnStatusHistoryResponse extends InquiryResponse
{
	private List<SdnStatusHistory> sdnStatusHistoryList;

	public List<SdnStatusHistory> getSdnStatusHistoryList()
	{
		return sdnStatusHistoryList;
	}

	public void setSdnStatusHistoryList(List<SdnStatusHistory> sdnStatusHistoryList)
	{
		this.sdnStatusHistoryList = sdnStatusHistoryList;
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	@Override
	public void addResults(Collection coll)
	{
		setSdnStatusHistoryList((List<SdnStatusHistory>)coll);
	}

	@Override
	public String toString()
	{
		return "SdnStatusHistoryResponse [getSdnStatusHistoryList()=" + getSdnStatusHistoryList()
				+ ", getResultsSetInfo()=" + getResultsSetInfo() + ", getMessageList()=" + getMessageList()
				+ ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()=" + isOperationSuccess()
				+ "]";
	}
}
