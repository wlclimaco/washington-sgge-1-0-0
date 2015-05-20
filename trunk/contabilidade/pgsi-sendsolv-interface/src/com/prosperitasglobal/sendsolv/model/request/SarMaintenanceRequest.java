package com.prosperitasglobal.sendsolv.model.request;

import com.prosperitasglobal.sendsolv.model.SuspiciousActivity;

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
