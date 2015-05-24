package com.prosperitasglobal.sendsolv.model.request;

import com.prosperitasglobal.sendsolv.model.SuspiciousActivity;
import com.qat.framework.model.request.Request;

public class SarMaintenanceRequest extends Request
{
	private SuspiciousActivity suspiciousActivity;

	public SuspiciousActivity getSuspiciousActivity()
	{
		return suspiciousActivity;
	}

	public void setSuspiciousActivity(SuspiciousActivity suspiciousActivity)
	{
		this.suspiciousActivity = suspiciousActivity;
	}

	@Override
	public String toString()
	{
		return "SarMaintenanceRequest [getSuspiciousActivity()=" + getSuspiciousActivity() + ", getUserContext()="
				+ getUserContext() + "]";
	}

}
