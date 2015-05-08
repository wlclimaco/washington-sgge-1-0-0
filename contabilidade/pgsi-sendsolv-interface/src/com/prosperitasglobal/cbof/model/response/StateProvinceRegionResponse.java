package com.prosperitasglobal.cbof.model.response;

import java.util.Collection;
import java.util.List;

import com.prosperitasglobal.cbof.model.StateProvinceRegion;
import com.qat.framework.model.response.InquiryResponse;

/**
 * The Class StateProvinceRegionResponse.
 */
public class StateProvinceRegionResponse extends InquiryResponse
{

	/** Attributes. */
	private List<StateProvinceRegion> stateProvinceRegionList;

	/**
	 * The Constructor.
	 */
	public StateProvinceRegionResponse()
	{

	}

	/**
	 * Gets the state province region list.
	 *
	 * @return the state province region list
	 */
	public List<StateProvinceRegion> getStateProvinceRegionList()
	{
		return stateProvinceRegionList;
	}

	/**
	 * Sets the state province region list.
	 *
	 * @param stateProvinceRegionList the state province region list
	 */
	public void setStateProvinceRegionList(List<StateProvinceRegion> stateProvinceRegionList)
	{
		this.stateProvinceRegionList = stateProvinceRegionList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"unchecked", "rawtypes"})
	@Override
	public void addResults(Collection coll)
	{
		setStateProvinceRegionList((List<StateProvinceRegion>)coll);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "StateProvinceRegionResponse [getStateProvinceRegionList()=" + getStateProvinceRegionList()
				+ ", getResultsSetInfo()=" + getResultsSetInfo() + ", getMessageIterator()=" + getMessageIterator()
				+ ", getMessageList()=" + getMessageList() + ", getMessageInfoList()=" + getMessageInfoList()
				+ ", isOperationSuccess()=" + isOperationSuccess() + "]";
	}

}
