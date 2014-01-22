package com.sensus.lc.light.model.response;

import java.util.List;

import com.sensus.common.model.response.InquiryResponse;
import com.sensus.lc.light.model.LastOperationalData;

/**
 * The Class LastOperationalDataMaintenanceResponse.
 */
public class LastOperationalDataMaintenanceResponse extends InquiryResponse
{

	/** The last operational data list. */
	private List<LastOperationalData> lastOperationalDataList;

	/**
	 * Gets the last operational data list.
	 *
	 * @return the last operational data list
	 */
	public List<LastOperationalData> getLastOperationalDataList()
	{
		return lastOperationalDataList;
	}

	/**
	 * Sets the last operational data list.
	 *
	 * @param lastOperationalDataList the new last operational data list
	 */
	public void setLastOperationalDataList(List<LastOperationalData> lastOperationalDataList)
	{
		this.lastOperationalDataList = lastOperationalDataList;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LastOperationalDataMaintenanceResponse [getLastOperationalDataList()=" + getLastOperationalDataList()
				+ ", toString()=" + super.toString() + "]";
	}


}
