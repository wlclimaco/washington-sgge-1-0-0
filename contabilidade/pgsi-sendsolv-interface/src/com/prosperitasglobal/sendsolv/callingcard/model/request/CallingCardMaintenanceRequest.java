package com.prosperitasglobal.sendsolv.callingcard.model.request;

import com.prosperitasglobal.sendsolv.callingcard.model.CallingCardInfo;
import com.qat.framework.model.request.MaintenanceRequest;

/**
 * The Class CallingCardMaintenanceRequest.
 */
public class CallingCardMaintenanceRequest extends MaintenanceRequest
{

	private CallingCardInfo callingCardInfo;

	/**
	 * The Constructor.
	 */
	public CallingCardMaintenanceRequest()
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
		return "CallingCardMaintenanceRequest [getCallingCardInfo()=" + getCallingCardInfo() + ", getUserContext()="
				+ getUserContext() + "]";
	}

}