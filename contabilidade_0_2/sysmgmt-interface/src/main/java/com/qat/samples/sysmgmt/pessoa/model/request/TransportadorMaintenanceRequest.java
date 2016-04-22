package com.qat.samples.sysmgmt.pessoa.model.request;

import com.qat.samples.sysmgmt.pessoa.model.Transportador;
import com.qat.samples.sysmgmt.util.model.request.UtilMaintenanceRequest;

public class TransportadorMaintenanceRequest extends UtilMaintenanceRequest
{

	/** Attributes */
	private Transportador transportador;

	/**
	 * The Constructor.
	 */
	public TransportadorMaintenanceRequest()
	{

	}

	/**
	 * @return the transportador
	 */
	public Transportador getTransportador()
	{
		return transportador;
	}

	/**
	 * @param transportador the transportador to set
	 */
	public void setTransportador(Transportador transportador)
	{
		this.transportador = transportador;
	}

	@Override
	public String toString() {
		return "TransportadorMaintenanceRequest [getTransportador()=" + getTransportador() + ", toString()="
				+ super.toString() + "]";
	}

}