package com.prosperitasglobal.sendsolv.model.response;

import java.util.List;

import com.prosperitasglobal.sendsolv.model.PedidoCompras;
import com.qat.framework.model.response.InquiryResponse;

public class PedidoComprasResponse extends InquiryResponse
{

	/** Attributes */
	private List<PedidoCompras> pedidoComprasList;

	/**
	 * The Constructor.
	 */
	public PedidoComprasResponse()
	{

	}

	/**
	 * @return the pedidoComprasList
	 */
	public List<PedidoCompras> getPedidoComprasList()
	{
		return pedidoComprasList;
	}

	/**
	 * @param pedidoComprasList the pedidoComprasList to set
	 */
	public void setPedidoComprasList(List<PedidoCompras> pedidoComprasList)
	{
		this.pedidoComprasList = pedidoComprasList;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "PedidoComprasResponse [getPedidoComprasList()=" + getPedidoComprasList() + ", getResultsSetInfo()="
				+ getResultsSetInfo() + ", getMessageIterator()=" + getMessageIterator() + ", getMessageList()="
				+ getMessageList() + ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()="
				+ isOperationSuccess() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

}