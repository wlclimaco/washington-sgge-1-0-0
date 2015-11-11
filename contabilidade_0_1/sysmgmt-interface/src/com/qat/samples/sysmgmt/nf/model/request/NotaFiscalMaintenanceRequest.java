package com.qat.samples.sysmgmt.nf.model.request;

import com.qat.framework.model.request.MaintenanceRequest;
import com.qat.samples.sysmgmt.nf.model.NotaFiscal;

public class NotaFiscalMaintenanceRequest extends MaintenanceRequest
{

	/** Attributes. */
	private NotaFiscal notafiscal;

	/**
	 * The Constructor.
	 */
	public NotaFiscalMaintenanceRequest()
	{

	}

	/**
	 * @return the notafiscalList
	 */
	public NotaFiscal getNotafiscal()
	{
		return notafiscal;
	}

	/**
	 * @param notafiscalList the notafiscalList to set
	 */
	public void setNotafiscal(NotaFiscal notafiscal)
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
		return "NotaFiscalMaintenanceRequest [getNotafiscal()=" + getNotafiscal() + ", getUserContext()="
				+ getUserContext() + ", toString()=" + super.toString() + "]";
	}

}