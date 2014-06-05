package com.qat.samples.sysmgmt.listaCompras.bas.rest;

import com.qat.samples.sysmgmt.listaCompras.bai.IListaComprasBAI;
import com.qat.samples.sysmgmt.listaCompras.bas.IListaComprasRESTBAS;
import com.qat.samples.sysmgmt.listaCompras.model.request.ListaComprasMaintenanceRequest;
import com.qat.samples.sysmgmt.listaCompras.model.response.ListaComprasResponse;
import com.qat.samples.sysmgmt.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.model.request.RefreshRequest;

/**
 * Standard implementation of a BAS where the operations are delegated to a BAI.
 * Note the BAI is injected by Spring.
 */
public class ListaComprasBAS implements IListaComprasRESTBAS
{

	/** The listaCompras bai. */
	private IListaComprasBAI listaComprasBAI; // injected by Spring through setter

	/**
	 * Sets the listaCompras bai.
	 * 
	 * @param listaComprasBAI the new listaCompras bai
	 */
	public void setListaComprasBAI(IListaComprasBAI listaComprasBAI)
	{
		this.listaComprasBAI = listaComprasBAI;
	}

	/**
	 * Gets the listaCompras bai.
	 * 
	 * @return the listaCompras bai
	 */
	public IListaComprasBAI getListaComprasBAI()
	{
		return listaComprasBAI;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.bas.IListaComprasRESTBAS#insertListaCompras(com.qat.samples.sysmgmt.model.request.
	 * ListaComprasMaintenanceRequest)
	 */
	@Override
	public ListaComprasResponse insertListaCompras(ListaComprasMaintenanceRequest request)
	{
		return getListaComprasBAI().insertListaCompras(request);
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.bas.IListaComprasRESTBAS#updateListaCompras(com.qat.samples.sysmgmt.model.request.
	 * ListaComprasMaintenanceRequest)
	 */
	@Override
	public ListaComprasResponse updateListaCompras(ListaComprasMaintenanceRequest request)
	{
		return getListaComprasBAI().updateListaCompras(request);
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.bas.IListaComprasRESTBAS#deleteListaCompras(com.qat.samples.sysmgmt.model.request.
	 * ListaComprasMaintenanceRequest)
	 */
	@Override
	public ListaComprasResponse deleteListaCompras(ListaComprasMaintenanceRequest request)
	{
		return getListaComprasBAI().deleteListaCompras(request);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bas.IListaComprasRESTBAS#refreshListaComprass(com.qat.samples.sysmgmt.model.request.
	 * RefreshRequest)
	 */
	@Override
	public ListaComprasResponse refreshListaComprass(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		return getListaComprasBAI().refreshListaComprass(request);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bas.IListaComprasRESTBAS#fetchAllListaComprass(com.qat.samples.sysmgmt.model.request.
	 * FetchAllRequest)
	 */
	@Override
	public ListaComprasResponse fetchAllListaComprass(FetchAllRequest request)
	{
		return getListaComprasBAI().fetchAllListaComprass(request);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bas.IListaComprasRESTBAS#fetchListaComprasById(com.qat.samples.sysmgmt.model.request.
	 * FetchByIdRequest
	 * )
	 */
	@Override
	public ListaComprasResponse fetchListaComprasById(FetchByIdRequest request)
	{
		return getListaComprasBAI().fetchListaComprasById(request);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bas.IListaComprasRESTBAS#fetchListaComprassByRequest(com.qat.samples.sysmgmt.model.request
	 * .
	 * PagedInquiryRequest)
	 */
	@Override
	public ListaComprasResponse fetchListaComprassByRequest(PagedInquiryRequest request)
	{
		return getListaComprasBAI().fetchListaComprassByRequest(request);
	}
}
