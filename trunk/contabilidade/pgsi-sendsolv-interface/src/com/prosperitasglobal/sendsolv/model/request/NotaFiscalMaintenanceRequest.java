package com.prosperitasglobal.sendsolv.model.request;

import com.prosperitasglobal.sendsolv.model.NotaFiscal;
import com.qat.framework.model.request.MaintenanceRequest;

public class NotaFiscalMaintenanceRequest extends MaintenanceRequest
{

	/** Attributes. */
	private NotaFiscal notaFiscal;

	/**
	 * The Constructor.
	 */
	public NotaFiscalMaintenanceRequest()
	{

	}

	/**
	 * @return the notaFiscal
	 */
	public NotaFiscal getNotaFiscal()
	{
		return notaFiscal;
	}

	/**
	 * @param notaFiscal the notaFiscal to set
	 */
	public void setNotaFiscal(NotaFiscal notaFiscal)
	{
		this.notaFiscal = notaFiscal;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "ProdutoMaintenanceRequest [getNotaFiscal()=" + getNotaFiscal() + ", getUserContext()="
				+ getUserContext() + "]";
	}
}