package com.prosperitasglobal.sendsolv.model.request;

import com.prosperitasglobal.sendsolv.model.Transportador;

public class TransportadorMaintenanceRequest extends MaintenanceRequest
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

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "TransportadorMaintenanceRequest [getTransportador()=" + getTransportador() + ", getUserContext()="
				+ getUserContext()
				+ "]";
	}

}