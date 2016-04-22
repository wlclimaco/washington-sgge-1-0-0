package com.qat.samples.sysmgmt.historico.model.request;

import com.qat.framework.model.request.Request;
import com.qat.samples.sysmgmt.historico.model.Historico;

public class HistoricoMaintenanceRequest extends Request
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

	@Override
	public String toString() {
		return "HistoricoMaintenanceRequest [getHistorico()=" + getHistorico() + ", toString()=" + super.toString()
				+ "]";
	}
}