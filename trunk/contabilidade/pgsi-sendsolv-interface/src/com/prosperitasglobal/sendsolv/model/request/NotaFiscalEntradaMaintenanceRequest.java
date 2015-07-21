package com.prosperitasglobal.sendsolv.model.request;

import com.prosperitasglobal.sendsolv.model.NotaFiscalEntrada;
import com.qat.framework.model.request.MaintenanceRequest;

public class NotaFiscalEntradaMaintenanceRequest extends MaintenanceRequest
{

	/** Attributes */
	private NotaFiscalEntrada notafiscalList;

	/**
	 * The Constructor.
	 */
	public NotaFiscalEntradaMaintenanceRequest()
	{

	}

	/**
	 * @return the notafiscalList
	 */
	public NotaFiscalEntrada getNotafiscalList()
	{
		return notafiscalList;
	}

	/**
	 * @param notafiscalList the notafiscalList to set
	 */
	public void setNotafiscalList(NotaFiscalEntrada notafiscalList)
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
		return "NotaFiscalEntradaMaintenanceRequest [getNotafiscalList()=" + getNotafiscalList()
				+ ", getUserContext()=" + getUserContext() + ", toString()=" + super.toString() + "]";
	}

}