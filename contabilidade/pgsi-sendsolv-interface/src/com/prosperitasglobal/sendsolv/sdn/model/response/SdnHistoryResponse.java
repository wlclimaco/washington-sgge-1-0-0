package com.prosperitasglobal.sendsolv.sdn.model.response;

import java.util.Collection;
import java.util.List;

import com.prosperitasglobal.sendsolv.sdn.model.SdnHistory;
import com.qat.framework.model.response.InquiryResponse;

/**
 * The Class SdnHistoryResponse.
 */
public class SdnHistoryResponse extends InquiryResponse
{
	/** The sdn history list. */
	private List<SdnHistory> sdnHistoryList;

	/**
	 * Gets the sdn history list.
	 *
	 * @return the sdnHistoryList
	 */
	public List<SdnHistory> getSdnHistoryList()
	{
		return sdnHistoryList;
	}

	/**
	 * Sets the sdn history list.
	 *
	 * @param sdnHistoryList the sdnHistoryList to set
	 */
	public void setSdnHistoryList(List<SdnHistory> sdnHistoryList)
	{
		this.sdnHistoryList = sdnHistoryList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"unchecked", "rawtypes"})
	@Override
	public void addResults(Collection coll)
	{
		setSdnHistoryList((List<SdnHistory>)coll);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "SdnHistoryResponse [getSdnHistoryList()=" + getSdnHistoryList() + "]";
	}

}
