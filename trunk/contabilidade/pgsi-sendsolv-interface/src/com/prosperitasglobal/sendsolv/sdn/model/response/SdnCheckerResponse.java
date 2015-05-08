package com.prosperitasglobal.sendsolv.sdn.model.response;

import java.util.Collection;
import java.util.List;

import com.prosperitasglobal.sendsolv.sdn.model.SdnCheckerResult;
import com.qat.framework.model.response.InquiryResponse;

/**
 * The Class SdnCheckerResponse.
 */
public class SdnCheckerResponse extends InquiryResponse
{

	/** The extended sdn entry list. */
	private List<SdnCheckerResult> sdnCheckerResultList;

	/**
	 * @return the extendedSdnEntryList
	 */
	public List<SdnCheckerResult> getSdnCheckerResultList()
	{
		return sdnCheckerResultList;
	}

	/**
	 * @param extendedSdnEntryList the extendedSdnEntryList to set
	 */
	public void setSdnCheckerResultList(List<SdnCheckerResult> sdnCheckerResultList)
	{
		this.sdnCheckerResultList = sdnCheckerResultList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"unchecked", "rawtypes"})
	@Override
	public void addResults(Collection coll)
	{
		setSdnCheckerResultList((List<SdnCheckerResult>)coll);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "SdnCheckerResponse [getSdnCheckerResultList()=" + getSdnCheckerResultList() + "]";
	}

}
