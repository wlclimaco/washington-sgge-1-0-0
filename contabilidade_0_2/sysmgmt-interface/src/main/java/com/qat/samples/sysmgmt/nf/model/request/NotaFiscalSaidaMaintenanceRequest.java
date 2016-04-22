package com.qat.samples.sysmgmt.nf.model.request;

import com.qat.framework.model.request.Request;
import com.qat.samples.sysmgmt.nf.model.NotaFiscalSaida;
import com.qat.samples.sysmgmt.util.model.request.UtilMaintenanceRequest;

public class NotaFiscalSaidaMaintenanceRequest extends UtilMaintenanceRequest
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

	@Override
	public String toString() {
		return "NotaFiscalSaidaMaintenanceRequest [getNotafiscal()=" + getNotafiscal() + ", toString()="
				+ super.toString() + "]";
	}

}