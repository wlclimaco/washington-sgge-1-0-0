package com.qat.samples.sysmgmt.nf.model.request;

import com.qat.framework.model.request.Request;
import com.qat.samples.sysmgmt.nf.model.NotaFiscal;

public class NotaFiscalMaintenanceRequest extends Request
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

	@Override
	public String toString() {
		return "NotaFiscalMaintenanceRequest [getNotafiscal()=" + getNotafiscal() + ", toString()=" + super.toString()
				+ "]";
	}

}