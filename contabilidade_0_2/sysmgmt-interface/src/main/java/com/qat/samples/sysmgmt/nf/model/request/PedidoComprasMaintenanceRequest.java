package com.qat.samples.sysmgmt.nf.model.request;

import com.qat.framework.model.request.Request;
import com.qat.samples.sysmgmt.nf.model.PedidoCompras;
import com.qat.samples.sysmgmt.util.model.request.UtilMaintenanceRequest;

public class PedidoComprasMaintenanceRequest extends UtilMaintenanceRequest
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

	@Override
	public String toString() {
		return "PedidoComprasMaintenanceRequest [getPedidoCompras()=" + getPedidoCompras() + ", toString()="
				+ super.toString() + "]";
	}

}