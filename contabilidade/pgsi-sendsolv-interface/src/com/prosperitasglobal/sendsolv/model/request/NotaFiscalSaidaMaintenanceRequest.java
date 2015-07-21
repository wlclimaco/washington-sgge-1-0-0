package com.prosperitasglobal.sendsolv.model.request;

import com.prosperitasglobal.sendsolv.model.NotaFiscalSaida;
import com.qat.framework.model.request.MaintenanceRequest;

public class NotaFiscalSaidaMaintenanceRequest extends MaintenanceRequest
{

	/** Attributes */
	private NotaFiscalSaida notafiscalList;

	/**
	 * The Constructor.
	 */
	public NotaFiscalSaidaMaintenanceRequest()
	{

	}

	/**
	 * @return the notafiscalList
	 */
	public NotaFiscalSaida getNotafiscalList()
	{
		return notafiscalList;
	}

	/**
	 * @param notafiscalList the notafiscalList to set
	 */
	public void setNotafiscalList(NotaFiscalSaida notafiscalList)
	{
		this.notafiscalList = notafiscalList;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "NotaFiscalSaidaMaintenanceRequest [getNotafiscalList()=" + getNotafiscalList() + ", getUserContext()="
				+ getUserContext() + "]";
	}

}