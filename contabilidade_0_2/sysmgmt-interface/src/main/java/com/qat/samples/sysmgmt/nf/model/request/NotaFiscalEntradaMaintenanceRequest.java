package com.qat.samples.sysmgmt.nf.model.request;

import com.qat.samples.sysmgmt.nf.model.NotaFiscalEntrada;
import com.qat.samples.sysmgmt.util.model.request.UtilMaintenanceRequest;

public class NotaFiscalEntradaMaintenanceRequest extends UtilMaintenanceRequest
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

	@Override
	public String toString() {
		return "NotaFiscalEntradaMaintenanceRequest [getNotafiscal()=" + getNotafiscal() + ", toString()="
				+ super.toString() + "]";
	}



}