package com.prosperitasglobal.sendsolv.model.request;

import com.prosperitasglobal.sendsolv.model.NotaFiscalSaida;
import com.qat.framework.model.request.MaintenanceRequest;

public class NotaFiscalSaidaMaintenanceRequest extends MaintenanceRequest
{

	/** Attributes */
	private NotaFiscalSaida notafiscal;

	/**
	 * The Constructor.
	 */
	public NotaFiscalSaidaMaintenanceRequest()
	{

	}

	/**
	 * @return the notafiscalList
	 */
	public NotaFiscalSaida getNotafiscal()
	{
		return notafiscal;
	}

	/**
	 * @param notafiscalList the notafiscalList to set
	 */
	public void setNotafiscal(NotaFiscalSaida notafiscal)
	{
		this.notafiscal = notafiscal;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "NotaFiscalSaidaMaintenanceRequest [getNotafiscal()=" + getNotafiscal() + ", getUserContext()="
				+ getUserContext() + "]";
	}

}