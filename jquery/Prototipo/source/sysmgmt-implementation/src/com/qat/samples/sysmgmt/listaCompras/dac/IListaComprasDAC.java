package com.qat.samples.sysmgmt.listaCompras.dac;

import java.util.List;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.listaCompras.model.ListaCompras;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;

/**
 * The Interface IListaComprasDAC. (Data Access Component - DAC)
 */
public interface IListaComprasDAC
{

	/**
	 * Insert listaCompras.
	 * 
	 * @param listaCompras the listaCompras
	 * @return the internal response
	 */
	public InternalResponse insertListaCompras(ListaCompras listaCompras);

	/**
	 * Update listaCompras.
	 * 
	 * @param listaCompras the listaCompras
	 * 
	 * @return the internal response
	 */
	public InternalResponse updateListaCompras(ListaCompras listaCompras);

	/**
	 * Delete listaCompras.
	 * 
	 * @param listaCompras the listaCompras
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteListaCompras(ListaCompras listaCompras);

	/**
	 * Delete all listaComprass.
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteAllListaComprass();

	/**
	 * Fetch all listaComprass.
	 * 
	 * @return the list< listaCompras>
	 */
	public List<ListaCompras> fetchAllListaComprass();

	/**
	 * Fetch county by id.
	 * 
	 * @param request the request
	 * @return the cached results response
	 */

	public ListaCompras fetchListaComprasById(FetchByIdRequest request);

	/**
	 * Fetch listaComprass by request.
	 * 
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<ListaCompras> fetchListaComprassByRequest(PagedInquiryRequest request);

	// ===================================

	/**
	 * Insert listaCompras.
	 * 
	 * @param listaCompras the listaCompras
	 * @return the internal response
	 */
	public InternalResponse insertListaComprasItens(ListaCompras listaCompras);

	/**
	 * Update listaCompras.
	 * 
	 * @param listaCompras the listaCompras
	 * 
	 * @return the internal response
	 */
	public InternalResponse updateListaComprasItens(ListaCompras listaCompras);

	/**
	 * Delete listaCompras.
	 * 
	 * @param listaCompras the listaCompras
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteListaComprasItens(ListaCompras listaCompras);

	/**
	 * Delete all listaComprass.
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteAllListaComprasItenss();

	/**
	 * Fetch all listaComprass.
	 * 
	 * @return the list< listaCompras>
	 */
	public List<ListaCompras> fetchAllListaComprasItenss();

	/**
	 * Fetch county by id.
	 * 
	 * @param request the request
	 * @return the cached results response
	 */

	public ListaCompras fetchListaComprasItensById(FetchByIdRequest request);

	/**
	 * Fetch listaComprass by request.
	 * 
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<ListaCompras> fetchListaComprasItenssByRequest(PagedInquiryRequest request);

}
