package com.qat.samples.sysmgmt.dp.model.request;

import com.qat.samples.sysmgmt.dp.model.Eventos;
import com.qat.samples.sysmgmt.util.model.request.UtilMaintenanceRequest;

// TODO: Auto-generated Javadoc
/**
 * The Class LocationMaintenanceRequest.
 *
 * @author abarros
 * @version 1.0
 * @created 22-Jul-2014 10:26:49 AM
 */
public class EventosMaintenanceRequest extends UtilMaintenanceRequest
{

	/** Attributes. */
	private Eventos funcionario;

	/**
	 * The Constructor.
	 */
	public EventosMaintenanceRequest()
	{

	}

	/**
	 * Gets the funcionario.
	 *
	 * @return the funcionario
	 */
	public Eventos getEventos()
	{
		return funcionario;
	}

	/**
	 * Sets the funcionario.
	 *
	 * @param funcionario the funcionario to set
	 */
	public void setEventos(Eventos funcionario)
	{
		this.funcionario = funcionario;
	}

	@Override
	public String toString() {
		return "EventosMaintenanceRequest [getEventos()=" + getEventos() + ", toString()="
				+ super.toString() + "]";
	}

}