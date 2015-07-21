package com.prosperitasglobal.sendsolv.model.request;

import com.prosperitasglobal.sendsolv.model.PedidoCompras;
import com.qat.framework.model.request.MaintenanceRequest;

public class PedidoComprasMaintenanceRequest extends MaintenanceRequest
{

	/** Attributes */
	private PedidoCompras pedidoCompras;

	/**
	 * The Constructor.
	 */
	public PedidoComprasMaintenanceRequest()
	{

	}

	/**
	 * @return the pedidoCompras
	 */
	public PedidoCompras getPedidoCompras()
	{
		return pedidoCompras;
	}

	/**
	 * @param pedidoCompras the pedidoCompras to set
	 */
	public void setPedidoCompras(PedidoCompras pedidoCompras)
	{
		this.pedidoCompras = pedidoCompras;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "PedidoComprasMaintenanceRequest [getPedidoCompras()=" + getPedidoCompras() + ", getUserContext()="
				+ getUserContext()
				+ "]";
	}

}