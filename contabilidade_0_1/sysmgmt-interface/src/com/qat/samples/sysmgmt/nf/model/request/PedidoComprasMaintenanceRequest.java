package com.qat.samples.sysmgmt.nf.model.request;

import com.qat.framework.model.request.MaintenanceRequest;
import com.qat.samples.sysmgmt.nf.model.PedidoCompras;

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