package com.qat.samples.sysmgmt.nf.model.request;

import com.qat.framework.model.request.MaintenanceRequest;
import com.qat.samples.sysmgmt.nf.model.NotaFiscalEntrada;

public class NotaFiscalEntradaMaintenanceRequest extends MaintenanceRequest
{

	/** Attributes */
	private NotaFiscalEntrada notafiscal;

	/**
	 * The Constructor.
	 */
	public NotaFiscalEntradaMaintenanceRequest()
	{

	}

	/**
	 * @return the notafiscal
	 */
	public NotaFiscalEntrada getNotafiscal()
	{
		return notafiscal;
	}

	/**
	 * @param notafiscal the notafiscal to set
	 */
	public void setNotafiscal(NotaFiscalEntrada notafiscal)
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
		return "NotaFiscalEntradaMaintenanceRequest [getNotafiscal()=" + getNotafiscal() + ", getUserContext()="
				+ getUserContext() + ", toString()=" + super.toString() + "]";
	}

}