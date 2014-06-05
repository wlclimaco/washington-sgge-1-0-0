package com.qat.samples.sysmgmt.listaCompras.bai;

import com.qat.samples.sysmgmt.listaCompras.model.request.ListaComprasMaintenanceRequest;
import com.qat.samples.sysmgmt.listaCompras.model.response.ListaComprasResponse;
import com.qat.samples.sysmgmt.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.model.request.RefreshRequest;

/**
 * The Interface IListaComprasBAI. (Business Area Interface - BAI)
 */
public interface IListaComprasBAI
{

	/**
	 * Insert listaCompras.
	 * 
	 * @param request the request
	 * @return the listaCompras paged response
	 */
	public ListaComprasResponse insertListaCompras(ListaComprasMaintenanceRequest request);

	/**
	 * Update listaCompras.
	 * 
	 * @param request the request
	 * @return the listaCompras paged response
	 */
	public ListaComprasResponse updateListaCompras(ListaComprasMaintenanceRequest request);

	/**
	 * Delete listaCompras.
	 * 
	 * @param request the request
	 * @return the listaCompras paged response
	 */
	public ListaComprasResponse deleteListaCompras(ListaComprasMaintenanceRequest request);

	/**
	 * Fetch all listaComprass.
	 * 
	 * @param request the request
	 * @return the listaCompras response
	 */
	public ListaComprasResponse fetchAllListaComprass(FetchAllRequest request);

	/**
	 * Refresh listaComprass.
	 * 
	 * @param request the request
	 * @return the listaCompras paged response
	 */
	public ListaComprasResponse refreshListaComprass(RefreshRequest request);

	/**
	 * Fetch listaCompras by id.
	 * 
	 * @param request the request
	 * @return the listaCompras response
	 */
	public ListaComprasResponse fetchListaComprasById(FetchByIdRequest request);

	/**
	 * Fetch listaComprass by request.
	 * 
	 * @param request the request
	 * @return the listaCompras paged response
	 */
	public ListaComprasResponse fetchListaComprassByRequest(PagedInquiryRequest request);

	// ====================

	/**
	 * Insert listaCompras.
	 * 
	 * @param request the request
	 * @return the listaCompras paged response
	 */
	public ListaComprasResponse insertListaCompraItens(ListaComprasMaintenanceRequest request);

	/**
	 * Update listaCompras.
	 * 
	 * @param request the request
	 * @return the listaCompras paged response
	 */
	public ListaComprasResponse updateListaCompraItens(ListaComprasMaintenanceRequest request);

	/**
	 * Delete listaCompras.
	 * 
	 * @param request the request
	 * @return the listaCompras paged response
	 */
	public ListaComprasResponse deleteListaCompraItens(ListaComprasMaintenanceRequest request);

	/**
	 * Fetch all listaComprass.
	 * 
	 * @param request the request
	 * @return the listaCompras response
	 */
	public ListaComprasResponse fetchAllListaCompraItenss(FetchAllRequest request);

	/**
	 * Refresh listaComprass.
	 * 
	 * @param request the request
	 * @return the listaCompras paged response
	 */
	public ListaComprasResponse refreshListaCompraItenss(RefreshRequest request);

	/**
	 * Fetch listaCompras by id.
	 * 
	 * @param request the request
	 * @return the listaCompras response
	 */
	public ListaComprasResponse fetchListaCompraItensById(FetchByIdRequest request);

	/**
	 * Fetch listaComprass by request.
	 * 
	 * @param request the request
	 * @return the listaCompras paged response
	 */
	public ListaComprasResponse fetchListaCompraItenssByRequest(PagedInquiryRequest request);

}
