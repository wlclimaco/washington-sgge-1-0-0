package com.qat.samples.sysmgmt.historico.model.request;

import com.qat.framework.model.request.MaintenanceRequest;
import com.qat.samples.sysmgmt.historico.Historico;

public class HistoricoMaintenanceRequest extends MaintenanceRequest
{

	/** Attributes. */
	private Historico historico;

	/**
	 * The Constructor.
	 */
	public HistoricoMaintenanceRequest()
	{

	}

	/**
	 * Gets the historico.
	 * 
	 * @return the historico
	 */
	public Historico getHistorico()
	{
		return historico;
	}

	/**
	 * Sets the historico.
	 * 
	 * @param historico the historico
	 */
	public void setHistorico(Historico historico)
	{
		this.historico = historico;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "HistoricoMaintenanceRequest [getHistorico()=" + getHistorico() + ", getUserContext()="
				+ getUserContext() + "]";
	}
}