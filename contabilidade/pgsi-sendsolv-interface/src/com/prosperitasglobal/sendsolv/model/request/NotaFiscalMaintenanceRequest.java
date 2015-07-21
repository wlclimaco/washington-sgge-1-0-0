package com.prosperitasglobal.sendsolv.model.request;

import com.prosperitasglobal.sendsolv.model.NotaFiscal;
import com.qat.framework.model.request.MaintenanceRequest;

public class NotaFiscalMaintenanceRequest extends MaintenanceRequest
{

	/** Attributes. */
	private NotaFiscal notafiscalList;

	/**
	 * The Constructor.
	 */
	public NotaFiscalMaintenanceRequest()
	{

	}

	/**
	 * @return the notafiscalList
	 */
	public NotaFiscal getNotafiscalList()
	{
		return notafiscalList;
	}

	/**
	 * @param notafiscalList the notafiscalList to set
	 */
	public void setNotafiscalList(NotaFiscal notafiscalList)
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
		return "NotaFiscalMaintenanceRequest [getNotafiscalList()=" + getNotafiscalList() + ", getUserContext()="
				+ getUserContext() + ", toString()=" + super.toString() + "]";
	}

}