package com.prosperitasglobal.sendsolv.callingcard.model.response;

import com.prosperitasglobal.sendsolv.callingcard.model.CallingCardInfo;
import com.qat.framework.model.response.MaintenanceResponse;

/**
 * The Class CallingCardMaintenanceRequest.
 */
public class CallingCardMaintenanceResponse extends MaintenanceResponse
{

	private CallingCardInfo callingCardInfo;

	/**
	 * The Constructor.
	 */
	public CallingCardMaintenanceResponse()
	{

	}

	public CallingCardInfo getCallingCardInfo()
	{
		return callingCardInfo;
	}

	public void setCallingCardInfo(CallingCardInfo callingCardInfo)
	{
		this.callingCardInfo = callingCardInfo;
	}

	@Override
	public String toString()
	{
		return "CallingCardMaintenanceResponse [getCallingCardInfo()=" + getCallingCardInfo() + ", getMessageList()="
				+ getMessageList() + ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()="
				+ isOperationSuccess() + "]";
	}
}